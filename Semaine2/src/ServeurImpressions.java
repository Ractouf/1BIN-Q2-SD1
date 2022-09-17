import java.util.ArrayList;

public class ServeurImpressions{
	private FileAttenteImpressions[] fileDemandesImpressionAvecPriorite;

	/**
	 * construit une table avec 10 files FileAttenteImpressions
	 */
	public ServeurImpressions() {
		fileDemandesImpressionAvecPriorite = new FileAttenteImpressions[10];

		for (int i = 0; i < fileDemandesImpressionAvecPriorite.length; i++) {
			fileDemandesImpressionAvecPriorite[i] = new FileAttenteImpressions();
		}
	}

	/**
	 * verifie si toutes les files sont vides
	 * @return true si toutes les files sont vides, false sinon
	 */
	public boolean estVide(){
		for (FileAttenteImpressions fileAttenteImpressions : fileDemandesImpressionAvecPriorite) {
			if (!fileAttenteImpressions.estVide())
				return false;
		}
		return true;
	}

	/**
	 * ajoute la demande d impression en fin de la file de priorite correspondante
	 * @param demande la demande a ajouter
	 * @throws IllegalArgumentException si la demande est null
	 */
	public void ajouter(DemandeImpressionAvecPriorite demande){
		if (demande == null)
			throw new IllegalArgumentException();
		
		fileDemandesImpressionAvecPriorite[demande.getPriorite()].ajouter(demande);
	}

	/**
	 * retire l'impression en tete de file de priorite la plus haute qui est non vide
	 * @return l'impression qui a ete retiree
	 * @throws FileVideException si aucune demande d impression dans la file
	 */
	public DemandeImpressionAvecPriorite retirer()throws FileVideException{



		DemandeImpressionAvecPriorite temp = null;
		for (int i = fileDemandesImpressionAvecPriorite.length - 1; i > 0; i--) {
			if (!fileDemandesImpressionAvecPriorite[i].estVide()) {

				fileDemandesImpressionAvecPriorite[i].retirer();
			}
		}
		return null;
	}



}


