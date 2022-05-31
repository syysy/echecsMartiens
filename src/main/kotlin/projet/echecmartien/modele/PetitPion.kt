package projet.echecmartien.modele

import projet.echecmartien.exceptions.DeplacementException

class PetitPion() : Pion(){
    override fun getScore() : Int{
        return 1
    }

    override fun getDeplacement(deplacement : Deplacement): List<Coordonnee> {
        if (deplacement.estDiagonal() && deplacement.longueur() == 1){
            return deplacement.getCheminDiagonal()
        }else{
            throw DeplacementException()
        }
    }

}