
public class Grille9X9 {

	private int[][] table;

	public Grille9X9(int[][] tableARecopier)throws IllegalArgumentException{
		if(tableARecopier==null)
			throw new IllegalArgumentException();
		if(tableARecopier.length!=9)
			throw new IllegalArgumentException();
		for(int i = 0;i<9;i++){
			if(tableARecopier[i]==null||tableARecopier[i].length!=9)throw new IllegalArgumentException();
		}
		table = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(tableARecopier [i][j]<1||tableARecopier[i][j]>9)throw new IllegalArgumentException();
				table[i][j]=tableARecopier[i][j];
			}
		}
	}

	private boolean ligneCorrecte(int ligne){
		Ensemble1A9 tempLigne = new Ensemble1A9();

		for (int i = 0; i < 9; i++) {
			if (!tempLigne.ajouter(table[ligne][i]))
				return false;
		}
		return true;
	}

	private boolean colonneCorrecte(int colonne){
		Ensemble1A9 tempLigne = new Ensemble1A9();

		for (int i = 0; i < 9; i++) {
			if (!tempLigne.ajouter(table[i][colonne]))
				return false;
		}
		return true;
	}
	
	// verifie le bloc qui commence a la ligne et a la colonne passees en parametre
	private boolean blocCorrect(int ligne, int colonne){
		Ensemble1A9 tempColonne = new Ensemble1A9();

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

			}

		}
		return true;
	}



		
	public boolean estUnSudoku(){
		// TODO
		return false;
	
	}
	
	
	
	public boolean estUnSudokuDiagonal(){
		// TODO
		// cette methode est proposee en ex supplementaire
		return false;

	}
	
	

	public boolean estUnHyperSudoku(){
		// TODO
		// cette methode est proposee en ex supplementaire
		return false;

	}



}
