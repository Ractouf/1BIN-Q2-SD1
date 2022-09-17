/**
 * @author Vandeputte François
 *
 */
public class Connexion {
   
	Ensemble<Login> ensembleDesConnectes;
	
	public Connexion(){
		ensembleDesConnectes = new EnsembleTableBooleens<>(666);
	}

	public int nombreDeConnectes(){
		return ensembleDesConnectes.taille();
	}

	public boolean connecter(Login login){
		return ensembleDesConnectes.ajouter(login);
	}

	public boolean deconnecter(Login login){
		return ensembleDesConnectes.enlever(login);
	}

	public boolean estConnecte(Login login){
		return ensembleDesConnectes.contient(login);
	}
}

