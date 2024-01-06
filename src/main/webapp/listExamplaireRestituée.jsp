<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.java.code.Examplaire" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Examplaire Restitue</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>
<form action="/rechercher" method="get">
  <label for="search">Rechercher :</label>
  <input type="search" id="search" name="q" placeholder="Entrez votre recherche ici" />
  <input type="submit" value="Rechercher" />
</form>

<table class="responsive-table striped centered">
        <thead>
          <tr>
              <th>IdExamplaire</th>
              <th>Isbn</th>
              <th>Rayon</th>
              <th>Etat</th>
              
          </tr>
        </thead>

        <tbody>
           <%
Object ExamplairerestitueeObj = request.getAttribute("ExamplaireRes");

if (ExamplairerestitueeObj  instanceof List) {
    List<Examplaire> examplairest = (List<Examplaire>) ExamplairerestitueeObj;

    for (Examplaire examplaireres : examplairest) {
%>
    <tr>
        <td><%= examplaireres.getId() %></td>
        <td><%= examplaireres.getIsbn() %></td>
        <td><%= examplaireres.getRayon() %></td>
         <td><%= examplaireres.getEtat() %></td>
       
       
        <!-- Ajoutez d'autres attributs de l'abonné -->
    </tr>
<%
    }
} else {
    // Gérer le cas où l'attribut n'est pas une liste d'Abonnee
    // Peut-être afficher un message d'erreur ou faire une action appropriée
}
%>
        <a href="Examplaire">Charger les Examplaire Restituee</a>
            
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>