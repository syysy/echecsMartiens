package projet.echecmartien.modele

import projet.echecmartien.exceptions.DeplacementException


class Jeu : InterfaceJeu{

    private var coordOrigine : Coordonnee? = null
    private var nombreCoupsSansPrise = 0
    private var nombreCoupsSansPriseMax = 4
    private var coordDest : Coordonnee? = null
    private var pionArriveDeZone : Pion? = null
    private var joueurCourant : Joueur? = null
    private var joueur = arrayOf(Joueur(""), Joueur(""))
    var plateau = Plateau()

    fun getNombreCoupsMax():Int{
        return nombreCoupsSansPriseMax
    }

    /**
     * getter
     * @return la coordonnée origine du déplacement
     */
    fun getCoordOrigineDeplacement(): Coordonnee? {
        return coordOrigine
    }

    /**
     * getter
     * @return la coordonnée destination du déplacement
     */
    fun getCoordDestinationDeplacement(): Coordonnee? {
        return coordDest
    }


    /**
     * setter
     * @param origine la coordonnée origine du déplacement
     */
    fun setCoordOrigineDeplacement(origine: Coordonnee) {
        coordOrigine = origine
    }


    /**
     * setter
     * @param destination la coordonnée destination du déplacement
     */
    fun setCoordDestinationDeplacement(destination: Coordonnee) {
        coordDest = destination
    }


    /** retourne le joueur courant
     * @return le joueur courant
     */
    fun getJoueurCourant(): Joueur? {
        return joueurCourant
    }

    fun getNombreCoupsSansPrise(): Int{
        return nombreCoupsSansPrise
    }

    fun setNombreCoupSansPrise(nombre : Int ){
        nombreCoupsSansPrise = nombre
    }

    /**
     * affectation des joueurs aux cases
     * @param joueur1 premier joueur
     * @paral joueur2 second joueur
     */
    private fun initialiserJoueur(joueur1: Joueur, joueur2: Joueur) {
        for (i in 0 until plateau.getTailleVerticale()){
            if (i < 4){
                for (j in 0 until plateau.getTailleHorizontale()){
                    plateau.getCases()[j][i].setJoueur(joueur1)
                }
            }else{
                for (j in 0 until plateau.getTailleHorizontale()){
                    plateau.getCases()[j][i].setJoueur(joueur2)
                }
        }
        }
        joueurCourant = joueur1
    }

    /**
     * permet de savoir si la partie est finie ou non
     * @return true si la partie est finie, false sinon
     */
    fun arretPartie(): Boolean {
        if (nombreCoupsSansPrise >= nombreCoupsSansPriseMax) {
            return true
        }
        var count = 0
        var countJcourant = 0
        for (i in 0 until 8) {
            for (j in 0 until 4) {
                if (!plateau.getCases()[i][j].estLibre()){
                    count += 1
                }
                if (plateau.getCases()[i][j].getJoueur() == joueurCourant && !plateau.getCases()[i][j].estLibre()){
                    countJcourant += 1
                }
            }
        }
        if (count < 2 ){
            return true
        }
        if (countJcourant == 0){
            return true
        }
        return false
    }

    /**
     * modifie le joueur courant
     *
     */
    fun changeJoueurCourant() {
        joueurCourant = if (joueurCourant == joueur[0]){
            joueur[1]
        }else{
            joueur[0]
        }
    }

    override fun initialiserPartie(joueur1: Joueur, joueur2: Joueur, nombreCoupsSansPriseMax: Int) {
        joueur[0] = joueur1
        joueur[1] = joueur2
        initialiserJoueur(joueur1,joueur2)
        this.nombreCoupsSansPriseMax = nombreCoupsSansPriseMax
        plateau.initialiser()
    }

    override fun deplacementPossible(coordOrigineX: Int, coordOrigineY: Int): Boolean {
        return (coordOrigineX >= 0 && coordOrigineX < plateau.getTailleHorizontale() && coordOrigineY >= 0 && coordOrigineY < plateau.getTailleVerticale())
    }

    override fun deplacementPossible(
        coordOrigineX: Int,
        coordOrigineY: Int,
        coordDestinationX: Int,
        coordDestinationY: Int,
        joueur: Joueur?
    ): Boolean {
        if (plateau.getCases()[coordOrigineX][coordOrigineY].estLibre() || joueur != joueurCourant){
            return false
        }
        if (deplacementPossible(coordOrigineX,coordOrigineY) && deplacementPossible(coordDestinationX,coordDestinationY)) {
            val dep =
                Deplacement(Coordonnee(coordOrigineX, coordOrigineY), Coordonnee(coordDestinationX, coordDestinationY))
            if (!dep.estHorizontal() && !dep.estVertical() && !dep.estDiagonal()) {
                return false
            }
            if (plateau.getCases()[coordDestinationX][coordDestinationY].getPion() != null && plateau.getCases()[coordDestinationX][coordDestinationY].getJoueur() == joueurCourant){
                return false
            }
            val ourPion = plateau.getCases()[coordOrigineX][coordOrigineY].getPion()
            println("${dep.getOrigine().getX()},${dep.getOrigine().getY()},${dep.getDestination().getX()},${dep.getDestination().getY()}")
            val chemin = ourPion!!.getDeplacement(dep)
            print(chemin)
            for (i in chemin) {
                if (!plateau.getCases()[i.getX()][i.getY()].estLibre()) {
                    return false
                }
            }
            return !(plateau.getCases()[coordOrigineX][coordOrigineY].getPion() === this.pionArriveDeZone && ((coordDestinationY >= 4 && coordOrigineY < 4) || (coordDestinationY < 4 && coordOrigineY >= 4)))

        }
        return false
    }




    override fun deplacer(coordOrigineX: Int, coordOrigineY: Int, coordDestinationX: Int, coordDestinationY: Int) {
        if (deplacementPossible(coordOrigineX,coordOrigineY,coordDestinationX,coordDestinationY,this.joueurCourant)){
            if (!plateau.getCases()[coordDestinationX][coordDestinationY].estLibre()){
                this.joueurCourant!!.ajouterPionCaptures(plateau.getCases()[coordDestinationX][coordDestinationY].getPion()!!)
            }else{
                this.nombreCoupsSansPrise += 1
            }
            plateau.getCases()[coordDestinationX][coordDestinationY].setPion(plateau.getCases()[coordOrigineX][coordOrigineY].getPion())
            plateau.getCases()[coordOrigineX][coordOrigineY].setPion(null)
        }else{
            throw DeplacementException()
        }
    }

    override fun joueurVainqueur(): Joueur? {
        if (joueur[0].calculerScore() < joueur[1].calculerScore()){
            return joueur[1]
        }
        if (joueur[0].calculerScore() > joueur[1].calculerScore()){
            return joueur[0]
        }
        return null
    }
}