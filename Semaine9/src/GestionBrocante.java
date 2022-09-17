public class GestionBrocante {

	private static MonScanner scanner = new MonScanner("lancement.txt");
	private static Brocante brocante;

	public static void main(String[] args) {

		System.out.println("**********************");
		System.out.println("gestion d'une brocante");
		System.out.println("**********************");
		System.out.println();
		System.out.println("configuration de la brocante");
		System.out.println("----------------------------");
		System.out.print("Entrez le nombre d'emplacements : ");
		int nombreEmplacements = scanner.nextInt();
		System.out.print("Entrez le nombre de riverains : ");
		int nombreRiverains = scanner.nextInt();
		scanner.nextLine();
		String[] tableRiverains = new String[nombreRiverains];
		for (int i = 0; i < tableRiverains.length; i++) {
			System.out.print("Entrez le nom du riverain "+ (i+1) + ": ");
			tableRiverains[i] = scanner.nextLine();
		}
		brocante = new Brocante(nombreEmplacements, tableRiverains);
		System.out.println();


		System.out.println("Phase 1");
		System.out.println("-------");
		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println("3 -> Consulter un exposant via son nom");
			System.out.println("4 -> Lister tous les exposants");
			System.out.println("5 -> changer de phase");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					reserverPhase1();
					break;
				case 2:
					afficherTout();
					break;
				case 3:
					getExposantByName();
					break;
				case 4:
					break;
				case 5:
					brocante.changerPhase();
					break;
			}

		} while (choix >= 1 && choix <= 4);

		System.out.println("...");
		System.out.println();

		System.out.println("Phase 2");
		System.out.println("-------");
		choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println("3 -> terminer la brocante");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					reserverPhase2();
					break;
				case 2:
					afficherTout();
					break;
			}

		} while (choix >= 1 && choix <= 2);

		System.out.println("Fin de la brocante!");
	}

	private static void reserverPhase1() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		Exposant temp = null;

		if (brocante.getExposantByName(nom) == null) {
			temp = nouveauExposant(nom);
		}

		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		scanner.nextLine();

		if (brocante.reserver(temp, numero)) {
			System.out.println("Reservation réussie");
		} else {
			System.out.println("échec de la réservation");
		}
	}

	private static void reserverPhase2() {
		System.out.println("Entrez le nom : ");
		String nom = scanner.nextLine();

		Exposant temp = null;

		if (brocante.getExposantByName(nom) == null) {
			temp = nouveauExposant(nom);
		}
		brocante.attribuerAutomatiquementEmplacement(temp);
	}

	private static Exposant nouveauExposant (String nom) {
		System.out.print("Entrez l'email : ");
		String email = scanner.nextLine();

		System.out.print("Entrez le numero de téléphone : ");
		String numeroTel = scanner.nextLine();
		return new Exposant(nom, email, numeroTel);
	}

	private static void afficherTout() {
		System.out.println(brocante);
	}

	private static Exposant getExposantByName () {
		System.out.println("Entrez le nom : ");
		String nom = scanner.nextLine();
		return brocante.getExposantByName(nom);
	}
}
