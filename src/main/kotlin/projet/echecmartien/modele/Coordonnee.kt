package projet.echecmartien.modele

class Coordonnee(private var x: Int, private var y: Int) {


    /**
     *@return la coordonnée en x
     */
    fun getX(): Int{
        return x
    }


    /**
     *@return la coordonnée en y
     */
    fun getY(): Int{
       return y
    }


    override fun toString():String{
       return "($x,$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Coordonnee){
            return false
        }
        if (this.x != other.x){
            return false
        }
        if (this.y != other.y){
            return false
        }
        return true
    }




}