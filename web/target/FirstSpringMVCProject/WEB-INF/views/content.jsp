<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Content Page</title>
</head>
<body>
<h1>
    Add a Content to a Menu
</h1>

<c:url var="addAction" value="/content/add?${_csrf.parameterName}=${_csrf.token}"></c:url>

<form:form action="${addAction}" commandName="content" enctype="multipart/form-data">
    <table>
        <c:if test="${!empty content.title}">
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
            <td>Pick file :</td>
            <td><input type="file" name="fileUpload" size="50" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty content.title}">

                    <input type="submit"
                           value="<spring:message text="Edit Content"/>"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </c:if>
                <c:if test="${empty content.title}">
                    <input type="submit"
                           value="<spring:message text="Add Content to This Menu"/>"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Menu List</h3>
<c:if test="${!empty listContents}">
    <table class="tg">
        <tr>
            <th width="80">Content ID</th>
            <th width="120">Content Title</th>
            <th width="120">Menu Name</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listContents}" var="content">
            <tr>
                <td>${content.id}</td>
                <td>${content.title}</td>
                <td>${content.menu.title}</td>
                <td><a href="<c:url value='/content/edit/${content.id}' />">Edit</a></td>
                <td><a href="<c:url value='/content/remove/${content.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="<c:url value='/menus'/>">All Menus</a>
    <a href="<c:url value='/contents'/>">All Contents</a>
</c:if>
</body>
</html>