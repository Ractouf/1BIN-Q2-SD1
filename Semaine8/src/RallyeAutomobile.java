import java.util.HashSet;

public class RallyeAutomobile {
    private ListeSDImpl<String> pilotes;
    private ListeSDImpl<String> depart;
    private ListeSDImpl<String> classement;
    private HashSet<String> ensembleDisqualifies;

    public RallyeAutomobile(String[] pilotes) {
        this.pilotes = new ListeSDImpl();
        classement = new ListeSDImpl();
        depart = new ListeSDImpl();
        ensembleDisqualifies = new HashSet();

        for (String pilote : pilotes) {
            this.pilotes.insererEnQueue(pilote);
            depart.insererEnQueue(pilote);
        }
    }

    public String donnerPiloteEnTete() {
        return pilotes.premier();
    }

    public boolean deplacement(String pilote) {
        if (estDiscalifie(pilote) || aTermineCourse(pilote) || pasEnCourse(pilote))
            return false;
        String piloteDepasse = pilotes.donnerPrecedent(pilote);
        pilotes.permuter(pilote, piloteDepasse);
        return true;
    }

    public boolean discalifier(String pilote) {
        if (estDiscalifie(pilote) || aTermineCourse(pilote) || pasEnCourse(pilote))
            return false;

        ensembleDisqualifies.add(pilote);
        pilotes.supprimer(pilote);
        return true;
    }

    public int getPositionPilote(String pilote) {
        if (estDiscalifie(pilote) || aTermineCourse(pilote))
            return -1;

        int index = 1;
        for (String piloteSuivant : pilotes) {
            if (piloteSuivant.equals(pilote)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void franchirLigneDArrivee() {
        classement.insererEnQueue(donnerPiloteEnTete());
        pilotes.supprimer(donnerPiloteEnTete());
    }

    public boolean estDiscalifie(String pilote) {
        return ensembleDisqualifies.contains(pilote);
    }
    public boolean aTermineCourse(String pilote) {
        return classement.contient(pilote);
    }
    public boolean pasEnCourse(String pilote) {
        return !depart.contient(pilote);
    }

    public String classement() {
        String toString = "";
        int index = 1;
        for (String pilote : classement) {
            toString += index + " - " + pilote + "\n";
        }
        return toString;
    }

    public String toString() {
        String toString = "";
        int index = 1;
        for (String pilote : pilotes) {
            toString += index + " - " + pilote + "\n";
        }
        return toString;
    }
}
