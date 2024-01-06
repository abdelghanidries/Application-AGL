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
 * Servlet implementation class ExamplaireEpmrunt
 */
@WebServlet("/Examplaireemprunt")
public class ExamplaireEpmrunt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 // Méthode pour récupérer tous les Examplaire empruntee depuis la base de données
    private List<Examplaire> getAllExamplairemprunt() {
        List<Examplaire> ExamplaireEmpruntList = new ArrayList<>();
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM examplaire where etat= 'emprunté'");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Créer un objet Ouvrage et récupérer les données de la base de données
                Examplaire examplaireemprut = new Examplaire(
                		 rs.getInt("idexamplaire"),
                    rs.getString("isbn"),
                    rs.getInt("rayon"),
                    rs.getString("etat")
                    
                );
                
                ExamplaireEmpruntList.add(examplaireemprut);
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
        return ExamplaireEmpruntList;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 List<Examplaire> ExamplaireempruntList = getAllExamplairemprunt();

	        // Transmettre la liste des ouvrages à la page JSP
	        request.setAttribute("Examplaireemprunt", ExamplaireempruntList);
	        RequestDispatcher dispatchers = request.getRequestDispatcher("listExamplaireEmprentée.jsp");
	        dispatchers.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String examplaireIdString = request.getParameter("examplaireId");

	        if (examplaireIdString != null && !examplaireIdString.isEmpty()) {
	            int examplaireId = Integer.parseInt(examplaireIdString);

	            // Appel à une méthode pour mettre à jour l'état de l'exemplaire
	            updateExamplaireState(examplaireId);

	            request.setAttribute("changer vers restitue", examplaireId);
	            RequestDispatcher dispatchers = request.getRequestDispatcher("listExamplaireRestituée.jsp");
		        dispatchers.forward(request, response);
	        } else {
	            // Gérer le cas où aucun identifiant d'exemplaire n'est fourni
	            // Peut-être renvoyer un message d'erreur ou rediriger vers une autre page
	            response.sendRedirect("listExamplaireEmprentée.jsp");
	        }
	}
	// Méthode pour mettre à jour l'état de l'exemplaire dans la base de données
    private void updateExamplaireState(int examplaireId) {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
            PreparedStatement pst = con.prepareStatement("UPDATE examplaire SET etat = 'restitué' WHERE idexamplaire = ?");
            pst.setInt(1, examplaireId);
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

}
