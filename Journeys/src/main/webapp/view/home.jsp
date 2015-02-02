<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>Blog</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <div id="main_home">
    	
    	<%@ include file="/view/common/search.jspf" %>
    	
        <p class="intro">
            Découvrez les voyages de ...
        </p>
        <c:if test="${!empty userList}">
            <ul class="user">
                <c:forEach items="${userList}" var="user">
                    <li>
                        <a href="<c:url value="/app/${user.id}" />">
                            <c:out value="${user.firstName} ${user.lastName}" />
                        </a>
                     </li>
                </c:forEach>
            </ul>
        </c:if>
	    <c:if test="<%= SessionUtil.isAuthenticatedUser(request) %>">
	
			<c:if test="${!empty currentUser.followedUsers}">
		        <p class="intro">
		            Les utilisateurs que vous suivez ...
		        </p>
	            <ul class="user">
	                <c:forEach items="${currentUser.followedUsers}" var="followedUser">
	                    <li>
	                        <a href="<c:url value="/app/${followedUser.id}" />">
	                            <c:out value="${followedUser.firstName} ${followedUser.lastName}" />
	                        </a>
	                     </li>
	                </c:forEach>
	            </ul>
	        </c:if>
	    </c:if>
    </div>
    

    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
</body>
</html>
