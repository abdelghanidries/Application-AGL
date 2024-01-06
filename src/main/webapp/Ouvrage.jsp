<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ouvrage</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>

  <div class="row">
    <form class="col s12" method="post" action="Ouvrage">
     <div class="row">
        <div class="input-field col s6">
          <input id="last_name" type="text" class="validate" name="isbn">
          <label for="isbn">Isbn</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="last_name" type="text" class="validate" name="titre">
          <label for="titre">Titre</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="last_name" type="text" class="validate" name="auteurs">
          <label for="auteurs">Auteurs</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
           <input type="text" class="datepicker" name="datepub">
          <label for="datepub">Date</label>
        </div>
      </div>
       <div class="row">
        <div class="input-field col s6">
          <input id="last_name" type="text" class="validate" name="quantite">
          <label for="quantite">Quantite</label>
        </div>
      </div>
      </div>
     
       <button class="waves-effect waves-light btn" type="submit">Submit form</button>
     
    </form>
  </div>
 <script >  document.addEventListener('DOMContentLoaded', function() {
	    var elems = document.querySelectorAll('.datepicker');
	    var instances = M.Datepicker.init(elems, {
	        // Options du DatePicker
	        format: 'yyyy-mm-dd', // Format de la date
	        // Ajoutez d'autres options si nécessaire
	      });
	  });</script>


 <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>