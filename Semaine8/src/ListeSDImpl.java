import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListeSDImpl<E> implements ListeSD<E>,Iterable<E> {

	private Noeud tete, queue;
	private HashMap<E, Noeud> mapElementNoeud;

	public ListeSDImpl () {
		mapElementNoeud = new HashMap<>();
		tete = new Noeud();
		queue = new Noeud();
		tete.suivant = queue;
		queue.precedent = tete;
	}

	public int taille () {
		return mapElementNoeud.size();
	}

	public boolean estVide () {
		return mapElementNoeud.isEmpty();
	}

	public boolean contient (E element) {
		return mapElementNoeud.containsKey(element);
	}

	public E premier() {
		return tete.suivant.element;
	}

	public E dernier() {
		return queue.precedent.element;
	}

	public boolean insererEnTete (E element){
		if (mapElementNoeud.containsKey(element))
			return false;

		Noeud nouveauNoeud = new Noeud(element);

		nouveauNoeud.precedent = tete;
		nouveauNoeud.suivant = tete.suivant;
		tete.suivant.precedent = nouveauNoeud;
		tete.suivant = nouveauNoeud;

		mapElementNoeud.put(element, nouveauNoeud);
		return true;
	}

	public boolean insererEnQueue (E element) {
		if (mapElementNoeud.containsKey(element))
			return false;

		Noeud nouveauNoeud = new Noeud(element);

		nouveauNoeud.suivant = queue;
		nouveauNoeud.precedent = queue.precedent;
		queue.precedent.suivant = nouveauNoeud;
		queue.precedent = nouveauNoeud;

		mapElementNoeud.put(element, nouveauNoeud);
		return true;
	}

	public boolean insererApres (E element, E elementAInserer) {
		if (mapElementNoeud.containsKey(elementAInserer))
			return false;

		Noeud nouveauNoeud = new Noeud(elementAInserer);
		Noeud noeudPrecedent = mapElementNoeud.get(element);

		if (noeudPrecedent == null)
			return false;

		nouveauNoeud.suivant = noeudPrecedent.suivant;
		nouveauNoeud.precedent = noeudPrecedent;
		noeudPrecedent.suivant.precedent = nouveauNoeud;
		noeudPrecedent.suivant = nouveauNoeud;

		mapElementNoeud.put(elementAInserer, nouveauNoeud);
		return true;
	}

	public boolean insererAvant (E element, E elementAInserer) {
		if (mapElementNoeud.containsKey(elementAInserer))
			return false;

		Noeud nouveauNoeud = new Noeud(elementAInserer);
		Noeud noeudSuivant = mapElementNoeud.get(element);

		if (noeudSuivant == null)
			return false;

		nouveauNoeud.suivant = noeudSuivant;
		nouveauNoeud.precedent = noeudSuivant.precedent;
		noeudSuivant.precedent.suivant = nouveauNoeud;
		noeudSuivant.precedent = nouveauNoeud;

		mapElementNoeud.put(elementAInserer, nouveauNoeud);
		return true;
	}


	public boolean supprimer (E element) {
		Noeud noeud = mapElementNoeud.get(element);
		if (noeud == null) {
			return false;
		}

		noeud.precedent.suivant = noeud.suivant;
		noeud.suivant.precedent = noeud.precedent;

		mapElementNoeud.remove(element);
		return true;
	}

	public E donnerPrecedent (E element) {
		Noeud temp = mapElementNoeud.get(element);
		if (temp == null)
			return null;
		return temp.precedent.element;
	}

	public E donnerSuivant (E element) {
		Noeud temp = mapElementNoeud.get(element);
		if (temp == null)
			return null;
		return temp.suivant.element;
	}
	
	
	public boolean permuter (E element1, E element2) {
		if (!mapElementNoeud.containsKey(element1) || !mapElementNoeud.containsKey(element2))
			return false;
		Noeud noeud1 = mapElementNoeud.get(element1);
		Noeud noeud2 = mapElementNoeud.get(element2);
		noeud1.element = element2;
		noeud2.element = element1;
		mapElementNoeud.put(element1, noeud2);
		mapElementNoeud.put(element2, noeud1);
		return true;
	}

	public Iterator<E> iterator() {
		return new IterateurImpl<E>();
	}

	public String toString () {
		String aRenvoyer = "";
		int num = 1;
		Noeud baladeur = tete.suivant;
		while (baladeur != queue) {
			aRenvoyer += num + " - " + baladeur.element + "\n";
			baladeur = baladeur.suivant;
			num++;
		}
		return aRenvoyer;   
	}

	// pour les tests
	public ListeSDImpl(E[] tableACopier) {	
		mapElementNoeud = new HashMap<E, Noeud>();
		tete = new Noeud();   // sentinelle de tete
		queue = new Noeud();  // sentinelle de queue
		Noeud prec = tete;
		for (int i = 0; i < tableACopier.length; i++) {
			Noeud nouveauNoeud = new Noeud(tableACopier[i]);
			mapElementNoeud.put(tableACopier[i], nouveauNoeud);
			nouveauNoeud.precedent = prec;
			prec.suivant = nouveauNoeud;
			prec = nouveauNoeud;
		}
		prec.suivant = queue;
		queue.precedent = prec;	
	}

	// pour les tests
	public String teteQueue(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = tete.suivant;
			int cpt=0;
			while (baladeur != queue) {
				if(cpt==0)
					aRenvoyer += baladeur.element;
				else
					aRenvoyer += ","+baladeur.element;
				baladeur = baladeur.suivant;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// pour les tests
	public String queueTete(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = queue.precedent;
			int cpt=0;
			while (baladeur != tete) {
				if(cpt==0)
					aRenvoyer += baladeur.element;
				else
					aRenvoyer += ","+baladeur.element;
				baladeur = baladeur.precedent;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// Classe interne Noeud
	private class Noeud{
		private E element;
		private Noeud suivant;
		private Noeud precedent;

		private Noeud() {
			this(null, null, null);
		}

		private Noeud(E element) {
			this(null, element, null);
		}

		private Noeud(Noeud precedent, E element, Noeud suivant) {
			this.element = element;
			this.suivant = suivant;
			this.precedent = precedent;
		}
	}

	

	// Classe interne IterateurImpl
	private class IterateurImpl<E> implements Iterator<E>{

		private Noeud noeudCourant;

		private IterateurImpl() {
			noeudCourant = tete.suivant;
		}

		public boolean hasNext() {
			return noeudCourant != queue;
		}

		// renvoie l element qui se trouve dans le noeud courant
		// le noeud courant passe au noeud suivant
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			E aRenvoyer = (E)noeudCourant.element;
			noeudCourant = noeudCourant.suivant;
			return aRenvoyer;
		}

		// A NE PAS COMPLETER : Les suppressions sont interdites
		public void remove() {
			throw new UnsupportedOperationException();			
		}
	}

}
