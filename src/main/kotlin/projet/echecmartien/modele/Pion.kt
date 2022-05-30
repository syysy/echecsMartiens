package projet.echecmartien.modele

import projet.echecmartien.exceptions.DeplacementException


/**
 * Classe Pion
 */
abstract class Pion {
	/**
	 * récupère la valeur du score d'un pion
	 * @return la valeur du score
	 */
	abstract fun getScore() : Int


	/**
	 * donne le chemin de coordonnées que constitue le déplacement
	 * du point de départ vers le point d'arrivée. Les déplacements autorisés sont horizontaux, verticaux, diagonaux.
	 *
	 * @param deplacement le déplacement
	 * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée.
	 * La liste ne contient pas les coordonnées du point de départ.
	 *
	 * @throws DeplacementException est levée si le déplacement n'est pas possible
	 */
	fun getDeplacemeent(deplacement : Deplacement) : List<Coordonnee>{
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