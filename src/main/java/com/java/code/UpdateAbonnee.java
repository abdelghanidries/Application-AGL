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
import java.sql.SQLException;

/**
 * Servlet implementation class UpdateAbonnee
 */
@WebServlet("/UpdateAbonnee")
public class UpdateAbonnee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mnom = request.getParameter("nom");
        String mprenom = request.getParameter("prenom");
        String mmatricule = request.getParameter("matricule");
        String memail = request.getParameter("email");
        String mmdp = request.getParameter("mdp");
        String mroleValue = request.getParameter("role");
        Abonnee abonnee = new Abonnee(mnom, mprenom, mmatricule, memail, mmdp);
        if (mroleValue != null && !mroleValue.isEmpty()) {
            abonnee.setRole(mroleValue);
        }
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
            PreparedStatement pst = con.prepareStatement("UPDATE utilisateurs SET nom = ?,prenom = ?, matricule = ?, motpass = ?, role = ?, email = ? WHERE matricule = ?");
            
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
            pst.setString(7, abonnee.getMatricule());
            
           

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                request.setAttribute("status", "success");
                RequestDispatcher dispatcher = request.getRequestDispatcher("listAbonnee.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("status", "failed");
                RequestDispatcher dispatcher = request.getRequestDispatcher("listAbonnee.jsp");
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

	}

}
