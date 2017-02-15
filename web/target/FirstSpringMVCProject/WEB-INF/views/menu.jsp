<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Menu Page</title>
</head>
<body>
<h1>
    Add a Menu
</h1>

<c:url var="addAction" value="/menu/add"></c:url>

<form:form action="${addAction}" commandName="menu">
    <table>
        <c:if test="${!empty menu.title}">
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
                <form:label path="status">
                    <spring:message text="STATUS"/>
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
                <c:if test="${!empty menu.title}">

                    <input type="submit"
                           value="<spring:message text="Edit Menu"/>"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </c:if>
                <c:if test="${empty menu.title}">
                    <input type="submit"
                           value="<spring:message text="Add Menu"/>"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Menu List</h3>
<c:if test="${!empty listMenus}">
    <table class="tg">
        <tr>
            <th width="80">Menu ID</th>
            <th width="120">Menu Title</th>
            <th width="120">Menu Status</th>
            <th width="120">Created By</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
            <th width="60">Content</th>
        </tr>
        <c:forEach items="${listMenus}" var="menu">
            <tr>
                <td>${menu.id}</td>
                <td>${menu.title}</td>
                <td><c:if test="${menu.status=='1'}">Active</c:if>
                    <c:if test="${menu.status=='0'}">Passive</c:if>
                </td>
                <td>${menu.user.username}</td>
                <td><a href="<c:url value='/menu/edit/${menu.id}' />">Edit</a></td>
                <td><a href="<c:url value='/menu/remove/${menu.id}' />">Delete</a></td>
                <td><a href="<c:url value='/contents/${menu.id}' />">Add Content</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>