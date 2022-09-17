/**
 * Cette classe retient dans une liste les etudiants inscrits au bal
 * La liste contient d abord les hommes, ensuite les femmes
 * Dans chacune des sous-listes, l'ordre suit l'ordre des inscriptions
 *
 * @author
 *
 */
public class Bal1 {

	private NoeudEtudiant tete;
	private NoeudEtudiant derM;
	private NoeudEtudiant derF;

	/**
	 * construit un bal "vide", la liste des etudiants est vide
	 */
	public Bal1(){
		tete = null;
		derM = null;
		derF = null;
	}

	public String toString(){
		String aRenvoyer = "";
		NoeudEtudiant baladeur = tete;
		while(baladeur!=null){
			aRenvoyer+=" "+baladeur.etudiant.getPrenom();
			baladeur = baladeur.suivant;
		}
		return aRenvoyer;
	}

	/**
	 * ajoute l etudiant dans la liste en tenant compte de l'ordre prevu
	 * @param etudiant l etudiant a ajouter
	 * @throws IllegalArgumentException si l etudiant est null
	 */
	public void ajouterEtudiant(Etudiant etudiant){
		if (etudiant == null)
			throw new IllegalArgumentException("etudiant null");

		NoeudEtudiant nouveauNoeud = new NoeudEtudiant(etudiant);
		NoeudEtudiant temp;

		if (etudiant.getSexe() == 'M') {
			if (tete == null)
				tete = nouveauNoeud;

			else if (derF == null)
				derM.suivant = nouveauNoeud;

			else if (derM == null) {
				nouveauNoeud.suivant = tete;
				tete = nouveauNoeud;
			} else {
				temp = derM.suivant;
				derM.suivant = nouveauNoeud;
				nouveauNoeud.suivant = temp;
			}

			derM = nouveauNoeud;

		} else if (etudiant.getSexe() == 'F') {
			if (tete == null)
				tete = nouveauNoeud;

			else if (derF == null)
				derM.suivant = nouveauNoeud;

			else
				derF.suivant = nouveauNoeud;

			derF = nouveauNoeud;
		}

	}

	// classe interne
	private class NoeudEtudiant{

		private Etudiant etudiant;
		private NoeudEtudiant suivant;

		public NoeudEtudiant(Etudiant etudiant){
			this.etudiant = etudiant;
			this.suivant = null;
		}

		public NoeudEtudiant(Etudiant etudiant, NoeudEtudiant suivant){
			this.etudiant = etudiant;
			this.suivant = suivant;
		}

	}
}
