package projet.echecmartien.modele

import projet.echecmartien.exceptions.DeplacementException


open class GrandPion : Pion(){
    override fun getScore() : Int{
        return 3
    }

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
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







