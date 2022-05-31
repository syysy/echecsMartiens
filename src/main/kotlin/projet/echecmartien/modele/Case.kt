package projet.echecmartien.modele



class Case {
    private var estLibre = true
    private var joueur : Joueur? = null
    private var pion : Pion? = null

    /**
     * teste si une case contient un pion ou non
     * @return true si la case ne contient pas un pion, false sinon.
     */
    fun estLibre(): Boolean {
        return estLibre
    }

    /** getter
     * @return le joueur associé à la case
     */
    fun getJoueur():Joueur? {
        return this.joueur
    }

    /** setter
     * @param joueur qui est associé à la case
     */
    fun setJoueur(joueur: Joueur?) {
        this.joueur = joueur
    }

    /** getter
     * @return le pion associé à la case
     */
    fun getPion():Pion? {
        return this.pion
    }

    /** setter
     * @param pion qui est associé à la case
     */
    fun setPion(pion: Pion?) {
       this.pion = pion
        this.estLibre = pion == null
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Case){
            return false
        }
        if (this.estLibre != other.estLibre){
            return false
        }
        if (this.joueur != other.joueur){
            return false
        }
        if (this.pion != other.pion){
            return false
        }
        return true
    }

}
