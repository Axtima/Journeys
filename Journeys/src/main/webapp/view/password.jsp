<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>${journey.title}</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <div id="main_password">
    
        <p class="intro">${journey.title} nécessite un mot de passe</p>
        
        <c:url var="post_url"  value="/app/journey/${journey.id}" />
        
        <form:form method="post" action="${post_url}" modelAttribute="journey">
            <form:hidden id="journeyId" path="id"/>
                <div class="field newline">
                    <form:label path="password">Password</form:label>
                    <form:password style="width:400px;" path="password" />
                </div>            
            <input type="submit" name="valid" value="Validation" />
            <input type="submit" name="cancel" value="Annuler" />
        </form:form>
    </div>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
</body>
</html>