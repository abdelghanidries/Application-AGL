<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rapport</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>
 <div class="row">
    <form class="col s12" method="post" action="Rapport">
    <div>
    <% String statusMessager = (String) request.getAttribute("status"); %>
    <% if (statusMessager  != null && !statusMessager .isEmpty()) { %>
        <p class="text-danger"><%= statusMessager  %></p>
    <% } %>
</div>
    <div class="row">
        <div class="input-field col s6">
          <input id="last_name" type="text" class="validate" name="matricule">
          <label for="matricule">Matricule</label>
        </div>
      </div>
    
     <div class="row">
        <div class="input-field col s6">
          <input id="last_name" type="text" class="validate" name="isbn">
          <label for="isbn">Isbn</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="last_name" type="text" class="validate" name="id">
          <label for="id">IdExamplaire</label>
        </div>
      </div>
      
      <div class="row">
        <div class="input-field col s12">
           <input type="text" class="datepicker" id ="dateemprunt" name="dateemprunt">
          <label for="dateemprunt">Date Emprunt</label>
        </div>
      </div>
       <div class="row">
        <div class="input-field col s12">
           <input type="text" class="" id="daterestitution" name="daterestitution">
          <label for="daterestitution">Date Restitution</label>
        </div>
      </div>
      </div>
     
       <button class="btn btn-primary" type="submit">Submit form</button>
     
    </form>
  </div>
 <script >  document.addEventListener('DOMContentLoaded', function() {
	    var elems = document.querySelectorAll('.datepicker');
	    var instances = M.Datepicker.init(elems, {
	        // Options du DatePicker
	        format: 'yyyy-mm-dd', // Format de la date
	        // Ajoutez d'autres options si nécessaire
	      });
	  });
        //date apres 15  jours
      document.addEventListener('DOMContentLoaded', function() {
    var dateEmprunt = document.querySelector('input[name="dateemprunt"]');
    var dateRestitution = document.querySelector('input[name="daterestitution"]');
    
    dateEmprunt.addEventListener('change', function() {
      // Récupérer la date sélectionnée
      var selectedDate = new Date(dateEmprunt.value);
      
      // Ajouter 15 jours à la date d'emprunt pour obtenir la date de restitution
      var date15DaysLater = new Date(selectedDate.getTime() + (15 * 24 * 60 * 60 * 1000));
      
      // Formatter la date pour l'affichage (YYYY-MM-DD)
      var formattedDate = date15DaysLater.toISOString().split('T')[0];
      
      // Mettre à jour le champ de la date de restitution
      dateRestitution.value = formattedDate;
    });
  });
 </script>


 <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>