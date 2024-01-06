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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class RechercherExamplaire
 */
@WebServlet("/SearchServlet")
public class RechercherExamplaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public List<Examplaire> searchByISBN(String searchISBN) {
	    List<Examplaire> examplaireList = new ArrayList<>();
	    Connection con = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
	        String query = "SELECT * FROM examplaire WHERE isbn LIKE ?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, "%" + searchISBN + "%");
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("idexamplaire");
	            String isbn = rs.getString("isbn");
	            int rayon = rs.getInt("rayon");
	            String etat = rs.getString("etat");

	            Examplaire exmaplire = new Examplaire(id, isbn, rayon,etat);
	            examplaireList.add(exmaplire);
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
	    return examplaireList;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RechercherExamplaire examplaireDAO = new RechercherExamplaire();
		    String searchISBN = request.getParameter("isbn");

		    List<Examplaire> searchResults = examplaireDAO .searchByISBN(searchISBN);
		    
		    request.setAttribute("searchResults", searchResults);
		    request.getRequestDispatcher("RechercherOuvrage.jsp").forward(request, response);
		    
		   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
