package com.java.code;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rapport {
	private String matricule;
	private String isbn;
	private Integer id;
	private Date dateemprunt;
	private Date daterestitution;
	private String prolonger;
	
	
	
	
	public Rapport(String matricule, String isbn, Integer id, String dateemprunt, String daterestitution ,String prolonger ) {
	if(matricule != null && !matricule.equals("")&& isbn != null && !isbn.isEmpty() && id != null && dateemprunt != null && daterestitution != null
			&& isbn != null && !isbn.isEmpty()) {
		this.matricule = matricule;
	     setIsbn(isbn);   
		this.id = id;
		try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateemprunt);
            this.dateemprunt = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Format de date invalide.");
        }
		try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(daterestitution);
            this.daterestitution = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Format de date invalide.");
        }
		this.prolonger = prolonger;}else {
            throw new IllegalArgumentException("Les paramètres ne peuvent pas être nuls.");
        }
		
		
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		if (validateISBN(isbn)) {
            this.isbn = isbn;
        } else {
            throw new IllegalArgumentException("Format ISBN invalide.");
        }
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDateemprunt() {
		return dateemprunt;
	}
	public void setDateemprunt(Date dateemprunt) {
		this.dateemprunt = dateemprunt;
	}
	public Date getDaterestitution() {
		return daterestitution;
	}
	public void setDaterestitution(Date daterestitution) {
		this.daterestitution = daterestitution;
	}

	public String getProlonger() {
		return prolonger;
	}
	public void setProlonger(String prolonger) {
		this.prolonger = prolonger;
	}

	private boolean validateISBN(String isbn) {
        // Expression régulière pour valider l'ISBN
        String isbnPattern = "^(?:ISBN(?:-1[03])?:?\\s?)?(?=[-0-9X]{17}$|[-0-9X]{13}$)"
                + "(?:97[89][-\\s]?)?[0-9]{1,5}[-\\s]?[0-9]+[-\\s]?[0-9]+[-\\s]?[0-9X]$";

        Pattern pattern = Pattern.compile(isbnPattern);
        Matcher matcher = pattern.matcher(isbn);

        return matcher.matches();
    }
    public static void main(String[] args) {
    	Rapport rapport = new Rapport("175997766","978-3-16-148410-0", 2,"1997-05-26","1996-05-26","oui");
    	System.out.println(rapport.prolonger);
    }
}
