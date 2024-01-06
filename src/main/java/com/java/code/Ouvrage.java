package com.java.code;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ouvrage {
     private String isbn;
     private String titre;
     private String auteurs;
     private Date datepub;
     private Integer quantite;
	public Ouvrage(String isbn, String titre, String auteurs, String datepub, Integer quantite) {
		if (isbn != null && titre != null && !titre.equals("") && auteurs != null && !auteurs.equals("") && datepub != null && quantite != null) {
			setIsbn(isbn);
		this.titre = titre;
		this.auteurs = auteurs;
		 try {
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             java.util.Date parsedDate = dateFormat.parse(datepub);
             this.datepub = new java.sql.Date(parsedDate.getTime());
         } catch (ParseException e) {
             throw new IllegalArgumentException("Format de date invalide.");
         }
     
		this.quantite = quantite;  } else {
            throw new IllegalArgumentException("Les paramètres ne peuvent pas être nuls.");
        }
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
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteurs() {
		return auteurs;
	}
	public void setAuteurs(String auteurs) {
		this.auteurs = auteurs;
	}
	public Date getDatepub() {
		return datepub;
	}
	public void setDatepub(Date datepub) {
		this.datepub = datepub;
	}
	public Integer getQuantite() {
		return quantite;
	}
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
     
	// Méthode pour valider l'ISBN
    private boolean validateISBN(String isbn) {
        // Expression régulière pour valider l'ISBN
        String isbnPattern = "^(?:ISBN(?:-1[03])?:?\\s?)?(?=[-0-9X]{17}$|[-0-9X]{13}$)"
                + "(?:97[89][-\\s]?)?[0-9]{1,5}[-\\s]?[0-9]+[-\\s]?[0-9]+[-\\s]?[0-9X]$";

        Pattern pattern = Pattern.compile(isbnPattern);
        Matcher matcher = pattern.matcher(isbn);

        return matcher.matches();
    }
    public static void main(String[] args) {
    	Ouvrage ovr = new Ouvrage("978-3-16-148410-0","", "","1997-05-26",40);
    	System.out.println(ovr.auteurs);
    }
     
}
