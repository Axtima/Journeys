<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>Login</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <div id="main_login" class="main_admin">
    
        <div class="message error_message">Vous n'avez pas les permissions requises pour accéder à cette page</div>
        
        <a class="link top" href="<c:url value="/app/"/>">Accueil</a>
    </div>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
</body>
</html>
