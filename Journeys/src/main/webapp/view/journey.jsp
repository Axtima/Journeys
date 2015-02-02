<%@page import="com.journeys.entity.Journey"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.journeys.entity.Day"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>

    <meta property="og:title" content="${journey.title}" /> 
	<meta property="og:image" content="${journey.pictureUrl}" /> 
	<meta property="og:site_name" content="My Wonderful Trips"/>
	
    <%@ include file="/view/common/head.jspf" %>
    
    <title>${journey.title}</title>

    <script src="<c:url value="/js/ckeditor/ckeditor.js" /> "></script>
    
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
	<div id="main_journey">
	    <p class="intro">${journey.title}</p>
        
	    <c:if test="${!empty journey.days}">
	        
	        <div class="journey">
	            
	            <%
	            Calendar cal = Calendar.getInstance();
	            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	            %>
	            <c:forEach var="i" begin="1" end="7">
	               <div class="calendar_header">
	                    <fmt:formatDate pattern="EEEE" value="<%= cal.getTime() %>" />
	                    <%
	                    cal.add(Calendar.DAY_OF_MONTH, 1);
	                    %>
	               </div>
	            </c:forEach>
	        
	            <c:forEach var="i" begin="1" end="${paddingStartDate}">
	               <div class="calendar_day">
	               </div>
	            </c:forEach>
	            
	            <c:forEach items="${journey.days}" var="day">
	                <c:if test="${day.enabled}">
	                    <a href="<c:url value="/app/day/${day.id}" />">
	                </c:if>
	                    <%
	                    Day day = (Day)pageContext.getAttribute("day");
	                    String pictureUrl = day.getPictureUrl();
	                    if ((day.getPictureUrl() == null) || "".equals(day.getPictureUrl())) {
	                        pictureUrl = "/Journeys/images/no_image.jpg";
	                    }
	                    
	                    // is Current date ?
	                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dateNow = sdf.format(Calendar.getInstance().getTime()); 
	                    
						String dateDay = sdf.format(day.getDate());
	                    %>
	                    <div style="background-image: url(<%= pictureUrl %>);" class="calendar_day <%= dateNow.equals(dateDay)?"current_calendar_day":"" %>">
	                        <div class="legend">
	                        	<div class="date"><fmt:formatDate pattern="dd MMMM" value="${day.date}" /></div>
	                        	<div class="title">${day.title}</div>
	                        </div>
	                    </div>
	                <c:if test="${day.enabled}">
	                    </a>
	                </c:if>
	            </c:forEach>
	            
	            <c:forEach var="i" begin="1" end="${paddingEndDate}">
	               <div class="calendar_day"></div>
	            </c:forEach>
	             
	         </div>
	        
	    </c:if>

        <div class="clear" style="margin-bottom: 40px;">&nbsp;</div>

        <a class="pdf_link" href="<c:url value="/app/journey/pdf/${journey.id}" />">Exportez en PDF !</a>
        
        <p class="intro" style="font-size: 20px; float: right; margin-right: 20px;">Zone géographique : ${journey.categoryGeo.frenchName}</p>
        
        <div class="clear">&nbsp;</div>
        
        <p class="intro" style="font-size: 20px; float: right; margin-right: 20px;">Type de voyage : ${journey.categoryTrip.frenchName}</p>
        
        <div class="clear" style="margin-bottom: 40px;">&nbsp;</div>
        
        <%@ include file="/view/common/comments.jspf" %>
        
        <a class="link left" href="<c:url value="/app/${journey.user.id}" />">Autre voyage</a>
        <c:if test="<%= SessionUtil.isAdminUser(request) %>">
            <a class="link bottom" href="<c:url value="/app/admin/journey/edit/${journey.id}" />">Modifier</a>
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
    
    <c:if test="${(currentUser != null) && (currentUser.id != journey.user.id) }">
	    <div class="subscribe">
	    	<a href="<c:url value="/app/user/subscribe/${journey.user.id}" />">Suivre les voyages de cet utilisateur</a>
	    </div>
    </c:if>
    
<%--     <%@ include file="/view/common/linker_start.jspf" %>

    	<li class="link first top">
    		<a href="<c:url value="/app/${journey.user.id}" />">Autre voyage</a>
    	</li>

    <%@ include file="/view/common/linker_end.jspf" %>
    
    <%@ include file="/view/common/comments.jspf" %>
    
    <c:if test="${currentUser != null && currentUser.isAdmin()}">
        <%@ include file="/view/common/linker_start.jspf" %>
    
            <li class="link first">
                <a href="<c:url value="/app/admin/journey/edit/${journey.id}" />">Modifier</a>
            </li>
    
        <%@ include file="/view/common/linker_end.jspf" %>
    </c:if> --%>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
    
</body>
</html>