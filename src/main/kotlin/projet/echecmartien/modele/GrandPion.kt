package projet.echecmartien.modele

import projet.echecmartien.exceptions.DeplacementException


open class GrandPion : Pion(){
    override fun getScore() : Int{
        return 3
    }

    /**
     * Regarde que le déplacement est sur le plateau et qu'il est soit horizontal, soit vertical, soit
     * diagonale. Dans le cas où il ne remplis pas les conditions, renvoie une erreur, sinon effectue le
     * déplacement.
     * */
    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.getOrigine().getX() !in 0..3 || deplacement.getOrigine().getY() !in 0..7 || deplacement.getDestination().getX() !in 0..3 || deplacement.getDestination().getY() !in 0..7){
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







