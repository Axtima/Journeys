<%@page import="com.journeys.util.SessionUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>Admin</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <div id="main_admin" class="main_admin">
        <p class="intro">
            Espace administration
        </p>
        <c:if test="<%= SessionUtil.isAdminUser(request) %>">
            <a class="link" href="<c:url value="/app/admin/user/" />">Utilisateurs</a>
            <a class="link top" href="<c:url value='/app/user/edit/' />">Ajouter un utilisateur</a>
            <a class="link top" href="<c:url value='/app/admin/reindex' />">Tout réindexer dans le moteur de recherche</a>
        </c:if>
        <%
        pageContext.setAttribute("currentUserId", SessionUtil.getAuthenticatedUser(request).getId());
        %>
		<a class="link" href="<c:url value="/app/user/edit/${currentUserId}" />">Gérer mon compte et mes voyages !</a>
		<a class="link left" href="<c:url value="/app/" />">Page d'accueil</a>
    </div>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
    
</body>
</html>
