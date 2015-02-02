<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>Days</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
	<div class="main_admin">
	    <p class="intro">${journey.title}</p>
	    <c:if test="${!empty dayList}">

            <c:forEach items="${dayList}" var="day">
	            <%-- <td><fmt:formatDate pattern="dd/MM/yyyy" value="${day.date}" /></td>
	            <td>${day.title}</td>
	            <td><img alt="${day.title}" src="${day.pictureUrl}" width="200" /></td> --%>
	            <a class="link" href="<c:url value="/app/admin/day/edit/${day.id}" />">${day.title} (<fmt:formatDate pattern="dd/MM/yyyy" value="${day.date}" />)</a>
            </c:forEach>
	    </c:if>

    	<a class="link left" href="<c:url value="/app/user/edit/${journey.user.id}" />">Autre voyage</a>
    </div>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
    
</body>
</html>