package projet.echecmartien.modele

class Joueur(nom : String) {
    var nom : String = nom
    var pionCapture : MutableSet<Pion> = mutableSetOf()

    fun stringPions(): String {
        var res = ""
        for (i in pionCapture){
            if(i is PetitPion){
                res += "1"
            }else if (i is MoyenPion){
                res += "2"
            }else{
                res += "3"
            }
        }
        return res
    }

    /**
     * récupére la liste des pions capturés
     * @return la liste des pions capturés qui est vide si aucun pion n'a été capturé
     */
    fun getPionsCaptures(): Set<Pion> {
        return this.pionCapture.toSet()
    }

    /**
     * ajout à la liste d'un pion qui a été capturé
     * @param pion à ajouter
     */
    fun ajouterPionCaptures(pion: Pion) {
        this.pionCapture.add(pion)
    }


    /**
     * permet de connaître le nombre de pions capturés
     * @return le nombre de pions capturés
     */
    fun getNbPionsCaptures(): Int {
        return pionCapture.size
    }


    /**
     * récupère la valeur du pseudo
     *
     * @return la valeur du pseudo
     */
    fun getPseudo(): String {
        return nom
    }



    /**
     * calcule le score du joueur
     * @return le score du joueur
     */
    fun calculerScore(): Int {
        var res = 0
        for (i in this.pionCapture){
            res += i.getScore()
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (this === other){
            return true
        }else return other is Joueur && this.nom == other.nom
    }

}
