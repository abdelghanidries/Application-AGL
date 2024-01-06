package com.java.code;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class penalisation
 */
@WebServlet("/penalisation")
public class penalisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Code pour se connecter à la base de données
		   Connection con = null;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
			// Code pour récupérer toutes les lignes de la table rapport
			String querySelect = "SELECT matricule, idexamplaire,daterestitution  FROM rapport"; 
			try (PreparedStatement preparedStatement = con.prepareStatement(querySelect)) {
			    ResultSet resultSet = preparedStatement.executeQuery();
			    while (resultSet.next()) {
			       String matricule = resultSet.getString("matricule");
			        int idexamplaire = resultSet.getInt("idexamplaire");
			        LocalDate dateRestitutionEmprunt = resultSet.getDate("daterestitution").toLocalDate();

			        // Vérifier si la date de restitution est dépassée
			        LocalDate dateActuelle = LocalDate.now();
			        if (dateActuelle.isAfter(dateRestitutionEmprunt)) {
			            // Appliquer la pénalité ici (par exemple, calculer le montant de la pénalité)
			        	 // Calculer le nombre de jours de retard
			            long nbJoursRetard = ChronoUnit.DAYS.between(dateRestitutionEmprunt, dateActuelle);
			            long montantPenalite = nbJoursRetard * 2;
			         // Calculer la date de fin de la pénalisation
			            LocalDate dateFinPenalisation = dateRestitutionEmprunt.plusDays(montantPenalite);
			         // Formater la date au format yyyy-MM-dd
			            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			            String dateFinPenalisationFormatted = dateFinPenalisation.format(formatter);


			            // Enregistrer la pénalité dans votre système (base de données, etc.)
			            String queryInsert = "INSERT INTO penalisation (matricule, idexemplaire, datefinpenalisation) VALUES (?, ?, ?)";
			            try (PreparedStatement insertStatement = con.prepareStatement(queryInsert)) {
			                insertStatement.setString(1, matricule);
			                insertStatement.setInt(2, idexamplaire);
			                insertStatement.setString(3, dateFinPenalisationFormatted);
			                insertStatement.executeUpdate();
			                
			                
			              
			            } catch (SQLException e) {
			                // Gérer les erreurs d'insertion dans la table penalisation
			                e.printStackTrace();
			            }//
			            
			        }
			    }
			} catch (SQLException e) {
			    // Gérer les erreurs de récupération depuis la table rapport
			    e.printStackTrace();
			}

			// Fermeture de la connexion à la base de données
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
