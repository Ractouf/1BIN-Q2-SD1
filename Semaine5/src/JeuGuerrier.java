public class JeuGuerrier {

    public static void main(String[] args) {
        EquipeGuerriers equipe = new EquipeGuerriers(3, 10);
        int pointsDeVieDuMal = 30;

        int nombreEnVie = equipe.nombreGuerriersEnVie();
        while (pointsDeVieDuMal > 0 && nombreEnVie > 0) {
            System.out.println("L'equipe compte " + nombreEnVie + " guerriers en vie");

            int pointsDeViePerduGuerrier = lancerDe();
            Guerrier guerrier = equipe.jouer(pointsDeViePerduGuerrier);
            System.out.println("Suite au combat entre la creature du mal et le guerrier n°" + guerrier.getNumero());
            System.out.println("Le guerrier vient de perdre " + pointsDeViePerduGuerrier + " points de vie");

            int pointsDeVieGuerrier = guerrier.getPointsDeVie();
            if (pointsDeVieGuerrier < 1)
                System.out.println("Le guerrier est mort");
            else
                System.out.println("Il lui reste " + pointsDeVieGuerrier + " points de vie");

            int pointsDeViePerduMal = lancerDe();
            pointsDeVieDuMal = pointsDeVieDuMal - pointsDeViePerduMal;
            System.out.println("La creature du mal vient de perdre " + pointsDeViePerduMal + " points de vie");

            if (pointsDeVieDuMal < 1)
                System.out.println("La creature du mal est morte");
            else
                System.out.println("Il lui reste " + pointsDeVieDuMal + " points de vie");

            nombreEnVie = equipe.nombreGuerriersEnVie();
        }
        if (nombreEnVie == 0)
            System.out.println("Tous les guerriers sont morts");
    }

    public static int lancerDe (){
        double nombreReel;
        nombreReel = Math.random();
        return (int) (nombreReel * 6) + 1;
    }

}
