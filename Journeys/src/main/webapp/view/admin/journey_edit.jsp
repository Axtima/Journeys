<%@page import="com.journeys.entity.CategoryGeo"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    
    <title>${journey.title}</title>
    
    <script type="text/javascript">
    
      $(function(){
        
        function refreshPicture() {
            $('#journey_edit_picture').attr('src', $('#pictureUrl').val());
        }
        
        $('#pictureUrl').change(function() {
            refreshPicture();
        });
        
        refreshPicture();
        
        $('.date-picker').datepicker( {
            changeMonth: true,
            changeYear: true,
            showButtonPanel: true,
            dateFormat: 'dd/mm/yy'
        });
        
      });
      
    </script>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <div id="main_journey_edit" class="main_admin">
    
        <img id="journey_edit_picture" alt="${journey.title}" src="${journey.pictureUrl}" />
        
		<p class="intro">
			<c:if test="${journey.id == null}">Ajouter un nouveau voyage</c:if>
	        <c:if test="${journey.id != null}">Modifier le voyage</c:if>
	    </p>
	    
	    <c:url var="post_url"  value="/app/admin/journey/edit/${journey.id}" />
	    
	    <form:form method="post" action="${post_url}" modelAttribute="journey">
	    
	        <form:hidden path="id"/>
	        <input type="hidden" name="userId" value="${user.id}" />
			<div class="field newline long">
	               <label for="title">Titre</label>
	               <form:input path="title" />
			</div>
			<div class="field newline long">
	               <label for="pictureUrl">Illustration</label>
	               <form:input path="pictureUrl" />
			</div>
			<div class="field newline short float_left">
					<label for="startDate">Date de début</label>
					<form:input class="date-picker" type="text" path="startDate" />
			</div>
			<div class="field newline short">
	               <label for="endDate">Date de fin</label>
	               <form:input class="date-picker" type="text" path="endDate" />
			</div>
			<div class="field newline long">
	               <label for="password">Mot de passe</label>
	               <form:password path="password" />
			</div>
            <div class="field newline long">
                   <label for="categoryGeo">Zone géographique</label>
                   <select name="categoryGeoId">
                   		<option value="" ${(journey.categoryGeo == null)?"selected":"" }></option>
                        <%
                        int i=5; 
                        pageContext.setAttribute("i", i);
                        %>
                        <c:forEach items="${categoriesGeo}" var="categoryGeo">
                            <%
                            CategoryGeo categoryGeo = (CategoryGeo)pageContext.getAttribute("categoryGeo");
                            %>
                            <c:if test="<%= categoryGeo.getType() < i %>">
                                <c:if test="<%= i < 5 %>">
                                    </optgroup>
                                </c:if>
                                <%
                                i=i-1;
                                %>
                                <c:if test="<%= i == 4 %>">
                                    <optgroup label="Monde">
                                </c:if>
                                <c:if test="<%= i == 3 %>">
                                    <optgroup label="Continents">
                                </c:if>
                                <c:if test="<%= i == 2 %>">
                                    <optgroup label="Régions">
                                </c:if>
                                <c:if test="<%= i == 1 %>">
                                    <optgroup label="Pays">
                                </c:if>
                            </c:if>
                            <option value="${categoryGeo.id}" ${(journey.categoryGeo.id == categoryGeo.id)?"selected":"" }>${categoryGeo.frenchName}</option>
                        </c:forEach>
                   </select>
            </div>
            <div class="field newline long">
                   <label for="categoryTrip">Type de voyage</label>
                   <select name="categoryTripId">
                   		<option value="" ${(journey.categoryTrip == null)?"selected":"" }></option>
                        <c:forEach items="${categoriesTrip}" var="categoryTrip">
                            <option value="${categoryTrip.id}" ${(journey.categoryTrip.id == categoryTrip.id)?"selected":"" }>${categoryTrip.frenchName}</option>
                        </c:forEach>
                   </select>
            </div>
            	        
	        <input type="submit" name="valid" value="Validation" />

	    </form:form>
	    
    </div>
    
	<div class="main_admin">

	    <c:if test="${!empty journey.days}">

            <c:forEach items="${journey.days}" var="day">
	            <%-- <td><fmt:formatDate pattern="dd/MM/yyyy" value="${day.date}" /></td>
	            <td>${day.title}</td>
	            <td><img alt="${day.title}" src="${day.pictureUrl}" width="200" /></td> --%>
	            <a class="link" href="<c:url value="/app/admin/day/edit/${day.id}" />"><fmt:formatDate pattern="dd/MM/yyyy" value="${day.date}" /> : ${((day.title != null) && (!day.title.equals("")))?day.title:"A compléter"}</a>
            </c:forEach>
	    </c:if>
            
        <p></p>
        
        <c:if test="${journey.id != null}">
	        <a class="link bottom" href="<c:url value='/app/admin/journey/delete/${journey.id}' />">Supprimer</a>
	        <a class="link right" href="<c:url value="/app/journey/${journey.id}" />">Voir le rendu de la page</a>
        </c:if>

    	<a class="link left" href="<c:url value="/app/user/edit/${journey.user.id}" />">Autre voyage</a>
    </div>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
    
</body>
</html>