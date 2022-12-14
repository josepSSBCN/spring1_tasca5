package PersonPackage;

import java.io.Serializable;
import java.util.Objects;

public class Persona implements Serializable {
	// ATRIBUTS
	private String nom;
	private String cognom;
	private String dni;

	// CONSTRUCTORA
	public Persona(String nom, String cognom, String dni) {
		this.nom = nom;
		this.cognom = cognom;
		this.dni = dni;
	}

	// GETTERS & SETTERS
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	
	// HASCODE & OVERRIDE
	@Override
	public int hashCode() {
		return Objects.hash(cognom, dni, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(cognom, other.cognom) && Objects.equals(dni, other.dni) && Objects.equals(nom, other.nom);
	}
	
	
}
