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
 * Servlet implementation class UpdateOuvrage
 */
@WebServlet("/UpdateOuvrage")
public class UpdateOuvrage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOuvrage() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		 String misbn = request.getParameter("isbn");
	        String mtitre = request.getParameter("titre");
	        String mauteurs = request.getParameter("auteurs");
	        String mdatepub = request.getParameter("datepub");
	        String mquantite = request.getParameter("quantite");

	        // Conversion de quantite en Integer si nécessaire (comme vous l'avez déjà fait dans votre code)
	        Integer quantiteInt = null;
	        if (mquantite != null && !mquantite.isEmpty()) {
	            try {
	                quantiteInt = Integer.parseInt(mquantite);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	                // Gérer l'exception si la chaîne n'est pas un entier valide
	            }
	        } else {
	            // Gérer le cas où la chaîne est vide ou nulle
	        }

	        // Ensuite, créez un nouvel objet Ouvrage avec les nouvelles valeurs
	        Ouvrage ouvrage = new Ouvrage(misbn, mtitre, mauteurs, mdatepub, quantiteInt);

	        Connection con = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque_bejaia", "root", "dbPasswordqiven");
	            PreparedStatement pst = con.prepareStatement("UPDATE ouvrage SET titre = ?, auteurs = ?, datepub = ?, quantite = ? WHERE isbn = ?");
	            
	            
	            pst.setString(1, ouvrage.getTitre());
	            pst.setString(2, ouvrage.getAuteurs());
	            pst.setDate(3, ouvrage.getDatepub());
	            pst.setInt(4, ouvrage.getQuantite());
	            pst.setString(5, ouvrage.getIsbn());
	            
	           

	            int rowCount = pst.executeUpdate();

	            if (rowCount > 0) {
	                request.setAttribute("status", "success");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("listOuvrage.jsp");
                    dispatcher.forward(request, response);
	            } else {
	                request.setAttribute("status", "failed");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("listOuvrage.jsp");
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


