<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    
    <meta property="og:title" content="${day.title}" /> 
	<meta property="og:image" content="${day.pictureUrl}" /> 
	<meta property="og:site_name" content="My Wonderful Trips"/>
	
    <title>${day.title}</title>
    
    <script src="<c:url value="/js/ckeditor/ckeditor.js" /> "></script>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js">
    </script>
    <script type="text/javascript">
      function initialize() {
        
        if ((${day.latitude} != 0) || (${day.longitude} != 0)) {
            
            var centreCarte = new google.maps.LatLng(${day.latitude}, ${day.longitude});
            
            var mapOptions = {
              center: centreCarte,
              zoom: 8
            };
            var dayMap = new google.maps.Map(document.getElementById('map-canvas'),
                mapOptions);
            
            var optionsMarqueur = {
                    position: dayMap.getCenter(),
                    map: dayMap
            };
            var marqueur = new google.maps.Marker(optionsMarqueur);
        }
        
      }
      google.maps.event.addDomListener(window, 'load', initialize);
    </script>
    <style type="text/css">
        #map-canvas { height: 300px; width: 300px; margin: 20px; float: right;}
    </style>

</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <div id="main_day">
        <h1 class="day_title">${day.title}</h1>
        
        <c:if test="${(day.latitude != 0) || (day.longitude != 0)}" >
            <div id="map-canvas"></div>
        </c:if>
        
        <div id="content_day">
            <c:choose>
                <c:when test="${day.content eq ''}">
                    L'utilisateur n'a pas encore rédigé cette journée
                </c:when>
                <c:otherwise>
                    ${day.content}
                </c:otherwise>            
            </c:choose>
        </div>
        
        <%@ include file="/view/common/comments.jspf" %>
        
        <c:if test="${previousDay != null}">
            <a class="link left" href="<c:url value="/app/day/${previousDay.id}" />">Previous Day</a>
        </c:if>
        <a class="link top" href="<c:url value="/app/journey/${day.journey.id}" />">${day.journey.title}</a>
        <c:if test="${currentUser != null && currentUser.isAdmin()}">
            <a class="link bottom" href="<c:url value="/app/admin/day/edit/${day.id}" />">Modifier</a>
        </c:if>
        <c:if test="${nextDay != null}">
            <a class="link right" href="<c:url value="/app/day/${nextDay.id}" />">Next Day</a>
        </c:if>
    </div>

    <!-- Facebook -->
    <div class="facebook">
		<iframe src="//www.facebook.com/plugins/share_button.php?href=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;layout=button" scrolling="no" frameborder="0" style="border:none; overflow:hidden;" allowTransparency="true"></iframe>
    </div>
    
    <!-- Twitter -->
    <div class="twitter">
		<a href="https://twitter.com/share" class="twitter-share-button" data-count="none">Tweet</a>
	</div>
	<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
    
    
    <%-- 
    <%@ include file="/view/common/linker_start.jspf" %>
    	<c:if test="${previousDay != null}">
	    	<li class="link first left">
	    		<a href="<c:url value="/app/day/${previousDay.id}" />">Previous Day</a>
	    	</li>
    	</c:if>
    	<li class="link top <c:if test="${previousDay == null}">first</c:if>">
    		<a href="<c:url value="/app/journey/${day.journey.id}" />">${day.journey.title}</a>
    	</li>
    	<c:if test="${nextDay != null}">
	    	<li class="link right">
	    		<a href="<c:url value="/app/day/${nextDay.id}" />">Next Day</a>
	    	</li>
    	</c:if>
    <%@ include file="/view/common/linker_end.jspf" %>

	<%@ include file="/view/common/comments.jspf" %>
	
    <c:if test="${currentUser != null && currentUser.isAdmin()}">
        <%@ include file="/view/common/linker_start.jspf" %>
    
            <li class="link first">
                <a href="<c:url value="/app/admin/day/edit/${day.id}" />">Modifier</a>
            </li>
    
        <%@ include file="/view/common/linker_end.jspf" %>
    </c:if> --%>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>

</body>
</html>