import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ArbreDEntiers implements Iterable<Integer>{
	protected NoeudEntier racine;
	protected int taille;

	public ArbreDEntiers () {
		this.racine=null ;
		this.taille=0;
	}

	public ArbreDEntiers (int entier) {
		this.racine=new NoeudEntier(entier) ;
		this.taille=1;
	}

	public ArbreDEntiers (ArbreDEntiers gauche, int entier, ArbreDEntiers droit) {
		this.racine=new NoeudEntier(gauche.racine,entier,droit.racine) ;
		this.taille= 1 + gauche.taille + droit.taille;
	}

	public boolean estVide () {
		return  this.racine == null;
	}

	public int taille () {
		return taille;
	}
	/* tout élément est visité avant ses enfants */
	public Iterator<Integer> preIterateur () {
		return new PreIterateur(this);
	}

	/* un élément est visité après sa descendance
		gauche, mais avant sa descendance droite */
	public Iterator<Integer> postIterateur () {
		return new PostIterateur(this);
	}

	// un élément sera visité après ses descendants
	public Iterator<Integer> iterator () {
		return new Iterateur(this);
	}

	public Iterator<Integer> iterateurParNiveau () {
		return new IterateurParNiveau(this);
	}


	protected class NoeudEntier {
		protected int entier;
		protected NoeudEntier gauche;
		protected NoeudEntier droit;

		private NoeudEntier (int entier) {
			this.entier = entier;
			this.gauche = null;
			this.droit = null;
		}

		private NoeudEntier (NoeudEntier g,int entier,NoeudEntier d) {
			this.entier = entier;
			this.gauche = g;
			this.droit = d;
		}
	}

	private class PreIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public PreIterateur (ArbreDEntiers a) {
			fileDEntiers = new ArrayDeque<>(taille);
			remplirFile(a.racine);
		}

		private void remplirFile (NoeudEntier n) {
			if (n == null) {
				return;
			}

			fileDEntiers.addLast(n.entier);

			remplirFile(n.gauche);
			remplirFile(n.droit);
		}

		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		public Integer next () {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return fileDEntiers.pollFirst();
		}
	}

	private class PostIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public PostIterateur (ArbreDEntiers a) {
			fileDEntiers = new ArrayDeque<>(taille);
			remplirFile(a.racine);
		}

		private void remplirFile (NoeudEntier n) {
			if (n == null) {
				return;
			}

			remplirFile(n.gauche);
			remplirFile(n.droit);
			fileDEntiers.addLast(n.entier);
		}

		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		public Integer next () {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return fileDEntiers.pollFirst();
		}
	}

	private class Iterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public Iterateur (ArbreDEntiers a) {
			fileDEntiers = new ArrayDeque<>(taille);
			remplirFile(a.racine);
		}

		private void remplirFile (NoeudEntier n) {
			if (n == null) {
				return;
			}

			remplirFile(n.gauche);
			fileDEntiers.addLast(n.entier);
			remplirFile(n.droit);
		}

		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		public Integer next () {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return fileDEntiers.pollFirst();
		}
	}

	private class IterateurParNiveau implements Iterator<Integer> {

		private ArrayDeque<NoeudEntier> fileDEntiers;

		public IterateurParNiveau (ArbreDEntiers a) {
			fileDEntiers = new ArrayDeque<>(taille);
			fileDEntiers.addLast(a.racine);
		}

		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		public Integer next () {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			NoeudEntier noeud = fileDEntiers.pollFirst();

			if (noeud.gauche != null)
				fileDEntiers.addLast(noeud.gauche);

			if (noeud.droit != null)
				fileDEntiers.addLast(noeud.droit);

			return noeud.entier;
		}
	}
}
