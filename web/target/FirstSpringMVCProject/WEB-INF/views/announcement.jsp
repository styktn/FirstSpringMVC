<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Announcement Page</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<h1>
    Add a new announcement
</h1>

<c:url var="addAction" value="/announcement/add"></c:url>

<form:form action="${addAction}" commandName="announcement">
    <table>
        <c:if test="${!empty announcement.title}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="text">
                    <spring:message text="Text"/>
                </form:label>
            </td>
            <td>
                <form:textarea path="text"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="enddate">
                    <spring:message text="End Date"/>
                </form:label>
            </td>
            <td>
                <form:input path="enddate"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="status">
                    <spring:message text="Status"/>
                </form:label>
            </td>
            <td>
                <form:select path="status">
                    <form:option value="0">Passive</form:option>
                    <form:option value="1">Active</form:option>
                </form:select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty announcement.title}">

                    <input type="submit"
                           value="<spring:message text="Edit announcement"/>"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </c:if>
                <c:if test="${empty announcement.title}">
                    <input type="submit"
                           value="<spring:message text="Add announcement"/>"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Menu List</h3>
<c:if test="${!empty listAnnouncements}">
    <table class="tg">
        <tr>
            <th width="80">Announcement ID</th>
            <th width="120">Announcement Title</th>
            <th width="100"> Owner </th>
            <th width="100"> End Date </th>
            <th width="100"> Created </th>
            <th width="100"> Updated </th>
            <th width="100"> Status </th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listAnnouncements}" var="announcement">
            <tr>
                <td>${announcement.id}</td>
                <td>${announcement.title}</td>
                <td>${announcement.owner.username}</td>
                <td>${announcement.enddate}</td>
                <td>${announcement.created}</td>
                <td>${announcement.updated}</td>
                <td><c:if test="${announcement.status=='1'}">Active</c:if>
                    <c:if test="${announcement.status=='0'}">Passive</c:if></td>
                <td><a href="<c:url value='/announcement/edit/${announcement.id}' />">Edit</a></td>
                <td><a href="<c:url value='/announcement/remove/${announcement.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="<c:url value='/menus'/>">All Menus</a>
    <a href="<c:url value='/contents'/>">All Contents</a>
</c:if>
</body>
</html>