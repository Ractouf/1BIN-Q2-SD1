import java.util.Scanner;

public class GestionSalleExposition {
	
	private static Scanner scanner = new Scanner(System.in);
	private static SalleExposition salleExpo = new SalleExposition();

	public static void main(String[] args) {	
		
		System.out.println("********************************");
		System.out.println("gestion d'une salle d'exposition");
		System.out.println("********************************");
	
		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> afficher le nombre d'emplacements");
			System.out.println("2 -> afficher le nombre d'oeuvres");
			System.out.println("3 -> afficher le catalogue");
			System.out.println("4 -> ajouter une oeuvre");
			System.out.println("5 -> supprimer une oeuvre");
			System.out.println("6 -> consulter une oeuvre");	
		
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
			
			case 1:
				afficherNombreEmplacements();
				break;
			case 2:
				afficherNombreOeuvres();
				break;
			case 3:
				afficherCatalogue();
				break;
			case 4:
				ajouter();
				break;
			case 5:
				supprimer();
				break;
			case 6:				
				consulter();
				break;
			default:
				break;
			}

		} while (choix >= 1 && choix <= 6);
	}
	
	private static void afficherNombreEmplacements() {
		System.out.println("Il y a "+ salleExpo.nombreEmplacements()+" emplacements");		
	}
	
	
	private static void afficherNombreOeuvres() {
		System.out.println("Il y a "+ salleExpo.nombreOeuvres()+" oeuvres");	
	}

	
	private static void afficherCatalogue() {
		System.out.println(salleExpo.donnerCatalogue());
		
	}

	private static void ajouter() {
		System.out.print("Entrez l'oeuvre : ");
		String oeuvre = scanner.nextLine();
		int numero = salleExpo.ajouter(oeuvre);	
		System.out.println("L'oeuvre a ete ajoutee avec succes.");
		System.out.println("Elle porte le numero "+numero);
	}
	

	private static void supprimer() {
		int numeroOeuvre;
		do {
			System.out.print("Entrez un numéro d'oeuvre : ");
			numeroOeuvre = scanner.nextInt();
		} while (salleExpo.supprimer(numeroOeuvre) == null);

		System.out.println("L'oeuvre a été suprimée avec succes.");

	}
	
	
	private static void consulter() {
		int numeroOeuvre;

		do {
			System.out.print("Entrez un numéro d'oeuvre : ");
			numeroOeuvre = scanner.nextInt();
		} while (salleExpo.consulter(numeroOeuvre) == null);

		System.out.println(salleExpo.consulter(numeroOeuvre));
	}


}
