<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    <title>Résultats de la recherche "${searchTerms}"</title>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <div id="main_search_result">
    
		 <%@ include file="/view/common/search.jspf" %>
		 
		 <%@ include file="/view/common/search_results.jspf" %>
    </div>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
</body>
</html>
