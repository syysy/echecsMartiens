package projet.echecmartien

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import projet.echecmartien.modele.*

class TestPlateau {

    private val plateau = Plateau()
    @Test
    fun testPlateau() {
        plateau.initialiser()
        // println(plateau)
    }

    @Test
    fun testPlateauTailleHor() {
        plateau.initialiser()
        val res = plateau.getTailleHorizontale()
        Assertions.assertEquals(4,res)
    }

    @Test
    fun testPlateauTailleVer() {
        plateau.initialiser()
        val res = plateau.getTailleVerticale()
        Assertions.assertEquals(8,res)
    }

    @Test
    fun testPlateau2() {
        val case = Case()
        case.setPion(GrandPion())
        plateau.initialiser()
        Assertions.assertEquals(case,plateau.getCases()[0][0])
    }
}