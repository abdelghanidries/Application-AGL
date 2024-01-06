<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <object data="${cheminFichier}" type="application/pdf" width="800px" height="600px">
        <p>Votre navigateur ne supporte pas l'affichage de PDF. <a href="${cheminFichier}">Télécharger le fichier PDF</a>.</p>
    </object>
    
</body>
</html>