<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>Blog</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <div id="main_user">

        <c:if test="${empty journeyList}">
            <p class="intro">
                ${user.firstName} ${user.lastName} n'a encore aucun voyage !
            </p>
        </c:if>
        <c:if test="${!empty journeyList}">
            <p class="intro">
                Les voyages de ${user.firstName} ${user.lastName} !
            </p>
            <ul class="journey_banner">
                <c:forEach items="${journeyList}" var="journey">
                    <li style="background-image:url(${journey.pictureUrl});">
                        <a href="<c:url value="/app/journey/${journey.id}" />">
                            <c:out value="${journey.title}" />
                        </a>
                     </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
    
    <a class="link left" href="<c:url value="/app/" />">Autre utilisateur</a></li>
    
<%--     <%@ include file="/view/common/linker_start.jspf" %>

    	<li class="link first top">
    		<a href="<c:url value="/app/" />">Autre utilisateur</a></li>
    	</li>

    <%@ include file="/view/common/linker_end.jspf" %> --%>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
    
</body>
</html>
