import java.util.Iterator;
import java.util.LinkedList;

public class Exposant {
    private String nom, email, numero;
    private LinkedList<Emplacement> listeEmplacents = new LinkedList<>();

    public Exposant(String nom, String email, String numero) {
        this.nom = nom;
        this.email = email;
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void ajouterEmplacement (Emplacement emplacement) {
        listeEmplacents.add(emplacement);
    }

    public Iterator<Emplacement> tousLesEmplacements(){
        return listeEmplacents.iterator() ;
    }

    @Override
    public String toString() {
        return nom +
                ", email : " + email +
                ", numero : " + numero +
                ", emplacements : " + tousLesEmplacements();
    }


}
