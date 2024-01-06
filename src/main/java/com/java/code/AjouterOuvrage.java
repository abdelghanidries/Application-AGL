package com.java.code;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class AjouterOuvrage
 */
@WebServlet("/Ouvrage")
public class AjouterOuvrage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// Méthode pour récupérer tous les Ouvrages depuis la base de données
    private List<Ouvrage> getAllOuvrage() {
        List<Ouvrage> ouvrageList = new ArrayList<>();
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM ouvrage");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Créer un objet Ouvrage et récupérer les données de la base de données
                Ouvrage ouvrage = new Ouvrage(
                    rs.getString("isbn"),
                    rs.getString("titre"),
                    rs.getString("auteurs"),
                    rs.getString("datepub"),
                    rs.getInt("quantite")
                );
                
                ouvrageList.add(ouvrage);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Fermeture de la connexion
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ouvrageList;
    }
	 private void deleteOuvrage(String ouvrageIsbn) {
		    Connection con = null;

		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
		        PreparedStatement pst = con.prepareStatement("DELETE FROM ouvrage WHERE isbn = ?");
		        pst.setString(1, ouvrageIsbn);
		        pst.executeUpdate();
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		    } finally {
		        if (con != null) {
		            try {
		                con.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 List<Ouvrage> ouvrageList = getAllOuvrage();
		 
      
	        // Transmettre la liste des ouvrages à la page JSP
		  request.setAttribute("ouvrages", ouvrageList);
	        
		 String deleteouvrageIsbn = request.getParameter("deleteouvrageIsbn");
		    if (deleteouvrageIsbn != null && !deleteouvrageIsbn.isEmpty()) {
		    	
		    	deleteOuvrage(deleteouvrageIsbn);
		        response.sendRedirect("Ouvrage");
		        return;
		    }
		  
	        RequestDispatcher dispatchers = request.getRequestDispatcher("listOuvrage.jsp");
	        dispatchers.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oisbn = request.getParameter("isbn");
        String otitre = request.getParameter("titre");
        String oauteurs = request.getParameter("auteurs");
        String odatepub = request.getParameter("datepub");
        String oquantite = request.getParameter("quantite");
        Integer quantite = null;

        if (oquantite != null && !oquantite.isEmpty()) {
            try {
                quantite = Integer.parseInt(oquantite);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Gérer l'exception si la chaîne n'est pas un entier valide
            }
        } else {
            // Gérer le cas où la chaîne est vide ou nulle
        }

        // Récupérer la chaîne de date de la requête

        
        
        
       
        try {
        	
            Ouvrage ouvrage = new Ouvrage(oisbn, otitre, oauteurs, odatepub, quantite);

            // Utilisation de la méthode setRole pour définir le rôle
           

            Connection con = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
                PreparedStatement pst = con.prepareStatement("insert into ouvrage(isbn, titre, auteurs, datepub, quantite) values(?, ?, ?, ?, ?)");
                pst.setString(1, ouvrage.getIsbn());
                pst.setString(2, ouvrage.getTitre());
                pst.setString(3, ouvrage.getAuteurs());
                pst.setDate(4, ouvrage.getDatepub());
                pst.setInt(5, ouvrage.getQuantite());
                
            

                int rowCount = pst.executeUpdate();
               
                if (rowCount > 0) {
                    request.setAttribute("status", "success");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Ouvrage.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("status", "failed");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Ouvrage.jsp");
                    dispatcher.forward(request, response);
                }
               

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
	}

}
