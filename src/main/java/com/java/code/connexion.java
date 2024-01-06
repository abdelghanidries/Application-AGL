package com.java.code;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class connexion
 */
@WebServlet("/said")
public class connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String umatricule = request.getParameter("matricule");
	    String upwd = request.getParameter("mdp");
	    
	    HttpSession session = request.getSession();
	    RequestDispatcher dispatcher = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia","root","dbPasswordqiven");
	        PreparedStatement pst = con.prepareStatement("select * from utilisateurs where matricule= ? and motpass = ?");
	        pst.setString(1, umatricule);
	        pst.setString(2, upwd);
	        
	        ResultSet rs = pst.executeQuery();
	        if(rs.next()) {
	            String userRole = rs.getString("role");
	            // Vérifier le rôle et rediriger en conséquence
	            if ("gestionnaire".equals(userRole)) {
	                session.setAttribute("name", rs.getString("nom"));
	                dispatcher = request.getRequestDispatcher("gestionnaire.jsp");
	            } else if ("etudiant_intern".equals(userRole) || "etudiant_extern".equals(userRole) || "enseignant".equals(userRole)) {
	                session.setAttribute("name", rs.getString("nom"));
	                dispatcher = request.getRequestDispatcher("abonnee.jsp");
	            } else if ("bibliothecaire".equals(userRole)) {
	                session.setAttribute("name", rs.getString("nom"));
	                dispatcher = request.getRequestDispatcher("bibliothecaire.jsp");
	            } else {
	                request.setAttribute("status", "failed");
	                dispatcher = request.getRequestDispatcher("connexion.jsp");
	            }
	        } else {
	            request.setAttribute("status", "failed");
	            dispatcher = request.getRequestDispatcher("connexion.jsp");
	        }
	        
	        dispatcher.forward(request, response);
	        
	    } catch (Exception e) {
	        // TODO: handle exception
	    }
	}

}
