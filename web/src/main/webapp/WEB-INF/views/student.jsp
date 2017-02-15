<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Student Page</title>
</head>
<body>
<h1>
    Add a Student
</h1>

    <c:url var="addAction" value="/student/add"></c:url>

    <form:form action="${addAction}" commandName="student">
        <table>
            <c:if test="${!empty student.name}">
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
                    <form:label path="name">
                        <spring:message text="Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                </td>
            </tr>
            <tr>
                <td>
                        <form:label path="surname">
                            <spring:message text="Surname"/>
                        </form:label>
                </td>
                <td>
                    <form:input path="surname"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="tc">
                        <spring:message text="TC"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="tc"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty student.name}">

                        <input type="submit"
                               value="<spring:message text="Edit Student"/>"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </c:if>
                    <c:if test="${empty student.name}">
                        <input type="submit"
                               value="<spring:message text="Add Student"/>"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
<br>
<h3>Student List</h3>
<c:if test="${!empty listStudents}">
    <table class="tg">
        <tr>
            <th width="80">Student ID</th>
            <th width="120">Student Name</th>
            <th width="120">Student TC</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listStudents}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.tc}</td>
                <td><a href="<c:url value='/edit/${student.id}' />">Edit</a></td>
                <td><a href="<c:url value='/remove/${student.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>