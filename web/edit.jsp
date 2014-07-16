<%@ page import="webapp.model.*" %>
<%@ page import="webapp.util.Util" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%

    Resume resume = (Resume) (request.getAttribute("resume"));
%>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Резюме ${resume.getFullName()}</title>
    <script language="JavaScript">
        function deleteSection(id) {
            var item = document.getElementById(id);
            item.parentNode.removeChild(item);
        }
    </script>
</head>
<body>
<header>Резюме ${resume.getFullName()}
</header>
<section>
    <form id="resume" method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.getUuid()}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="name" size=50 value="${resume.getFullName()}"></dd>
        </dl>
        <dl>
            <dt>Проживание:</dt>
            <dd><input type="text" name="location" size=50 value="${resume.getLocation()}"></dd>
        </dl>
        <h3>Контакты:</h3>
<%
    for (ContactType type : ContactType.values()) {
%>
        <dl>
            <dt><%=type.getTitle()%>:</dt>
            <dd><input type="text" name="<%=type.name()%>" size=30 value="<%=Util.mask(resume.getContact(type))%>">
            </dd>
        </dl>
<%
    }
%>
        <h3>Разделы:</h3>
<%
    int itemNum = 0;

    for (Map.Entry<SectionType, Section> e : resume.getSections().entrySet()) {
        SectionType type = e.getKey();
        Section s = e.getValue();
%>
        <h4><%=type.getTitle()%></h4>

<%
        for (Object item : s.getValues()) {
            itemNum++;
            switch (type) {
                case OBJECTIVE:
                case ACHIEVEMENT:
                case QUALIFICATIONS:
%>
            <span id='s_<%=itemNum%>'>
                <dl>
                    <dd>
                        <%
                            if (type == SectionType.ACHIEVEMENT) {
                        %>
                        <textarea name="<%=type.name()%>" rows=5 cols=75><%=item%>
                        </textarea>
                        <%
                        } else {
                        %>
                        <input type='text' name='<%=type.name()%>' size=80 value='<%=item%>'>
                        <%
                            }
                        %>
                        <span class="small"><a href="#"
                                               onClick="deleteSection('s_<%=itemNum%>');return false;">Удалить</a></span>
                    </dd>
                    <br>
                </dl>
            </span>
<%
                break;
            case EXPERIENCE:
            case EDUCATION:
                Organization org = (Organization) item;
%>
        <div id='s_<%=itemNum%>' class="section_item">
            <dl>
                <dt>Название учереждения:</dt>
                <dd><input type="text" name='<%=type.name()%>' size=100 value="<%=org.getLink().getName()%>"></dd>
            </dl>
            <dl>
                <dt>Сайт учереждения:</dt>
                <dd><input type="text" name='<%=type.name()%>_orgUrl' size=100 value="<%=org.getLink().getUrl()%>">
                </dd>
            </dl>
            <%
                for(Period p: org.getPeriods()){
                    itemNum++;
            %>
            <div id='s_<%=itemNum%>' class="section_item">

                 <%--TODO add period--%>

                <span class="small"><a href="#" onClick="deleteSection('s_<%=itemNum%>');return false;">Удалить</a></span>
            </div>

            <%
                }
            %>
            <span class="small"><a href="#" onClick="deleteSection('s_<%=itemNum%>');return false;">Удалить</a></span>
        </div>
<%
                break;

            default:
                throw new IllegalArgumentException("Type " + type + " is not implemented");
            }
        }
    }
%>
        <br>
        <button type="submit"><%-- onclick="return validate()--%>Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
</body>
</html>
