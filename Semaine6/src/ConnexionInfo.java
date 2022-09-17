/**
 * @author Vandeputte François
 *
 */
public class ConnexionInfo {
   
 	Ensemble<LoginInfo> ensembleDesConnectes;
 	// N'AJOUTEZ PAS D'AUTRES ATTRIBUTS!!!
 	// N'OUBLIEZ PAS DE COMPLETER LA METHODE HASHCODE() DE LA CLASSE LOGININFO
 	
 	public ConnexionInfo(){
		ensembleDesConnectes = new EnsembleTableBooleens<>(666);
	}
 	
 	public	int nombreDeConnectes(){
 		return ensembleDesConnectes.taille();
	}

	public boolean connecter(LoginInfo loginInfo){
 		return ensembleDesConnectes.ajouter(loginInfo);
	}

	public boolean deconnecter(LoginInfo loginInfo){
 		return ensembleDesConnectes.enlever(loginInfo);
	}

	public boolean estConnecte(LoginInfo loginInfo){
		return ensembleDesConnectes.contient(loginInfo);
	}
}