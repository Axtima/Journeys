<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>Journeys</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <h1>Journeys</h1>
    <c:if test="${!empty journeyList}">
        <table class="data">
            <tr>
                <th>Title</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Protected</th>
                <th>Picture Url</th>
            </tr>
            <c:forEach items="${journeyList}" var="journey">
                <tr>
                    <td>${journey.title}</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${journey.startDate}" /></td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${journey.endDate}" /></td>
                    <td><%= pageContext.getAttribute("journey.password") != null && pageContext.getAttribute("journey.password") != "" %></td>
                    <td><img alt="${journey.title}" src="${journey.pictureUrl}" width="200" /></td>
                    <td><a href="<c:url value="/app/admin/journey/${journey.id}" />">Voir les jours</a></td>
                    <td><a href="<c:url value="/app/admin/journey/edit/${journey.id}" />">Edit</a></td>
                    <td><a href="<c:url value='/app/admin/journey/delete/${journey.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

	<a href="<c:url value="/app/admin/journey/add?userId=${user.id}" />">Add</a>
    <a href="<c:url value="/app/admin/user/" />">Autre utilisateur</a>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
    
</body>
</html>