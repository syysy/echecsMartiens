package projet.echecmartien

import com.sun.jdi.connect.Connector
import org.junit.jupiter.api.Test
import projet.echecmartien.modele.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.Exception
import java.util.stream.Stream

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

    @ParameterizedTest
    @MethodSource("coorPionValid")
    fun testDeplacement(oracle : List<Coordonnee>,deplacement: Deplacement,pion: Pion){
        assertEquals(oracle,pion.getDeplacement(deplacement))
    }

    @ParameterizedTest
    @MethodSource("coorPionInvalid")
    fun testDeplacementInvalid(deplacement: Deplacement,pion: Pion){
        assertThrows<Exception> { pion.getDeplacement(deplacement) }
    }
    companion object{
        @JvmStatic
        fun coorPionValid(): Stream<Arguments?>? {
            return Stream.of(
                // Grand Pion
                // Déplacement Vertical
                Arguments.of(listOf(Coordonnee(1,3),Coordonnee(1,4)),Deplacement(Coordonnee(1,2),Coordonnee(1,5)),GrandPion()),
                // Déplacement Horizontal
                Arguments.of(listOf(Coordonnee(1,2),Coordonnee(2,2)),Deplacement(Coordonnee(0,2),Coordonnee(3,2)),GrandPion()),
                // Déplacement Diagonal
                Arguments.of(listOf(Coordonnee(1,1),Coordonnee(2,2)),Deplacement(Coordonnee(0,0),Coordonnee(3,3)),GrandPion()),
                // Déplacement de 1
                Arguments.of(emptyList<Coordonnee>(),Deplacement(Coordonnee(1,1), Coordonnee(2,2)),GrandPion()),

                // Moyen Pion
                // Déplacement Vertical
                Arguments.of(listOf(Coordonnee(1,3)),Deplacement(Coordonnee(1,2),Coordonnee(1,4)),MoyenPion()),
                // Déplacement Horizontal
                Arguments.of(listOf(Coordonnee(1,2)),Deplacement(Coordonnee(0,2),Coordonnee(2,2)),MoyenPion()),
                // Déplacement Diagonal
                Arguments.of(listOf(Coordonnee(1,1)),Deplacement(Coordonnee(0,0),Coordonnee(2,2)),MoyenPion()),
                // Déplacement de 1
                Arguments.of(emptyList<Coordonnee>(),Deplacement(Coordonnee(1,1),Coordonnee(2,2)),MoyenPion()),

                // Petit Pion
                // Déplacement Diagonal
                Arguments.of(emptyList<Coordonnee>(),Deplacement(Coordonnee(1,1), Coordonnee(2,2)),PetitPion())
            )
        }

        @JvmStatic
        fun coorPionInvalid(): Stream<Arguments?>? {
            return Stream.of(
                // Petit Pion
                // Déplacement Vertical
                Arguments.of(Deplacement(Coordonnee(1,2),Coordonnee(1,3)),PetitPion()),
                // Déplacement Horizontal
                Arguments.of(Deplacement(Coordonnee(1,2), Coordonnee(2,2)),PetitPion()),
                // Déplacement en dehors
                Arguments.of(Deplacement(Coordonnee(0,0), Coordonnee(-1,-1)),PetitPion()),
                // Déplacement trop long
                Arguments.of(Deplacement(Coordonnee(0,0), Coordonnee(2,2)),PetitPion()),

                // Moyen Pion
                // Déplacement en L
                Arguments.of(Deplacement(Coordonnee(0,0),Coordonnee(1,2)),MoyenPion()),
                // Déplacement en dehors
                Arguments.of(Deplacement(Coordonnee(0,0),Coordonnee(-2,-2)),MoyenPion()),
                // Déplacement trop long
                Arguments.of(Deplacement(Coordonnee(0,0),Coordonnee(3,3)),MoyenPion()),

                // Grand Pion
                // Déplacement en L
                Arguments.of(Deplacement(Coordonnee(0,0),Coordonnee(1,3)),GrandPion()),
                // Déplacement en dehors
                Arguments.of(Deplacement(Coordonnee(0,0),Coordonnee(-2,-2)),GrandPion())
            )
        }
    }


}