import java.util.Scanner;

public class GestionnaireEntrepot {
    private final static Scanner scanner = new Scanner(System.in);
    private static Entrepot entrepot;
    public static void main(String[] args) {
        entrepot = new Entrepot(10);
        int choix = 0;

        System.out.println("*************************");
        System.out.println("Gestionnaire d'Entrepot :");
        System.out.println("*************************");

        do {
            System.out.println("1 : attribuer un hangar");
            System.out.println("2 : lister les hangars d'une société");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    testAttribuerHangar();
                    break;
                case 2:
                    listeHangarsSociete();
                    break;
                case 3:
                    libererHangar();
                    break;
            }
        } while (choix <= 3 && choix >= 1);
    }

    private static void testAttribuerHangar() {
        int numeroSociete;
        String nomSociete;

        do {
            System.out.println("Veuillez entrer un numéro de société : ");
            numeroSociete = scanner.nextInt();
        } while (numeroSociete < 0);

        System.out.println("Veuillez entrer un nom de société");
        nomSociete = scanner.next();
        int resultat = entrepot.attribuerHangar(numeroSociete, nomSociete);
        if (resultat == -1) {
            System.out.println("Tous les hangars sont occupés");
            return;
        }
        System.out.println("Le hangar attribué est le " + resultat);
    }

    private static void listeHangarsSociete() {
        int numeroSociete;

        do {
            System.out.println("Veuillez entrer un numéro de société : ");
            numeroSociete = scanner.nextInt();
        } while (numeroSociete < 0);

        System.out.println(entrepot.getSociete(numeroSociete).lesHangars());
    }

    private static void libererHangar() {
        int numeroHangar;
        do {
            System.out.println("Veuillez entrer un numéro de hangar : ");
            numeroHangar = scanner.nextInt();
        } while (numeroHangar < 0 || numeroHangar > 3);
    }
}
