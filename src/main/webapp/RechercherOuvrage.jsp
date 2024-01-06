<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="com.java.code.Examplaire" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rechercheer Ouvrage</title>
</head>
<body>

</body> <h1>Résultats de la recherche</h1>

    <form action="SearchServlet" method="get">
        <input type="text" name="isbn" placeholder="Entrez un ISBN" />
        <input type="submit" value="Rechercher" />
    </form>
    
   <%  Object searchouvrageObj = request.getAttribute("searchResults");

if (searchouvrageObj instanceof List) {
    List<Examplaire> examplaires = (List<Examplaire>) searchouvrageObj;

    for (Examplaire exmplairess : examplaires) {%>

    	   <p>ID: <%= exmplairess.getId() %></p>
           <p>ISBN: <%= exmplairess.getIsbn() %></p>
           <p>Rayon: <%= exmplairess.getRayon() %></p>
           <hr/>
   <% } } else { %>
       <p>Aucun résultat trouvé.</p>
   <% } %>
</html>