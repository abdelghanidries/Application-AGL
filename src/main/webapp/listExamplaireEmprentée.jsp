<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.java.code.Examplaire" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Examplaire Emprunt</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>

<table class="responsive-table striped centered">
        <thead>
          <tr>
              <th>IdExamplaire</th>
              <th>Isbn</th>
              <th>Rayon</th>
              <th>Etat</th>
              <th>changer l'etat vers restituée</th>
              
          </tr>
        </thead>

        <tbody>
           <%
Object ExamplairerempruntObj = request.getAttribute("Examplaireemprunt");

if (ExamplairerempruntObj  instanceof List) {
    List<Examplaire> examplairemprunt = (List<Examplaire>) ExamplairerempruntObj;

    for (Examplaire examplaireemprunt : examplairemprunt) {
%>
    <tr>
        <td><%= examplaireemprunt.getId() %></td>
        <td><%= examplaireemprunt.getIsbn() %></td>
        <td><%= examplaireemprunt.getRayon() %></td>
         <td><%= examplaireemprunt.getEtat() %></td>
         <td>
       
    </td>
       
       
        <!-- Ajoutez d'autres attributs de l'abonné -->
    </tr>
<%
    }
} else {
    // Gérer le cas où l'attribut n'est pas une liste d'Abonnee
    // Peut-être afficher un message d'erreur ou faire une action appropriée
}
%>
        <a href="Examplaireemprunt">Charger les Examplaire empruntee</a>
            
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>