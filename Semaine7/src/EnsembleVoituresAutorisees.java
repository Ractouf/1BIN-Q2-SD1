import java.util.HashMap;
import java.util.Objects;


public class EnsembleVoituresAutorisees {
	private HashMap<String, Proprietaire> ensemble;
	/**
	 * construit un ensemble vide
	 */
	public EnsembleVoituresAutorisees(){
		ensemble = new HashMap(500);
	}

	/**
	 * ajoute une voiture a condition que celle-ci ne soit pas deja presente
	 * @param plaque la plaque de la voiture a ajouter
	 * @param proprietaire le proprietaire de la voiture a ajouter
	 * @return true si la voiture n'etait pas encore presente, false sinon
	 */
	public boolean ajouterVoiture(String plaque, Proprietaire proprietaire){
		if (ensemble.containsKey(plaque))
			return false;

		ensemble.put(plaque, proprietaire);
		return true;
	}


	/**
	 * retire une voiture a condition que celle-ci soit presente
	 * @param plaque la plaque de la voiture a ajouter
	 * @return true si la voiture etait presente, false sinon
	 */
	public boolean retirerVoiture(String plaque){
		if (ensemble.containsKey(plaque)) {
			ensemble.remove(plaque);
			return true;
		}

		return false;
	}


	
	/**
	 * verifie si la voiture est autorisee car presente dans l'ensemble
	 * @param plaque la plaque de la voiture a verifier
	 * @return true si la voiture est presente dans l'ensemble, false sinon
	 */
	public boolean voitureAutorisee(String plaque){
		return ensemble.containsKey(plaque);
	}
	
	/**
	 * renvoie le proprietaire de la voiture
	 * @param plaque la plaque de la voiture recherchee
	 * @return le proprietaire ou null si la plaque n'est pas dans l'ensemble
	 */
	public Proprietaire donnerProprietaire(String plaque){
		return ensemble.get(plaque);
	}
	
	public String toString(){
		return ensemble.toString();
	}
}
