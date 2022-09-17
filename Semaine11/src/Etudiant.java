import java.util.Comparator;

public class Etudiant implements Comparable<Etudiant> {
	private int numeroMatricule;
	private String nom;
	private String prenom;

	public Etudiant(int numeroMatricule, String nom, String prenom) {
		super();
		this.numeroMatricule = numeroMatricule;
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getNumeroMatricule() {
		return numeroMatricule;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}

	// A NE PAS MODIFIER : le numero de matricule est l'identifiant unique

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;

		return numeroMatricule == other.numeroMatricule;
	}

	@Override
	public int hashCode() {
		return numeroMatricule;
	}
	
	@Override
	public String toString() {
		return "Etudiant [numeroMatricule=" + numeroMatricule + ", nom=" + nom
				+ ", prenom=" + prenom + "]";
	}

	@Override
	public int compareTo(Etudiant o) {
		return numeroMatricule - o.numeroMatricule;
	}
}
