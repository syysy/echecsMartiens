package projet.echecmartien.modele

class Coordonnee(x : Int, y : Int) {
    private var x = x
    private var y = y


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
       return "x = $x,y = $y"
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