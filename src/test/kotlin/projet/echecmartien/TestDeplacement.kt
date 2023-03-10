package projet.echecmartien

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Deplacement
import java.util.stream.Stream

class TestDeplacement {
    @Test
    fun testDeplacementDiag(){
        val deplacement = Deplacement(Coordonnee(0,3), Coordonnee(1,4))
        deplacement.estDiagonal()
        Assertions.assertTrue(deplacement.estDiagonal())
        Assertions.assertFalse(deplacement.estHorizontal())
        Assertions.assertFalse(deplacement.estVertical())
    }

    @Test
    fun testDeplacementDiagNegNeg(){
        val deplacement = Deplacement(Coordonnee(1,4), Coordonnee(0,3))
        deplacement.estDiagonalNegatifXNegatifY()
        Assertions.assertTrue(deplacement.estDiagonalNegatifXNegatifY())
        Assertions.assertFalse(deplacement.estHorizontal())
        Assertions.assertFalse(deplacement.estVertical())
    }

    @Test
    fun testDeplacementDiagNegPos(){
        val deplacement = Deplacement(Coordonnee(1,3), Coordonnee(0,4))
        deplacement.estDiagonalNegatifXPositifY()
        Assertions.assertTrue(deplacement.estDiagonalNegatifXPositifY())
        Assertions.assertFalse(deplacement.estHorizontal())
        Assertions.assertFalse(deplacement.estVertical())
    }

    @Test
    fun testDeplacementDiagPosPos(){
        val deplacement = Deplacement(Coordonnee(1,2), Coordonnee(2,3))
        deplacement.estDiagonalPositifXPositifY()
        Assertions.assertTrue(deplacement.estDiagonalPositifXPositifY())
        Assertions.assertFalse(deplacement.estHorizontal())
        Assertions.assertFalse(deplacement.estVertical())
    }

    @Test
    fun testDeplacementDiagPosNeg(){
        val deplacement = Deplacement(Coordonnee(0,3), Coordonnee(1,2))
        deplacement.estDiagonalPositifXNegatifY()
        Assertions.assertTrue(deplacement.estDiagonalPositifXNegatifY())
        Assertions.assertFalse(deplacement.estHorizontal())
        Assertions.assertFalse(deplacement.estVertical())
    }

    @Test
    fun testDeplacementHorizontalePos(){
        val deplacement = Deplacement(Coordonnee(0,3),Coordonnee(1,3))
        deplacement.estHorizontalPositif()
        Assertions.assertTrue(deplacement.estHorizontalPositif())
        Assertions.assertFalse(deplacement.estDiagonal())
        Assertions.assertFalse(deplacement.estVertical())
    }

    @Test
    fun testDeplacementHorizontale(){
        val deplacement = Deplacement(Coordonnee(1,3),Coordonnee(0,3))
        deplacement.estHorizontal()
        Assertions.assertTrue(deplacement.estHorizontal())
        Assertions.assertFalse(deplacement.estDiagonal())
        Assertions.assertFalse(deplacement.estVertical())
    }

    @Test
    fun testDeplacementVerticale(){
        val deplacement = Deplacement(Coordonnee(0,3),Coordonnee(0,2))
        deplacement.estVertical()
        Assertions.assertTrue(deplacement.estVertical())
        Assertions.assertFalse(deplacement.estHorizontal())
        Assertions.assertFalse(deplacement.estDiagonal())
    }

    @Test
    fun testDeplacementVerticalePos(){
        val deplacement = Deplacement(Coordonnee(0,3),Coordonnee(0,4))
        deplacement.estVerticalPositif()
        Assertions.assertTrue(deplacement.estVerticalPositif())
        Assertions.assertFalse(deplacement.estHorizontal())
        Assertions.assertFalse(deplacement.estDiagonal())
    }
}