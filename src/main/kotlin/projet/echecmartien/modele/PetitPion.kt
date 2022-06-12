package projet.echecmartien.modele

import projet.echecmartien.exceptions.DeplacementException

class PetitPion() : Pion(){
    override fun getScore() : Int{
        return 1
    }

    override fun getDeplacement(deplacement : Deplacement): List<Coordonnee> {
        println(deplacement.longueur())
        if (deplacement.estDiagonal() && deplacement.longueur() == 1 && deplacement.getOrigine().getX() in 0..3 && deplacement.getOrigine().getY() in 0..7 && deplacement.getDestination().getX() in 0..3 && deplacement.getDestination().getY() in 0..7){
            return deplacement.getCheminDiagonal()
        }else{
            throw DeplacementException()
        }
    }

}