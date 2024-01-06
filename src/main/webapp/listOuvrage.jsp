<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.java.code.Ouvrage" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liste ouvrage</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>

<table class="responsive-table striped centered">
        <thead>
          <tr>
              <th>ISBN</th>
              <th>Titre</th>
              <th>auteurs</th>
              <th>date publication</th>
              <th>nombre examplaire</th>
              <th>supprimer ouvrage</th>
              <th>mise a jours ouvrage</th>
          </tr>
        </thead>

        <tbody>
           <%
Object OuvragesObj = request.getAttribute("ouvrages");

if (OuvragesObj instanceof List) {
    List<Ouvrage> ouvrages = (List<Ouvrage>) OuvragesObj;

    for (Ouvrage ouvragess : ouvrages) {
%>
    <tr>
        <td><%= ouvragess.getIsbn() %></td>
        <td><%= ouvragess.getTitre() %></td>
        <td><%= ouvragess.getAuteurs() %></td>
         <td><%= ouvragess.getDatepub() %></td>
       
        <td><%= ouvragess.getQuantite() %></td>
        <td><a href="Ouvrage?deleteouvrageIsbn=<%= ouvragess.getIsbn() %>">Supprimer un ouvrage</a></td>
      <td><form action="updateouvrage.jsp" method="post">
    <input type="hidden" name="isbn" value="<%= ouvragess.getIsbn() %>">
    <input type="hidden" name="titre" value="<%= ouvragess.getTitre() %>">
    <input type="hidden" name="auteurs" value="<%= ouvragess.getAuteurs() %>">
    <input type="hidden" name="datepub" value="<%= ouvragess.getDatepub() %>">
    <input type="hidden" name="quantite" value="<%= ouvragess.getQuantite() %>">
    <button type="submit">Update</button>
</form></td>

        
        <!-- Ajoutez d'autres attributs de l'abonné -->
    </tr>
<%
    }
} else {
    // Gérer le cas où l'attribut n'est pas une liste d'Abonnee
    // Peut-être afficher un message d'erreur ou faire une action appropriée
}
%>
        <a href="Ouvrage">Charger les Ouvrages</a>
            
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>