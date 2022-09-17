import java.util.*;

/**
* @author Vandeputte François
*/
public class Brocante {
	
	private int phase = 1;
	
	// suivez l'implementation imposee dans l'enonce
	private Emplacement[] tableEmplacements;
	private HashMap<String, Integer> mapRiverains;
	private HashMap<String, Exposant> mapExposants;
	private ArrayDeque<Emplacement> pileEmplacementsLibres;
	
	/**
	 * initialise une brocante avec nombre emplacements
	 * @param nombreEmplacements le nombre d'emplacements
	 * @param tableRiverains la table des riverains 
	 * @throws IllegalArgumentException si le nombre d'emplacements est negatif ou nul ou si la table des riverains est null
	 */
	public Brocante(int nombreEmplacements, String[] tableRiverains) {
		if (nombreEmplacements < 0 || tableRiverains == null)
			throw new IllegalArgumentException();

		pileEmplacementsLibres = new ArrayDeque<>();
		tableEmplacements = new Emplacement[nombreEmplacements];
		mapRiverains = new HashMap<>();
		mapExposants = new HashMap<>();

		for (int i = 0; i < tableEmplacements.length; i++)
			tableEmplacements[i] = new Emplacement(i, null);

		for (String riverain : tableRiverains)
			mapRiverains.put(riverain, 0);

	}
	
	/**
	 * reserve l'emplacement qui porte le numero passe en parametre au demandeur passe en parametre
	 * La reservation reussit si
	 *     l'emplacement est libre
	 *     le demandeur est bien un riverain
	 *     le riverain n'a pas encore 3 emplacements
	 * @param exposant le riverain qui demande un emplacement
	 * @param numeroEmplacement le numero de l'emplacement souhaite
	 * @return true si la reservation a reussi, false sinon
	 * @throws IllegalArgumentException si le numero de l'emplacement n'existe pas
	 * @throws IllegalStateException si on n'est pas en phase 1
	 */
	public boolean reserver(Exposant exposant,int numeroEmplacement) {
		if (numeroEmplacement < 0 || numeroEmplacement > tableEmplacements.length)
			throw new IllegalArgumentException("Emplacement inexistant");
		if (phase != 1)
			throw new IllegalStateException("Vous n'êtes pas en phase une");

		Integer nombreEmplacements = nombreEmplacementsRiverain(exposant.getNom());

		if (estLibre(numeroEmplacement) && estUnRiverain(exposant.getNom()) && nombreEmplacements != 3) {
			tableEmplacements[numeroEmplacement].setExposant(exposant);
			exposant.ajouterEmplacement(tableEmplacements[numeroEmplacement]);

			mapRiverains.put(exposant.getNom(), ++nombreEmplacements);
			mapExposants.put(exposant.getNom(), exposant);

			return true;
		}

		return false;
	}
	
	/**
	 * a comme effet de passer de la phase 1 a la phase 2
	 * si deja en phase 2, rien ne doit etre fait
	 */
	public void changerPhase() {
		if (phase < 2) {
			phase ++;

			for (int i = 0; i < tableEmplacements.length; i++)
				if (estLibre(i))
					pileEmplacementsLibres.push(tableEmplacements[i]);
		}
	}
	
	/**
	 * attribue automatiquement un emmplacement libre au demandeur passe en parametre
	 * @param exposant le demandeur d'un emplacement
	 * @return le numero de l'emplacement attribue ou -1 si plus d'emplacement libre
	 * @throws IllegalStateException si on n'est pas en phase 2
	 */
	public int attribuerAutomatiquementEmplacement(Exposant exposant) {
		if (phase != 2)
			throw new IllegalStateException();

		if (!emplacementLibre())
			return -1;

		Emplacement premierLibre = pileEmplacementsLibres.pop();
		tableEmplacements[premierLibre.getNumero()].setExposant(exposant);

		exposant.ajouterEmplacement(premierLibre);
		mapExposants.put(exposant.getNom(), exposant);

		return premierLibre.getNumero();
	}

	public boolean estUnRiverain(String nom) {
		return mapRiverains.containsKey(nom);
	}

	public int nombreEmplacementsRiverain(String nom) {
		return mapRiverains.get(nom);
	}

	public boolean estLibre(int numeroEmplacement) {
		if (numeroEmplacement < 0 || numeroEmplacement > tableEmplacements.length)
			throw new IllegalArgumentException("Emplacement inexistant");

		return tableEmplacements[numeroEmplacement].getExposant() == null;
	}

	public boolean emplacementLibre() {
		return !pileEmplacementsLibres.isEmpty();
	}

	public Exposant getExposantByName (String nom) {
		return mapExposants.get(nom);
	}

	public Iterator<Exposant> tousLesExposants() {
		return mapExposants.values().iterator();
	}
	/**
	 * renvoie, sous forme d'une chaine de caracteres, tous les numeros des emplacements et leurs eventuels occupants
	 */
	public String toString() {
		// Va servir pour debugger
		String aRenvoyer = "";
		aRenvoyer = aRenvoyer + "\ntableEmplacements" + Arrays.toString(tableEmplacements);
		aRenvoyer = aRenvoyer + "\nmapRiverains" + mapRiverains.toString();
		aRenvoyer = aRenvoyer + "\npileEmplacementsLibres" + pileEmplacementsLibres.toString();
		return aRenvoyer;
		// A modifier lorsque toute l'application sera au point!
	}

}
