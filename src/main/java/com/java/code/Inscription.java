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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
    private static final long serialVersionUID = 1L;

 // Méthode pour récupérer tous les abonnés depuis la base de données
    private List<Abonnee> getAllAbonnes() {
        List<Abonnee> abonneeList = new ArrayList<>();
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM utilisateurs");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Créer un objet Abonnee et récupérer les données de la base de données
                Abonnee abonnee = new Abonnee(
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("matricule"),
                    rs.getString("email"),
                    rs.getString("motpass")
                );
                
                // Définir le rôle de l'abonné
                abonnee.setRole(rs.getString("role")); // À adapter selon votre implémentation

                abonneeList.add(abonnee);
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
        return abonneeList;
    }
    private void deleteAbonnee(String abonneeMatricule) {
	    Connection con = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
	        PreparedStatement pst = con.prepareStatement("DELETE FROM  utilisateurs WHERE matricule = ?");
	        pst.setString(1, abonneeMatricule);
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
        List<Abonnee> abonneeList = getAllAbonnes();

        // Transmettre la liste des abonnés à la page JSP
        request.setAttribute("abonnes", abonneeList);
        String deleteabonneMatricule = request.getParameter("deleteabonneMatricule");
	    if (deleteabonneMatricule != null && !deleteabonneMatricule.isEmpty()) {
	    	
	    	deleteAbonnee(deleteabonneMatricule);
	        response.sendRedirect("Inscription");
	        return;
	    }
        RequestDispatcher dispatcher = request.getRequestDispatcher("listAbonnee.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
        
        String unom = request.getParameter("nom");
        String uprenom = request.getParameter("prenom");
        String umatricule = request.getParameter("matricule");
        String uemail = request.getParameter("email");
        String umdp = request.getParameter("mdp");
        String roleValue = request.getParameter("role");
        RequestDispatcher dispatchers = null;
        if(unom == null || unom.equals("")) {
        	request.setAttribute("nom", "veilluez rentrez votre nom");
        	dispatchers = request.getRequestDispatcher("inscription.jsp");
        	dispatchers.forward(request, response);
        	
        }
        if(uprenom == null || uprenom.equals("")) {
        	request.setAttribute("prenom", "veilluez rentrez votre prenom");
        	dispatchers = request.getRequestDispatcher("inscription.jsp");
        	dispatchers.forward(request, response);
        }
        if(umatricule == null || umatricule.equals("")) {
        	request.setAttribute("matricule", "veilluez rentrez votre matricule");
        	dispatchers = request.getRequestDispatcher("inscription.jsp");
        	dispatchers.forward(request, response);
        }
        if(uemail == null || uemail.equals("")) {
        	request.setAttribute("email", "veilluez rentrez votre email");
        	dispatchers = request.getRequestDispatcher("inscription.jsp");
        	dispatchers.forward(request, response);
        }
        if(umdp == null || umdp.equals("")) {
        	request.setAttribute("mdp", "veilluez rentrez votre mot de passe");
        	dispatchers = request.getRequestDispatcher("inscription.jsp");
        	dispatchers.forward(request, response);
        }


        try {
            Abonnee abonnee = new Abonnee(unom, uprenom, umatricule, uemail, umdp);

            // Utilisation de la méthode setRole pour définir le rôle
            if (roleValue != null && !roleValue.isEmpty()) {
                abonnee.setRole(roleValue);
            }

            Connection con = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
                PreparedStatement pst = con.prepareStatement("insert into utilisateurs(nom, prenom, matricule,motpass, role, email) values(?, ?, ?, ?, ?, ?)");
                pst.setString(1, abonnee.getNom());
                pst.setString(2, abonnee.getPrenom());
                pst.setString(3, abonnee.getMatricule());
                pst.setString(4, abonnee.getMotpass());
                
                // Gestion du rôle
                if (abonnee.getRole() != null) {
                    pst.setString(5, abonnee.getRole().name());
                } else {
                    pst.setString(5, null);
                }
                
                pst.setString(6, abonnee.getEmail());

                int rowCount = pst.executeUpdate();
               
                if (rowCount > 0) {
                    request.setAttribute("status", "success");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("status", "failed");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
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
