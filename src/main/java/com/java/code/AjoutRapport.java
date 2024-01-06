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
 * Servlet implementation class AjoutRapport
 */
@WebServlet("/Rapport")
public class AjoutRapport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private List<Rapport> getAllRapport() {
	        List<Rapport> rapportList = new ArrayList<>();
	        Connection con = null;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
	            PreparedStatement pst = con.prepareStatement("SELECT * FROM rapport");
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	                // Créer un objet Ouvrage et récupérer les données de la base de données
	                Rapport rapport = new  Rapport(
	                	rs.getString("matricule"),
	                    rs.getString("isbn"),
	                    rs.getInt("idexamplaire"),
	                    rs.getString("dateemprunt"),
	                    rs.getString("daterestitution"),
	                    rs.getString("prolonger")
	                    
	                );
	                
	                rapportList.add(rapport);
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
	        return rapportList;
	    }
	 private void deleteRapport(String rapportId) {
		    Connection con = null;

		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
		        PreparedStatement pst = con.prepareStatement("DELETE FROM rapport WHERE idexamplaire = ?");
		        pst.setString(1, rapportId);
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
	 
	 private void updateExamplaireState(String examplaireId) {
	        Connection con = null;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
	            PreparedStatement pst = con.prepareStatement("UPDATE examplaire SET etat = 'restitué' WHERE idexamplaire = ?");
	            pst.setString(1, examplaireId);
	            pst.executeUpdate();
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
	    }
	 
	 private void prolongerDateRestitution(String rapportId) {
		    Connection con = null;

		    try {
		       
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");

		        // Vérifier si la prolongation est autorisée pour ce rapport
		        PreparedStatement checkProlongation = con.prepareStatement("SELECT prolonger FROM rapport WHERE idexamplaire = ?");
		        checkProlongation.setString(1, rapportId);
		        ResultSet prolongationResult = checkProlongation.executeQuery();
		        
		        if (prolongationResult.next()) {
		            String prolongerValue = prolongationResult.getString("prolonger");
		            if (prolongerValue.equals("non")) {
		                // Mettre à jour la date de restitution en ajoutant 7 jours à la date existante
		                PreparedStatement pst = con.prepareStatement("UPDATE rapport SET daterestitution = DATE_ADD(daterestitution, INTERVAL 7 DAY), prolonger = 'oui' WHERE idexamplaire = ?");
		                pst.setString(1, rapportId);
		                pst.executeUpdate();
		            } else {
		                // Afficher un message d'erreur si la prolongation n'est pas autorisée
		                System.out.println("La prolongation n'est pas autorisée pour ce rapport.");
		                // Ou vous pouvez jeter une exception ou gérer l'erreur selon votre logique métier
		            }
		        }
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





	 protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		}
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Rapport> rapportList = getAllRapport();
		  request.setAttribute("rapports", rapportList);
		 String deleteRapportId = request.getParameter("deleteRapportId");
		    if (deleteRapportId != null && !deleteRapportId.isEmpty()) {
		    	updateExamplaireState(deleteRapportId);
		    	deleteRapport(deleteRapportId);
		        response.sendRedirect("Rapport");
		        return;
		    }
		    String updateRapportId = request.getParameter("updateRapportId");
		    if (updateRapportId != null && !updateRapportId.isEmpty()) {
		    	prolongerDateRestitution(updateRapportId);
		    	
		        response.sendRedirect("Rapport");
		        return;
		    }
		    RequestDispatcher dispatcher = request.getRequestDispatcher("listRapport.jsp");
		    dispatcher.forward(request, response);
	        // Transmettre la liste des ouvrages à la page JSP
	      
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String omatricule = request.getParameter("matricule");
		String oisbn = request.getParameter("isbn");
        String oid = request.getParameter("id");
        String odateemprunt = request.getParameter("dateemprunt");
        String daterestitution = request.getParameter("daterestitution");
        
        Integer id = null;

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

        // Récupérer la chaîne de date de la requête

        
        
        
       
        try {
        	
            Rapport rapport = new Rapport(omatricule, oisbn, id, odateemprunt, daterestitution,"non");

            // Utilisation de la méthode setRole pour définir le rôle
           

            Connection con = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
                PreparedStatement pst = con.prepareStatement("insert into rapport(matricule, isbn, idexamplaire, dateemprunt, daterestitution ,prolonger) values(?, ?, ?, ?, ?,?)");
                pst.setString(1, rapport.getMatricule());
                pst.setString(2, rapport.getIsbn());
                pst.setInt(3, rapport.getId());
                pst.setDate(4, rapport.getDateemprunt());
                pst.setDate(5, rapport.getDaterestitution());
                pst.setString(6, rapport.getProlonger());
               
                
            
             // Vérifier l'état de l'exemplaire spécifique dont l'ID est soumis via le formulaire
                int rowCount = pst.executeUpdate();

                if (rowCount > 0) {
                    String selectQuery = "SELECT etat FROM examplaire WHERE idexamplaire = ? and isbn = ?";
                    try (PreparedStatement checkStmt = con.prepareStatement(selectQuery)) {
                        checkStmt.setInt(1, rapport.getId());
                        checkStmt.setString(2, rapport.getIsbn());
                        try (ResultSet resultSet = checkStmt.executeQuery()) {
                            if (resultSet.next()) {
                                String etatInitial = resultSet.getString("etat");
                                if (etatInitial.equals("restitué")) {
                                    String updateQuery = "UPDATE examplaire SET etat = 'emprunté' WHERE idexamplaire = ? and isbn = ? ";
                                    try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                                        updateStmt.setInt(1, rapport.getId());
                                        updateStmt.setString(2, rapport. getIsbn());
                                        updateStmt.executeUpdate();

                                        request.setAttribute("status", "Votre emprunt a réussi.");
                                        RequestDispatcher dispatcher = request.getRequestDispatcher("Rapport.jsp");
                                        dispatcher.forward(request, response);
                                    }
                                } else {
                                    request.setAttribute("status", "L'opération est invalide.");
                                }
                               
                            }
                        }
                    }
                } else {
                    // ...
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
