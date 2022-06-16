package projet.echecmartien.modele

import projet.echecmartien.exceptions.DeplacementException


class MoyenPion:GrandPion() {
    override fun getScore(): Int {
        return 2
    }

    /**
     * Regarde si le déplacement si le déplacement est bien sur le plateau, que celui ci est bien inférieur
     * ou égal à 2 de distance et qu'il est soit Horizontal, soit Vertical, soit Diagonale. Dans le cas où
     * il ne remplis pas les conditions, renvoie une erreur, sinon effectue le déplacement.
     * */
    override fun getDeplacement(deplacement : Deplacement): List<Coordonnee> {
        if (deplacement.getOrigine().getX() !in 0..3 || deplacement.getOrigine().getY() !in 0..7 || deplacement.getDestination().getX() !in 0..3 || deplacement.getDestination().getY() !in 0..7){
            throw DeplacementException()
        }
        if (deplacement.longueur() > 2){
            throw DeplacementException()
        }
        if (deplacement.estHorizontal()){
            return deplacement.getCheminHorizontal()
        }else if (deplacement.estVertical()) {
            return deplacement.getCheminVertical()
        }else if (deplacement.estDiagonal()){
            return deplacement.getCheminDiagonal()
        }
        throw DeplacementException()
    }
}