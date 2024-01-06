<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.java.code.Rapport" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liste rapports</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>

<table class="responsive-table striped centered">
        <thead>
          <tr>
              <th>Matricule</th>
              <th>Isbn</th>
              <th>Id Examplaire</th>
              <th>Date Emprunt</th>
              <th>Date Restitution</th>
              <th>Porolonger</th>
              <th>Supprimer</th>
              <th>mise a jours des informations d'un rapport</th>
              <th>Prolonger Date Restitution</th>
              
          </tr>
        </thead>

        <tbody>
           <%
Object RappotsObj = request.getAttribute("rapports");

if (RappotsObj instanceof List) {
    List<Rapport> rapports = (List<Rapport>) RappotsObj;

    for (Rapport rapportss : rapports) {
%>
    <tr>
        <td><%= rapportss.getMatricule() %></td>
        <td><%= rapportss.getIsbn() %></td>
        <td><%= rapportss.getId() %></td>
         <td><%= rapportss.getDateemprunt() %></td>
       
        <td><%= rapportss.getDaterestitution() %></td>
        <td><%= rapportss.getProlonger() %></td>
          <td><a href="Rapport?deleteRapportId=<%= rapportss.getId() %>">Supprimer un rapport</a></td>
            <td><form action="updaterapport.jsp" method="post">
    <input type="hidden" name="matricule" value="<%= rapportss.getMatricule() %>">
    <input type="hidden" name="isbn" value="<%= rapportss.getIsbn() %>">
    <input type="hidden" name="id" value="<%= rapportss.getId() %>">
    <input type="hidden" name="dateemprunt" value="<%= rapportss.getDateemprunt() %>">
    <input type="hidden" name="daterestitution" value="<%= rapportss.getDaterestitution() %>">
   
    <button class="waves-effect waves-light btn" type="submit">Update</button>
</form></td>
           <td><a href="Rapport?updateRapportId=<%= rapportss.getId() %>">pronloger</a></td>
           
        <!-- Ajoutez d'autres attributs de l'abonné -->
    </tr>
<%
    }
} else {
    // Gérer le cas où l'attribut n'est pas une liste d'Abonnee
    // Peut-être afficher un message d'erreur ou faire une action appropriée
}
%>
        <a href="Rapport">Charger les rappots</a>
            
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>