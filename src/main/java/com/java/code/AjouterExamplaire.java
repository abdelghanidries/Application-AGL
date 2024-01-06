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

/**
 * Servlet implementation class AjouterExamplaire
 */
@WebServlet("/Examplaire")
public class AjouterExamplaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// Méthode pour récupérer tous les Examplaire restituée depuis la base de données
    private List<Examplaire> getAllExamplairerestituée() {
        List<Examplaire> ExamplairerestList = new ArrayList<>();
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM examplaire where etat= 'restitué'");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Créer un objet Ouvrage et récupérer les données de la base de données
                Examplaire examplairerest = new Examplaire(
                		 rs.getInt("idexamplaire"),
                    rs.getString("isbn"),
                    rs.getInt("rayon"),
                    rs.getString("etat")
                    
                );
                
                ExamplairerestList.add(examplairerest);
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
        return ExamplairerestList;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Examplaire> examplaireRestList = getAllExamplairerestituée();

	    
	  
	        request.setAttribute("ExamplaireRes", examplaireRestList);
	        RequestDispatcher dispatcherRest = request.getRequestDispatcher("listExamplaireRestituée.jsp");
	        dispatcherRest.forward(request, response);
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid = request.getParameter("id");
		String oisbn = request.getParameter("isbn");
        String orayon = request.getParameter("rayon");
        
        Integer id =null;
        
        if (oid != null && !oid.isEmpty()) {
            try {
                id = Integer.parseInt(oid);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Gérer l'exception si la chaîne n'est pas un entier valide
            }
        } else {
            // Gérer le cas où la chaîne est vide ou nulle
        }
       Integer rayon =null;
        
        if (orayon != null && !orayon.isEmpty()) {
            try {
            	rayon = Integer.parseInt(orayon);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Gérer l'exception si la chaîne n'est pas un entier valide
            }
        } else {
            // Gérer le cas où la chaîne est vide ou nulle
        }
        // Récupérer la chaîne de date de la requête
        Connection con = null;

        try {
        	 Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
            // Vérifier la quantité d'exemplaires déjà présents pour cet ISBN dans la table examplaire
            PreparedStatement pstExemplaireCount = con.prepareStatement("SELECT COUNT(*) as exemplaire_count FROM examplaire WHERE isbn = ?");
            pstExemplaireCount.setString(1, oisbn);
            ResultSet rsExemplaireCount = pstExemplaireCount.executeQuery();
            
            int exemplaireCount = 0;
            if (rsExemplaireCount.next()) {
                exemplaireCount = rsExemplaireCount.getInt("exemplaire_count");
            }

            // Récupérer la quantité d'ouvrage disponible dans la table ouvrage
            PreparedStatement pstOuvrageQuantity = con.prepareStatement("SELECT quantite FROM ouvrage WHERE isbn = ?");
            pstOuvrageQuantity.setString(1, oisbn);
            ResultSet rsOuvrageQuantity = pstOuvrageQuantity.executeQuery();
            
            int ouvrageQuantity = 0;
            if (rsOuvrageQuantity.next()) {
                ouvrageQuantity = rsOuvrageQuantity.getInt("quantite");
            }

            // Comparer les quantités pour voir si le nombre d'exemplaires atteint ou dépasse la quantité d'ouvrage disponible
            if (exemplaireCount >= ouvrageQuantity) {
                // Le nombre d'exemplaires atteint ou dépasse la quantité d'ouvrage disponible
                request.setAttribute("status", "failed");
                request.setAttribute("message", "Le nombre d'exemplaires atteint ou dépasse la quantité d'ouvrage disponible.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Examplaire.jsp");
                dispatcher.forward(request, response);
            }else {
        
        
        
       
        try {
        	
            Examplaire examplaire = new Examplaire(id,oisbn, rayon, "restitué");

            // Utilisation de la méthode setRole pour définir le rôle
           

            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
                PreparedStatement pst = con.prepareStatement("insert into examplaire(idexamplaire,isbn, rayon, etat) values(?, ?, ?, ?)");
                pst.setInt(1, examplaire.getId());
                pst.setString(2, examplaire.getIsbn());
                pst.setInt(3, examplaire.getRayon());
                pst.setString(4, examplaire.getEtat());
                
                
            

                int rowCount = pst.executeUpdate();
               
                if (rowCount > 0) {
                    request.setAttribute("status", "success");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Examplaire.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("status", "failed");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Examplaire.jsp");
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
        }}} catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
