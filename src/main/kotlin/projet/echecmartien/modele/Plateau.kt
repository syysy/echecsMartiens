package projet.echecmartien.modele

import projet.echecmartien.librairie.GeneralData


class Plateau {

    private var cases : Array<Array<Case>>
    private val tailleHorizontale : Int = 4
    private val tailleVerticale : Int = 8

    init {
        val caseFant : MutableList<Array<Case>> = mutableListOf()
        var fantoche : MutableList<Case>
        for (i in 0 until tailleHorizontale){
            fantoche = mutableListOf()
            for (j in 0 until tailleVerticale){
                fantoche.add(Case())
            }
            caseFant.add(fantoche.toTypedArray())
        }
        cases = caseFant.toTypedArray()
    }

    /**
     * initialise le plateau de jeu avec les pions
     */

    fun initialiser() {
        val tab = GeneralData().tableau
        for(i in 0 until tailleHorizontale){
            for ( j in 0 until tailleVerticale){
                if (tab[i][j].valeur == "M"){
                    cases[i][j].setPion(MoyenPion())
                }
                if (tab[i][j].valeur == "G"){
                    cases[i][j].setPion(GrandPion())
                }
                if (tab[i][j].valeur == "P"){
                    cases[i][j].setPion(PetitPion())
                }
            }
        }
    }



    /**
     * donne la taille horizontale du plateau
     * @return la taille horizontale du plateau
     */
    fun getTailleHorizontale(): Int {
        return this.tailleHorizontale
    }


    /**
     * donne la taille verticale du plateau
     * @return la taille verticale du plateau
     */
    fun getTailleVerticale(): Int {
        return this.tailleVerticale
    }


    /**
     * donne le tableau des cases du plateau
     * @return les cases du plateau
     */
    fun getCases(): Array<Array<Case>> {
        return cases
    }

    override fun toString(): String {
        var printing = "┏"
        for (j in 0 until tailleHorizontale){
            printing += if (j == tailleHorizontale-1){
                "━┓\n"
            }else{
                "━┳"
            }
        }
        for (i in 0 until tailleVerticale){
            for (j in 0 until tailleHorizontale) {
                printing += "┃${if (cases[j][i].getPion() == null){" "}else{cases[j][i].getPion()!!.getScore()}}"
            }
            printing += "┃\n"
            if (i != tailleVerticale-1) {
                if (i == tailleVerticale/2-1){
                    printing += "┣═"
                    for (j in 1 until tailleHorizontale) {
                        printing += "╪═"
                    }
                    printing += "┫\n"
                }else {
                    printing += "┣━"
                    for (j in 1 until tailleHorizontale) {
                        printing += "╋━"
                    }
                    printing += "┫\n"
                }
            } else {
                printing += "┗━"
                for (j in 1 until tailleHorizontale) {
                    printing += "┻━"
                }
                printing += "┛\n"
            }
        }
        return printing
    }

}