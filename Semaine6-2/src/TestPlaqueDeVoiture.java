import java.util.Arrays;

public class TestPlaqueDeVoiture {

	public final static int NBRE_LISTES = 500;
	
	public static void main (String args[]) {
		// Attention, la methode hashCode() renvoie un entier
		// Cet entier pourrait etre negatif --> Math.abs()		
		// Cet entier doit correspondre a une liste --> %NBRE_LISTES
		int[] repartition = new int[2000];
		// int numero = 1;

		for (char i = 'A'; i <= 'Z'; i++) {
			for (char j = 'A'; j <= 'Z'; j++) {
				for (char k = 'A'; k <= 'Z'; k++) {
					for (int l = 0; l < 10; l++) {
						for (int m = 0; m < 10; m++) {
							for (int n = 0; n < 10; n++) {
								String plaque = "1" + i + j + k + l + m + n;
								/*System.out.println(plaque + " numero: " + numero);
								numero ++;*/
								Voiture voiture = new Voiture(plaque, plaque);
								repartition[Math.abs(voiture.hashCode()) % 2000] ++;
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < 2000; i++) {
			System.out.println(repartition[i]);
		}
	}
}