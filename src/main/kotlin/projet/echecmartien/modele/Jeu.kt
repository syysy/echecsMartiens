package projet.echecmartien.modele

import projet.echecmartien.exceptions.DeplacementException


class Jeu() : InterfaceJeu{

    private var coordOrigine : Coordonnee? = null
    private var nombreCoupsSansPrise = 0
    private var nombreCoupsSansPriseMax = 10
    private var coordDest : Coordonnee? = null
    private var pionArriveDeZone : Pion? = null
    private var joueurCourant : Joueur? = null
    private var joueur = arrayOf<Joueur>(Joueur(""), Joueur(""))
    private var plateau = Plateau()

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


    /**
     * affectation des joueurs aux cases
     * @param joueur1 premier joueur
     * @paral joueur2 second joueur
     */
    private fun initialiserJoueur(joueur1: Joueur, joueur2: Joueur) {
        val compt = 0
        for (i in this.plateau.getCases()){
            if (compt < 4){
                for (j in i){
                    j.setJoueur(joueur1)
                }
            }else{
                for (j in i){
                    j.setJoueur(joueur2)
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
        if(nombreCoupsSansPrise == nombreCoupsSansPriseMax){
            return true
        }
        return false
    }

    /**
     * modifie le joueur courant
     *
     */
    fun changeJoueurCourant() {
        if (joueurCourant == joueur[0]){
            joueurCourant = joueur[1]
        }else{
            joueurCourant = joueur[0]
        }
    }

    override fun initialiserPartie(joueur1: Joueur, joueur2: Joueur, nombreCoupsSansPriseMax: Int) {
        joueur[0] = joueur1
        joueur[1] = joueur2
        initialiserJoueur(joueur1,joueur2)
        this.nombreCoupsSansPriseMax = nombreCoupsSansPriseMax
    }

    override fun deplacementPossible(coordOrigineX: Int, coordOrigineY: Int): Boolean {
        return (coordOrigineX < 0 || coordOrigineX > plateau.getTailleHorizontale() || coordOrigineY < 0 || coordOrigineY > plateau.getTailleVerticale())
    }

    override fun deplacementPossible(
        coordOrigineX: Int,
        coordOrigineY: Int,
        coordDestinationX: Int,
        coordDestinationY: Int,
        joueur: Joueur?
    ): Boolean {
        if (deplacementPossible(coordOrigineX,coordOrigineY) && deplacementPossible(coordDestinationX,coordDestinationY)){
            val dep = Deplacement(Coordonnee(coordOrigineX,coordOrigineY),
                Coordonnee(coordDestinationX,coordDestinationY)
            )
            if (!dep.estHorizontal() && !dep.estVertical() && !dep.estDiagonal()){
                return false
            }
            val ourPion = plateau.getCases()[coordOrigineX][coordOrigineY].getPion()
            if (ourPion is PetitPion){
                return (dep.longueur() == 1 && dep.estDiagonal())
            }else if (ourPion is MoyenPion){
                return (dep.longueur() <= 2)
            }else if (ourPion is GrandPion){
                return true
            }else{
                throw DeplacementException()
            }

        }else{
            return false
        }
    }

    override fun deplacer(coordOrigineX: Int, coordOrigineY: Int, coordDestinationX: Int, coordDestinationY: Int) {
        if (deplacementPossible(coordOrigineX,coordOrigineY,coordDestinationX,coordDestinationY,this.joueurCourant)){
            if (!plateau.getCases()[coordDestinationX][coordDestinationY].estLibre()){
                this.joueurCourant!!.ajouterPionCaptures(plateau.getCases()[coordDestinationX][coordDestinationY].getPion()!!)
                this.nombreCoupsSansPrise += 1
            }
            plateau.getCases()[coordDestinationX][coordDestinationY].setPion(plateau.getCases()[coordOrigineX][coordOrigineY].getPion())
            plateau.getCases()[coordOrigineX][coordOrigineY].setPion(null)



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