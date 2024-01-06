package com.java.code;

public class Abonnee {
	
	private String nom;
	private String prenom;
	private String matricule;
	private String email;
	private String motpass;
   
	public enum Role {
		enseignant,
		etudiant_intern,
		etudiant_extern
	}
	

	  public Abonnee(String nom, String prenom, String matricule, String email, String motpass) {
		  if (nom != null && !nom.equals("") && prenom != null && !prenom.equals("") && matricule != null 
				  && !matricule.equals("") && email != null && !email.equals("") 
				  && motpass != null && !motpass.equals("")) {
	            this.nom = nom;
	            this.prenom = prenom;
	            this.matricule = matricule;
	            this.email = email;
	            this.motpass = motpass;
	        } else {
	            throw new IllegalArgumentException("Les paramètres ne peuvent pas être nuls.");
	        }
	    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotpass() {
		return motpass;
	}

	public void setMotpass(String motpass) {
		this.motpass = motpass;
	}

	public Role getRole() {
		return role;
	}

	private Role role;
	
	// Méthode pour définir le rôle à partir d'une chaîne
    public void setRole(String roleString) {
        if (roleString != null && !roleString.isEmpty()) {
            switch (roleString) {
                case "enseignant":
                    this.role = Role.enseignant;
                    break;
                case "etudiant_intern":
                    this.role = Role.etudiant_intern;
                    break;
                case "etudiant_extern":
                    this.role = Role.etudiant_extern;
                    break;
                default:
                    // Gérer les autres cas si nécessaire
                    break;
            }
        }
    }
	
    public void setRole(Role role) {
        this.role = role;
    }
	
	
	
    public static void main(String[] args) {
    	Abonnee abn = new Abonnee("said","said","moh", "rt","der");
    	System.out.println("votre nom est "+abn.nom);
    }
	
}
