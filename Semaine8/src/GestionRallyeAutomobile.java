import java.util.Scanner;

public class GestionRallyeAutomobile {
    private final static Scanner scanner = new Scanner(System.in);
    private static RallyeAutomobile rallye;
    public static void main(String[] args) {
        int choix = 0;

        System.out.println("************************");
        System.out.println("Gestionnaire de course :");
        System.out.println("************************");

        System.out.print("Veuillez entrer le nombre de pilotes : ");
        int nombrePilotes = scanner.nextInt();

        String[] pilotes = new String[nombrePilotes];

        for (int i = 1; i <= nombrePilotes; i++) {
            System.out.println("Veuillez entrer le nom du pilote n°" + i);
            String nomPilote = scanner.next();

            pilotes[i - 1] = nomPilote;
        }
        rallye = new RallyeAutomobile(pilotes);

        do {
            System.out.println("1 -> Afficher toute la course");
            System.out.println("2 -> Afficher le pilote en tête");
            System.out.println("3 -> Enregistrer un dépassement");
            System.out.println("4 -> Disqualifier un pilote");
            System.out.println("5 -> Donner la position d’un pilote");
            System.out.println("6 -> Faire franchir la ligne d’arrivée au pilote de tête");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    afficherCourse();
                    break;
                case 2:
                    afficherPiloteTete();
                    break;
                case 3:
                    enregistrerDepassement();
                    break;
                case 4:
                    discalifierPilote();
                    break;
                case 5:
                    getPositionPilote();
                    break;
                case 6:
                    franchirLigneDArrivee();
                    break;
            }
        } while (choix < 7 && choix > 0 && rallye.donnerPiloteEnTete() != null);
        System.out.println("La course est terminée !");
        System.out.println("Voici le classement :\n" + rallye.classement());
    }

    private static void afficherCourse() {
        System.out.println("Le classement actuel de la course est :\n" + rallye.toString());
    }

    private static void afficherPiloteTete() {
        System.out.println("le pilote de tête est : " + rallye.donnerPiloteEnTete());
    }

    private static void enregistrerDepassement() {
        System.out.println("Veuillez entrer le nom du pilote qui effectue le déplacement : ");
        String pilote = scanner.next();

        if (!rallye.deplacement(pilote)) {
            if (rallye.donnerPiloteEnTete().equals(pilote)) {
                System.out.println(pilote + " est déjà en tête");
            } else if (rallye.estDiscalifie(pilote)) {
                System.out.println(pilote + " est discalifié");
            } else if (rallye.aTermineCourse(pilote)) {
                System.out.println(pilote + " a déjà franchi la ligne d’arrivée");
            } else if (rallye.pasEnCourse(pilote)) {
                System.out.println(pilote + " n'est pas dans la course");
            }
        }

        if (rallye.deplacement(pilote)) {
            System.out.println("Dépassement enregistré !");
        }
    }

    private static void discalifierPilote() {
        System.out.println("Veuillez entrer le nom du pilote discalifié : ");
        String pilote = scanner.next();

        if (!rallye.discalifier(pilote)) {
            if (rallye.estDiscalifie(pilote)) {
                System.out.println(pilote + " est déjà discalifié");
            } else if (rallye.aTermineCourse(pilote)) {
                System.out.println(pilote + " a déjà franchi la ligne d’arrivée");
            } else if (rallye.pasEnCourse(pilote)) {
                System.out.println(pilote + " n'est pas dans la course");
            }
        }

        if (rallye.discalifier(pilote)) {
            System.out.println("Le pilote " + pilote + " a été disaclifié !");
        }
    }

    private static void getPositionPilote() {
        System.out.println("Veuillez entrer le nom du pilote recherché : ");
        String pilote = scanner.next();
        int position = rallye.getPositionPilote(pilote);

        if (rallye.getPositionPilote(pilote) == -1) {
            if (rallye.estDiscalifie(pilote)) {
                System.out.println(pilote + " est déjà discalifié");
            } else if (rallye.aTermineCourse(pilote)) {
                System.out.println(pilote + " a déjà franchi la ligne d’arrivée");
            } else if (rallye.pasEnCourse(pilote)) {
                System.out.println(pilote + " n'est pas dans la course");
            }
        } else {
            System.out.println("Le pilote " + pilote + " se trouve a la position " + position);
        }
    }

    private static void franchirLigneDArrivee() {
        System.out.println("Le pilote " + rallye.donnerPiloteEnTete() + " a franchi la ligne d'arrivée");
        rallye.franchirLigneDArrivee();
    }
}
