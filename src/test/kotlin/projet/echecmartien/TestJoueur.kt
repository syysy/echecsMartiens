package projet.echecmartien



import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import projet.echecmartien.librairie.EnumPion
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Deplacement
import projet.echecmartien.modele.Joueur
import projet.echecmartien.modele.Plateau

class TestJoueur{

    private val joueur1= Joueur("toto")

    @Test
    fun testPseudo() {
        assertEquals("toto", joueur1.getPseudo())
    }

    @Test
    fun testPlato() {
        var plateau = Plateau()
        plateau.initialiser()
        for (i in plateau.getCases()){
            for (j in i){
                if (j.getPion() == null){
                    print(0)
                }else{
                    print(j.getPion()!!.getScore())
                }
            }
            println("")
        }
        println(plateau)
    }



}