<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>User</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>

    <div id="main_user_edit" class="main_admin">

		<p class="intro">
			<c:if test="${user.id == null}">Inscription</c:if>
	        <c:if test="${user.id != null}">Modifier le compte</c:if>
	    </p>
        
	    <c:url var="post_url"  value="/app/user/edit/${user.id}" />
	    
	    <form:form method="post" action="${post_url}" modelAttribute="user">
	        <form:hidden path="id"/>
	        <div class="field newline">
	            <form:label path="email"><spring:message code="label.email" /></form:label>
	            <form:input path="email" />
	        </div>
	        <div class="field newline">
	            <form:label path="firstName"><spring:message code="label.firstName" /></form:label>
	            <form:input path="firstName" />
	        </div>
	        <div class="field newline">
	            <form:label path="lastName"><spring:message code="label.lastName" /></form:label>
	            <form:input path="lastName" />
	        </div>
			<div class="field newline">
				<form:label path="password">Mot de passe</form:label>
				<form:password path="password" />
			</div>
			<div class="field newline">
				<label for="confirmPassword">Confirmation du mot de passe</label>
				<input type="password" name="confirmPassword" />
			</div>
			
	        <input type="submit" name="valid" value="Validation" />

	    </form:form>
        
        <c:if test="<%= SessionUtil.getAuthenticatedUser(request) != null %>">
			<p class="intro">
	            Voyages de ${user.firstName} ${user.lastName}
	        </p>
	        <ul class="admin_list">
	            <c:forEach items="${user.journeys}" var="journey">
	                <li>
	                    <a class="link" href="<c:url value="/app/admin/journey/edit/${journey.id}" />">${journey.title}</a>
	                </li>
	            </c:forEach>
	        </ul>
	        
	        <c:if test="${user.id != null}">
	            <a class="link top" href="<c:url value='/app/admin/journey/add?userId=${user.id}' />">Ajouter un voyage</a>
	            <a class="link bottom" href="<c:url value='/app/admin/user/delete/${user.id}' />">Supprimer l'utilisateur</a>
	        </c:if>
	        
	        <c:if test="<%= SessionUtil.isAdminUser(request) %>">
	        	<a class="link left" href="<c:url value="/app/admin/user" />">Autre utilisateur</a>
	        </c:if>
	        <c:if test="<%= !SessionUtil.isAdminUser(request) %>">
	        	<a class="link left" href="<c:url value="/app/" />">Page d'accueil</a>
	        </c:if>
		</c:if>
	</div>
	
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
    
</body>
</html>