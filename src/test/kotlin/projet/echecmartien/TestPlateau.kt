package projet.echecmartien

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import projet.echecmartien.modele.Case
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Deplacement
import projet.echecmartien.modele.Plateau

class TestPlateau {

    @Test
    fun testPlateau() {
        val plateau = Plateau()
        plateau.initialiser()
        println(plateau)
    }

    @Test
    fun testPlateauTailleHor() {
        val plateau = Plateau()
        plateau.initialiser()
        val res = plateau.getTailleHorizontale()
        Assertions.assertEquals(4,res)
    }

    @Test
    fun testPlateauTailleVer() {
        val plateau = Plateau()
        plateau.initialiser()
        val res = plateau.getTailleVerticale()
        Assertions.assertEquals(8,res)
    }

    @Test
    fun testPlateau2() {
        val plateau = Plateau()
        val case = Case()
        plateau.initialiser()
        Assertions.assertEquals(Case(),plateau.getCases()[0][0])
    }
}