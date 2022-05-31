package projet.echecmartien

import org.junit.jupiter.api.Test
import projet.echecmartien.modele.*
import org.junit.jupiter.api.Assertions.*

class TestPion {
    var pion1 = GrandPion()
    var pion2 = MoyenPion()
    var pion3 = PetitPion()

    @Test
    fun testScore(){
        assertEquals(1,pion3.getScore())
        assertEquals(2,pion2.getScore())
        assertEquals(3,pion1.getScore())
    }

    @Test
    fun testDeplacement(){
        assertEquals(listOf(Coordonnee(1,3),Coordonnee(1,4)),pion1.getDeplacement(Deplacement(Coordonnee(1,2),Coordonnee(1,5))))
    }
}