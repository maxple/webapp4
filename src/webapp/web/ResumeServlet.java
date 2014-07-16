package webapp.web;

import webapp.Config;
import webapp.model.*;
import webapp.storage.IStorage;
import webapp.util.Util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User: gkislin
 * Date: 11.07.2014
 */
public class ResumeServlet extends HttpServlet {
    private IStorage storage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        Resume r = new Resume(
                uuid,
                request.getParameter("name"),
                request.getParameter("location"));

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (!Util.isEmpty(value)) {
                r.addContact(type, value);
            }
        }

        for (SectionType type : SectionType.values()) {
            String[] values = request.getParameterValues(type.name());
            if (!Util.isEmpty(values)) {
                switch (type) {
                    case OBJECTIVE:
                    case QUALIFICATIONS:
                    case ACHIEVEMENT:
                        if (Util.isEmpty(values[0])) {
                            values = Arrays.copyOfRange(values, 1, values.length);
                        }
                        r.addSection(type, values);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        String[] orgUrls = request.getParameterValues(type.name() + "_orgUrl");
                        List<Organization> orgList = new LinkedList<>();
                        for (int i = 0; i < values.length; i++) {
                            String orgName = values[i];
                            if (!Util.isEmpty(orgName)) {
                                orgList.add(new Organization(orgName, orgUrls[i]));
                            }
                        }
                        r.addSection(type, orgList.toArray(new Organization[orgList.size()]));
                        break;
                }
            }
        }

        if (Util.isEmpty(uuid)) {
            r.createUuid();
            Config.getStorage().save(r);
        } else {
            Config.getStorage().update(r);
        }
        response.sendRedirect("list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
/*
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer w = response.getWriter();
        String name = request.getParameter("name");

        w.write("Тест сервелет: привет " + name);
        w.close();
*/
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume r;

        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("list");
                return;
            case "create":
                r = Resume.EMPTY;
                break;
            case "view":
                r = storage.load(uuid);
                break;
            case "edit":
                r = storage.load(uuid);
                // add first item in every edited item
                for (SectionType type : SectionType.values()) {
                    Section s = r.getSection(type);
                    if (s == null) {
                        r.addSection(type, type.getSectionClass().getEmptySection());
                    } else {
                        type.addEmptyValue(s);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/view.jsp" : "/edit.jsp")
        ).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getStorage();
    }
}
