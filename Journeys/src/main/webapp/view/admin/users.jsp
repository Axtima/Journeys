<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>Users</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
	<div class="main_admin">
	    <p class="intro">Utilisateurs</p>
	    
	    <c:if test="${!empty userList}">
	        <ul class="admin_list">
	            <c:forEach items="${userList}" var="user">
	                <li>
	                    <a class="link" href="<c:url value="/app/user/edit/${user.id}" />">${user.firstName} ${user.lastName}</a>
	                </li>
	            </c:forEach>
	        </ul>
	    </c:if>
	    
	    <a class="link top" href="<c:url value="/app/user/add" />">Ajouter un utilisateur</a>
	    <a class="link left" href="<c:url value="/app/admin/" />">Page d'administration</a>
    </div>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
    
</body>
</html>