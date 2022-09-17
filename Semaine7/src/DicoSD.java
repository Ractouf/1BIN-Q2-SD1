import java.util.HashMap;
import java.util.LinkedList;

public class DicoSD {
	private HashMap<String, LinkedList<String>> ensemble;

	public DicoSD() {
		ensemble = new HashMap<>(500);
	}

	/**
	 * ajout dans le dico une association sd-url si cette association n'est pas encore presente 
	 * @param sd une structure de donnees
	 * @param url une url vers un site internet
	 * @return true si cette association n'etait pas encore presente dans le dico, false sinon
	 */
	public boolean ajouter(String sd, String url){
		if (ensemble.containsKey(sd)) {
			if (ensemble.get(sd).contains(url))
				return false;

			ensemble.get(sd).add(url);
		} else {
			LinkedList<String> temp = new LinkedList<>();
			temp.add(url);
			ensemble.put(sd, temp);
		}
		return true;
	}
	
	/**
	 * verifie si la structure de donnees se trouve dans le dico
	 * cette structure de donnees doit posseder au moins une url!
	 * @param sd
	 * @return true si sd est present, false sinon
	 */
	public boolean contient(String sd) {
		return ensemble.containsKey(sd);
	}
	
	
	/**
	 * renvoie tous les urls associes a la structure de donnees passee en parametre
	 * @param sd
	 * @return une chaine de caracteres avec les urls selon le format : [urlPile1, urlPile2] ou [] si la structure de donnees n'existe pas
	 */
	public String lesURLs(String sd){
		if (!ensemble.containsKey(sd))
			return "[]";
		return ensemble.get(sd).toString();
	}
	
	/**
	 * supprime dans le dico l'association sd-url si celle-ci est presente 
	 * @param sd une structure de donnees
	 * @param url une url vers un site internet
	 * @return true si l'association etait presente dans le dico, false sinon
	 */
	public boolean supprimer(String sd, String url){
		if (ensemble.containsKey(sd) && ensemble.get(sd).contains(url)) {
			ensemble.get(sd).remove(url);
			if (ensemble.get(sd).size() == 0) {
				ensemble.remove(sd);
			}
			return true;
		}
		return false;
	}
		
}
