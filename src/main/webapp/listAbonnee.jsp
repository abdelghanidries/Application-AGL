<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.java.code.Abonnee" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Abonnee</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>


<table class="responsive-table striped centered">
    <thead>
        <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Matricule</th>
            <th>Mot de passe</th>
            <th>Role</th>
            <th>Email</th>
             <th>Supprimer abonnee</th>
             <th>mise a jours des information d'un abonnee</th>
            <!-- Ajoutez d'autres colonnes pour les attributs de l'abonné -->
        </tr>
    </thead>
    <tbody>
    
 <%
Object abonnesObj = request.getAttribute("abonnes");

if (abonnesObj instanceof List) {
    List<Abonnee> abonnes = (List<Abonnee>) abonnesObj;

    for (Abonnee abonnee : abonnes) {
%>
    <tr>
        <td><%= abonnee.getNom() %></td>
        <td><%= abonnee.getPrenom() %></td>
        <td><%= abonnee.getMatricule() %></td>
         <td><%= abonnee.getMotpass() %></td>
        <td><%= (abonnee.getRole() != null) ? abonnee.getRole().name() : "B/G" %></td>
        <td><%= abonnee.getEmail() %></td>
        <td><a href="Inscription?deleteabonneMatricule=<%= abonnee.getMatricule() %>">Supprimer un Abonnee</a></td>
        <td><a></a><form action="updateabonnee.jsp" method="post">
    <input type="hidden" name="nom" value="<%= abonnee.getNom() %>">
    <input type="hidden" name="prenom" value="<%= abonnee.getPrenom() %>">
    <input type="hidden" name="matricule" value="<%= abonnee.getMatricule() %>">
    <input type="hidden" name="motpass" value="<%= abonnee.getMotpass() %>">
    <input type="hidden" name="role" value="<%= (abonnee.getRole() != null) ? abonnee.getRole().name() : "B/G" %> ">
     <input type="hidden" name="email" value="<%= abonnee.getEmail() %>">
    <button class="waves-effect waves-light btn" type="submit">Update</button>
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
<a href="Inscription">Charger les abonnés</a>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


</body>
</html>