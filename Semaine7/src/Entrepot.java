import java.util.HashMap;
import java.util.LinkedList;


/**
 * 
 * @author 											-------> METTEZ ICI VOS NOM ET PRENOM
 * 
 * 
 */
public class Entrepot {
	private int nombreHangarsOccupes = 0;
	private HashMap<Integer, Societe> mapSocietes;
	private Hangar[] tableauHangars;

	/**
	 * construit un entrepot contenant nombreHangars
	 * @param nombreHangars le nombre d'hangars
	 * @throws IllegalArgumentException si le nombre d'hangars est negatif ou nul
	 */
	public Entrepot(int nombreHangars) {
		if (nombreHangars < 1)
			throw new IllegalArgumentException();

		mapSocietes = new HashMap<>(666);
		tableauHangars = new Hangar[nombreHangars];

		for (int i = 0; i < nombreHangars; i++) {
			tableauHangars[i] = new Hangar(i);
		}
	}
	
	/**
	 * attribue un hangar a la societe passee en parametre
	 * Si l'attribution a pu se faire : 
	 * la societe est enregistree comme presente (si pas encore presente)
	 * le hangar lui est ajoute
	 * @param numeroSociete
	 * @param nomSociete
	 * @return le numero du hangar attribue ou -1 s'il n'y en a plus de libre
	 */
	public int attribuerHangar(int numeroSociete, String nomSociete) {
		if (tableauHangars.length == nombreHangarsOccupes) {
			return -1;
		}

		if (!mapSocietes.containsKey(numeroSociete)) {
			Societe temp = new Societe(numeroSociete, nomSociete);
			mapSocietes.put(numeroSociete, temp);
		}


		for (int i = mapSocietes.get(numeroSociete).hashCode() % tableauHangars.length; i < tableauHangars.length; i++) {
			if (tableauHangars[i].getSociete() == null) {
				tableauHangars[i].setSociete(mapSocietes.get(numeroSociete));
				mapSocietes.get(numeroSociete).ajouterHangar(i);
				nombreHangarsOccupes ++;
				return i;
			}
		}

		for (int j = 0; j < mapSocietes.get(numeroSociete).hashCode() % tableauHangars.length; j++) {
			if (tableauHangars[j].getSociete() == null) {
				tableauHangars[j].setSociete(mapSocietes.get(numeroSociete));
				mapSocietes.get(numeroSociete).ajouterHangar(j);
				nombreHangarsOccupes ++;
				return j;
			}
		}
		return -1;
	}
	
	

	
	/**
	 * renvoie la societe dont le numero est passe en parametre
	 * @param numeroSociete le numero de la societe
	 * @return la societe recherchee ou null si aucune societe presente ne possede ce numero
	 */
	public Societe getSociete(int numeroSociete){
		if (!mapSocietes.containsKey(numeroSociete))
			return null;

		return mapSocietes.get(numeroSociete);
	}

	/**
	 *
	 * */
	public boolean libererHangar(int numeroHangar) {
		if (tableauHangars[numeroHangar].getSociete() == null) {
			return false;
		}

		Societe societe = tableauHangars[numeroHangar].getSociete();
		tableauHangars[numeroHangar] = null;
		mapSocietes.get(societe.getNumeroSociete()).libererHangar(numeroHangar);

		if (societe.lesHangars().equals("[]")) {
			mapSocietes.remove(societe.getNumeroSociete());
		}
		return true;
	}
}
