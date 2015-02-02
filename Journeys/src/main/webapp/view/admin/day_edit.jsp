<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/view/common/head.jspf" %>
    
    <title>${day.title}</title>
    
    <script src="<c:url value="/js/ckeditor/ckeditor.js" /> "></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>
    
    <script type="text/javascript">
    
    var dayMap;
    var marqueur;
    
      $(function(){
          
          function refreshPicture() {
              $('#journey_edit_picture').attr('src', $('#pictureUrl').val());
          }
          
          $('#pictureUrl').change(function() {
              refreshPicture();
          });
        
          refreshPicture();
          
          function initializeMap() {
              
              var centreCarte = new google.maps.LatLng(${day.latitude}, ${day.longitude});
              var mapOptions = {
                center: centreCarte,
                zoom: 10
              };
              dayMap = new google.maps.Map(document.getElementById('map-canvas'),
                  mapOptions);
              
              var optionsMarqueur = {
                      position: dayMap.getCenter(),
                      map: dayMap
              };
              
              marqueur = new google.maps.Marker(optionsMarqueur);
              
              if (${day.latitude} == 0 && ${day.longitude} == 0) {
                  $("#map-canvas").css("display", "none");
              }
          
          }
          
          function refreshMap() {
            var geocoder = new google.maps.Geocoder();
            var address = $('#address').val();
            
            geocoder.geocode( { 'address': address}, function(results, status) {
            
                if (status == google.maps.GeocoderStatus.OK) {
                    var latitude = results[0].geometry.location.lat();
                    var longitude = results[0].geometry.location.lng();
                    dayMap.setCenter(new google.maps.LatLng(latitude, longitude))
                    marqueur.setPosition(new google.maps.LatLng(latitude, longitude))
                    $("#latitude").val(latitude);
                    $("#longitude").val(longitude);
                    $("#map-canvas").css("display", "block");
                } 
            });
          }
          
          $('#resetMap').click(function() {
               $("#latitude").val(0);
               $("#longitude").val(0);
               $("#address").val('');
               $("#map-canvas").css("display", "none");
               dayMap.setCenter(new google.maps.LatLng(0, 0))
          });
        
          $('#address').keydown(function(event) {
              // Enter key
              if(event.which == 13) {
                  event.preventDefault();
                  refreshMap();
                  return false;
              }
          });
          
         $('#refreshMap').click(function() {
             refreshMap();
         });
         
         google.maps.event.addDomListener(window, 'load', initializeMap);
      });

    </script>
    <style type="text/css">
        #map-canvas { height: 300px; width: 300px; }
    </style>
</head>
<body>
    <%@ include file="/view/common/header.jspf" %>
    
    <div id="main_day_edit" class="main_admin">
    
        <img id="day_edit_picture" alt="${day.title}" src="${day.pictureUrl}" />
        
		<p class="intro">
	        Modifier la journée
	    </p>

	    <c:url var="post_url"  value="/app/admin/day/edit/${day.id}" />
	    
	    <form:form method="post" action="${post_url}" modelAttribute="day">
	        <form:hidden path="id"/>
			<div class="field newline short">
	             <span class="field_label">Date</span>
                 <span class="field_value"><fmt:formatDate pattern="dd/MM/yyyy" value="${day.date}" /></span>
			</div>
			<div class="field newline">
	             <label for="title">Titre</label>
	             <form:input style="width:400px;" path="title" />
			</div>
			<div class="field newline">
	             <label for="pictureUrl">Illustration</label>
	             <form:input style="width:400px;" path="pictureUrl" />
			</div>
			<div class="field newline cked">
	               <label for="content">Contenu</label>
	               <form:textarea style="width:400px;" path="content" />
	                  <script type="text/javascript">
	                  CKEDITOR.replace( 'content', {
	                      toolbarGroups: [
	                                      { name: 'document', groups: [ 'mode', /*'document', 'doctools' */] },
	                                      { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
	                                      { name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ] },
	                                      /*{ name: 'forms' },*/
	                                      { name: 'basicstyles', groups: [ 'basicstyles'/*, 'cleanup' */] },
	                                      '/',
	                                      { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align'/*, 'bidi' */] },
	                                      { name: 'links' },
	                                      { name: 'insert' },
	                                      '/',
	                                      { name: 'styles' },
	                                      { name: 'colors' },
	                                      { name: 'tools' },
	                                      { name: 'others' }/*,
	                                      { name: 'about' }*/
	                                  ],
	                  height: '800px'
	                  });
	                  
	                  </script>
			</div>
			<div class="field_address field newline long">
	               <label for="address">Rechercher une adresse à marquer</label>
	               <input type="text" id="address" name="address" />
                   <input class="small_button" type="button" id ="refreshMap" value="Rechercher" />
                   <input class="small_button" type="button" id ="resetMap" value="Réinitialiser" />
	               <div>
	                   <form:hidden path="latitude" />
	                   <form:hidden path="longitude" />
	                   <div id="map-canvas"></div>
	               </div>
			</div>
			<div class="field newline">
	               <label for="enabled">Activer la page ?</label>
	               <input type="checkbox" name="enabled" value="true" id="id3" <c:if test="${day.enabled == 'true'}">checked="checked"</c:if>/>
			</div>
	
	        <input type="submit" name="valid" value="Validation" />
	    </form:form>
        
        <a class="link right" href="<c:url value="/app/day/${day.id}" />">Voir le rendu de la page</a>
        <a class="link left" href="<c:url value="/app/admin/journey/edit/${day.journey.id}" />">Autre journée</a>
        
    </div>
    
    
    <%@ include file="/view/common/linker_start.jspf" %>

    <%@ include file="/view/common/linker_end.jspf" %>
    
    <%@ include file="/view/common/footer_start.jspf" %>

    <%@ include file="/view/common/footer_end.jspf" %>
    
</body>
</html>