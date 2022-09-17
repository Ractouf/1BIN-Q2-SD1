
public class DrapeauBelge {
	
	private NoeudCouleur premierNoir;	
	private NoeudCouleur dernierJaune;
	
	/**
	 * construit une chaine contenant 3 noeuds avec les caracteres 'n', 'j' et 'r' (dans cet ordre)
	 */
	public DrapeauBelge() {
		premierNoir = new NoeudCouleur('n', dernierJaune = new NoeudCouleur('j', new NoeudCouleur('r')));
	}

	// A NE PAS MODIFIER. VA SERVIR POUR LES TESTS
	public String toString(){
		String drapeau="";
		NoeudCouleur baladeur = premierNoir;
		while(baladeur!=null){
			drapeau+=baladeur.couleur;
			baladeur = baladeur.suivant;
		}
		return drapeau;
	}
	
	/**
	 * ajoute un noeud avec la couleur passee en parametre dans la chaine
	 * La chaine doit respecter les couleurs du  drapeau belge : noir/jaune/rouge
	 * @param couleur un caractere representant une couleur du drapeau belge : 'n', 'j' ou 'r'
	 * @throws IllegalArgumentException si le caractere ne correspond pas a un des 3 caracteres : 'n', 'j' ou 'r'
	 */
	public void ajouter(char couleur){
		NoeudCouleur nouveauNoeud = new NoeudCouleur(couleur);
		NoeudCouleur temp;

		if (couleur != 'n' && couleur != 'j' && couleur != 'r')
			throw new IllegalArgumentException();

		if (couleur == 'n') {
			nouveauNoeud.suivant = premierNoir;
			premierNoir = nouveauNoeud;
		} else {
			temp = dernierJaune.suivant;
			dernierJaune.suivant = nouveauNoeud;
			nouveauNoeud.suivant = temp;
			if (couleur == 'j')
				dernierJaune = nouveauNoeud;
		}
	}
	
	private class NoeudCouleur{
		
		private char couleur;
		private NoeudCouleur suivant;
		
		private NoeudCouleur(char couleur){
			this.couleur = couleur;
			this.suivant = null;
		}
		
		private NoeudCouleur(char couleur, NoeudCouleur suivant){
			this.couleur = couleur;
			this.suivant = suivant;
		}

	}
}
