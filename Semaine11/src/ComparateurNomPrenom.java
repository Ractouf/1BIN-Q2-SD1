import java.util.Comparator;

public class ComparateurNomPrenom implements Comparator<Etudiant> {

    @Override
    public int compare(Etudiant o1, Etudiant o2) {
        if (o1.getNom().equals(o2.getNom()))
            return o1.getPrenom().compareTo(o2.getPrenom());

        return o1.getNom().compareTo(o2.getNom());
    }
}
