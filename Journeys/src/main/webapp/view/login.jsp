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
    
		<c:if test="${not empty param.error}">
			<div class="message error_message">L'identifiant ou le mot de passe est erroné</div>
		</c:if>
		
		<p class="intro">
            Authentification
        </p>
 
		<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
			
			<div class="field newline medium">
				<label for="username">Email</label>
				<input type='text' name='username' value=''>
			</div>
			<div class="field newline medium">
				<label for="password">Mot de passe</label>
				<input type='password' name='password' />
			</div>
			<input name="submit" type="submit" value="Valider" />
 
		  <%-- <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> --%>
 
		</form>
		
		<a class="link" href="<c:url value='/app/user/add' />">S'inscrire</a>
    </div>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
</body>
</html>
