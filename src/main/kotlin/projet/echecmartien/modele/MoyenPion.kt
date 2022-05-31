package projet.echecmartien.modele

import projet.echecmartien.exceptions.DeplacementException


class MoyenPion():GrandPion() {
    override fun getScore(): Int {
        return 2
    }

    override fun getDeplacement(deplacement : Deplacement): List<Coordonnee> {
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