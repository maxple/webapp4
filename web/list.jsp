<%@ page import="webapp.model.Resume" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
<header>Список всех резюме</header>
<section>
    <table>
        <tr>
            <td colspan="5" style="text-align: right"><a href="resume?action=create"><img src="img/add.png"> Добавить
                Резюме</a></td>
        </tr>
        <tr>
            <td>
                <table border="1" cellpadding="8" cellspacing="0">
                    <tr>
                        <th>Имя</th>
                        <th>Проживание</th>
                        <th>Email</th>
                        <th><img src="img/s.gif"></th>
                        <th><img src="img/s.gif"></th>
                    </tr>
                    <% for (Resume r : Config.getStorage().getCollection()) { %>
                    <tr>
                        <td><a href="resume?uuid=<%=r.getUuid()%>&action=view"><%=r.getFullName()%>
                        </a>
                        </td>
                        <td><%=HtmlUtil.mask(r.getLocation())%>
                        </td>
                        <td><%=HtmlUtil.mask(r.getContact(ContactType.MAIL))%>
                        </td>
                        <td><a href="resume?uuid=<%=r.getUuid()%>&action=delete"><img src="img/delete.png"></a></td>
                        <td><a href="resume?uuid=<%=r.getUuid()%>&action=edit"><img src="img/pencil.png"></a></td>
                    </tr>
                    <% } %>

                </table>
            </td>
            <td>
            </td>
        </tr>
    </table>
</section>
</body>
</html>