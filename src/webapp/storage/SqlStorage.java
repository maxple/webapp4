package webapp.storage;

import webapp.WebAppException;
import webapp.model.Resume;
import webapp.sql.Sql;
import webapp.sql.SqlExecutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * User: gkislin
 * Date: 14.07.2014
 */
public class SqlStorage implements IStorage {

    @Override
    public void clear() {
        Sql.execute("DELETE FROM RESUME", new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement ps) throws SQLException {
                ps.execute();
                return null;
            }
        });
    }

    @Override
    public void save(final Resume r) {
        Sql.execute("INSERT INTO resume (uuid, full_name, location) VALUES(?,?,?)",
                new SqlExecutor<Void>() {
                    @Override
                    public Void execute(PreparedStatement ps) throws SQLException {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.setString(3, r.getLocation());
                        ps.execute();
                        return null;
                    }
                }
        );
    }

    @Override
    public void update(final Resume r) {
        if (Sql.execute("UPDATE resume SET full_name=?, location=? WHERE uuid=?",
                new SqlExecutor<Integer>() {
                    @Override
                    public Integer execute(PreparedStatement ps) throws SQLException {
                        ps.setString(1, r.getFullName());
                        ps.setString(2, r.getLocation());
                        ps.setString(3, r.getUuid());
                        return ps.executeUpdate();
                    }
                }) == 0) throw new WebAppException("Resume " + r.getUuid() + "not exist", r.getUuid());
    }

    @Override
    public Resume load(final String uuid) {
        return Sql.execute("SELECT r.uuid, r.full_name, r.location FROM RESUME AS r WHERE r.uuid=?",
                new SqlExecutor<Resume>() {
                    @Override
                    public Resume execute(PreparedStatement ps) throws SQLException {
                        ps.setString(1, uuid);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            return new Resume(uuid, rs.getString("full_name"), rs.getString("location"));
                        }
                        throw new WebAppException("Resume " + uuid + " is not found");
                    }
                });
    }

    @Override
    public void delete(final String uuid) {
/*
        try (Connection conn = Sql.CONN_FACTORY.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM RESUME WHERE uuid=?")) {
            ps.setString(1, uuid);
            ps.execute();
        } catch (SQLException e) {
            throw new WebAppException("SQL failed", e);
        }
*/
        // Strategy
        if (Sql.execute("DELETE FROM RESUME WHERE uuid=?", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, uuid);
                return ps.executeUpdate();
            }
        }) == 0) throw new WebAppException("Resume " + uuid + "not exist", uuid);
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return Sql.execute("SELECT r.uuid, r.full_name, r.location  FROM RESUME AS r order by r.full_name, r.uuid",
                new SqlExecutor<Collection<Resume>>() {
                    @Override
                    public Collection<Resume> execute(PreparedStatement ps) throws SQLException {
                        List<Resume> res = new LinkedList<>();
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String uuid = rs.getString("uuid");
                            res.add(new Resume(uuid, rs.getString("full_name"), rs.getString("location")));
                        }
                        return res;
                    }
                });
    }

    @Override
    public int size() {
        return Sql.execute("SELECT count(*) FROM RESUME", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        });
    }
}