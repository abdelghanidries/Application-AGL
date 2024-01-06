package com.java.code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Examplaire {
	private Integer id;
	private String isbn;
	private Integer rayon;
	private String etat;
	public Examplaire(Integer id, String isbn, Integer rayon, String etat) {
		if (id != null && isbn != null && rayon != null && etat != null 
	            && !isbn.isEmpty() && !etat.isEmpty()) {
		this.id = id;
		setIsbn(isbn);
		this.rayon = rayon;
		this.etat = etat;}
		else {
            throw new IllegalArgumentException("Tous les attributs doivent être renseignés.");
        }
	}
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		if (id != null) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("L'ID ne peut pas être nul.");
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
	public Integer getRayon() {
		return rayon;
	}
	public void setRayon(Integer rayon) {
		if (rayon != null) {
            this.rayon = rayon;
        } else {
            throw new IllegalArgumentException("Le rayon ne peut pas être nul.");
        }
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
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
    	Examplaire exa = new Examplaire(40,"978-0-306-40455-7",null,"restitué");
    	System.out.println(exa.etat);
    
    }
}
