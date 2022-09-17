
public class PileImplChainee<E> implements Pile<E>{

	private Noeud sommet;
	private int taille;

	
	public PileImplChainee(){
		this.sommet=null;
		this.taille = 0;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	// base 'a' 'b' 'c' sommet : ['a','b','c']
	public PileImplChainee(Object[] table) {
		if(table == null)
			throw new IllegalArgumentException();
		this.taille = table.length;
		Noeud noeud = null;
		for (int i = 0; i < table.length; i++) {
			noeud = new Noeud((E)table[i],noeud);
		}
		this.sommet=noeud;
	}

	// A ne pas modifier ! Methode utilisee pour les tests
	public String toString(){
		String aRenvoyer="";
		Noeud baladeur=sommet;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>taille){
				aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
			}
			aRenvoyer=" "+baladeur.element+aRenvoyer;
			baladeur=baladeur.suivant;
		}
		return aRenvoyer;
	}
	
	
	public int taille() {
		return this.taille;
	}
		
	
	public boolean estVide() {
		return this.taille == 0;
	}
	
	
	public void push(E element) {
		sommet = new Noeud(element, sommet);
		taille ++;
	}

	
	public E pop() throws PileVideException {
		if (taille < 1)
			throw new PileVideException();

		Noeud temp = sommet;
		sommet = sommet.suivant;
		taille --;

		return temp.element;
	}
	
	
	public E sommet() throws PileVideException {
		if (taille < 1)
			throw new PileVideException();

		return sommet.element;
	}


	
	// classe interne
	private class Noeud{
		private E element;
		private Noeud suivant;

		private Noeud(E element, Noeud suivant){
			this.element = element;
			this.suivant = suivant;
		}

		public Noeud(E element) {
			this.element = element;
			this.suivant = null;
		}
		
		
	}

}
