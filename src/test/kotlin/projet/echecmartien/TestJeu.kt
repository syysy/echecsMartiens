package projet.echecmartien.modele

import com.sun.jdi.connect.Connector
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import projet.echecmartien.exceptions.DeplacementException
import java.util.stream.Stream

internal class TestJeu {

    val jeuTest = Jeu()

    @Test
    fun setCoordOrigineDeplacementNull() {
        val coorOriginPrimary = jeuTest.getCoordOrigineDeplacement()
        assert(coorOriginPrimary == null)
    }

    @Test
    fun setCoordOrigineDeplacement() {
        val coorOriginPrimary = jeuTest.getCoordOrigineDeplacement()
        assert(coorOriginPrimary == jeuTest.getCoordOrigineDeplacement())
    }

    @Test
    fun setCoordOrigineDeplacementChangementDiff() {
        val coorOriginPrimary = jeuTest.getCoordOrigineDeplacement()
        jeuTest.setCoordOrigineDeplacement(Coordonnee(0,0))
        assert(coorOriginPrimary != jeuTest.getCoordOrigineDeplacement())
    }

    @Test
    fun setCoordOrigineDeplacementChangement() {
        jeuTest.setCoordOrigineDeplacement(Coordonnee(0,0))
        assertEquals(Coordonnee(0,0),jeuTest.getCoordOrigineDeplacement()!!)
    }

    @Test
    fun setCoordDestinationDeplacementNull() {
        val coorDestinationPrimary = jeuTest.getCoordDestinationDeplacement()
        assert(coorDestinationPrimary == null)
    }

    @Test
    fun setCoordDestinationDeplacement() {
        val coorDestinationPrimary = jeuTest.getCoordDestinationDeplacement()
        assert(coorDestinationPrimary == jeuTest.getCoordDestinationDeplacement())
    }

    @Test
    fun setCoordDestinationDeplacementChangementDiff() {
        jeuTest.setCoordDestinationDeplacement(Coordonnee(0,0))
        assertNotNull(jeuTest.getCoordDestinationDeplacement())
    }

    @Test
    fun setCoordDestinationDeplacementChangement() {
        jeuTest.setCoordDestinationDeplacement(Coordonnee(0,0))
        assert(Coordonnee(0,0) == jeuTest.getCoordDestinationDeplacement())
    }

    @Test
    fun getJoueurCourant() {
        val joueurCourant = jeuTest.getJoueurCourant()
        assert(joueurCourant == null)
    }

    @Test
    fun setJoueurCourant() {
        jeuTest.initialiserPartie(Joueur("Quoi"),Joueur("Feur"),10)
        assertEquals(Joueur("Quoi"),jeuTest.getJoueurCourant())
    }

    @Test
    fun changeJoueurCourant() {
        jeuTest.initialiserPartie(Joueur("Quoi"),Joueur("Feur"),10)
        jeuTest.changeJoueurCourant()
        assert(jeuTest.getJoueurCourant() == Joueur("Feur"))
    }

    @Test
    fun initialiserPartie() {
        jeuTest.initialiserPartie(Joueur("Râ"),Joueur("Dio"),160)
        val joueur1 = jeuTest.getJoueurCourant()
        jeuTest.changeJoueurCourant()
        val joueur2 = jeuTest.getJoueurCourant()
        assertEquals(joueur1,Joueur("Râ"))
        assertEquals(joueur2,Joueur("Dio"))
        assertEquals(160,jeuTest.getNombreCoupsMax())
    }

    @Test
    fun testdeplacementPossible1() {
        assertTrue(jeuTest.deplacementPossible(2,3))
        assertTrue(jeuTest.deplacementPossible(2,4))
        assertTrue(jeuTest.deplacementPossible(3,0))
        assertTrue(jeuTest.deplacementPossible(0,2))
        assertFalse(jeuTest.deplacementPossible(-1,2))
        assertFalse(jeuTest.deplacementPossible(5,3))
        assertFalse(jeuTest.deplacementPossible(2,-1))
        assertFalse(jeuTest.deplacementPossible(5,18))
    }

    @Test
    fun testDeplacementPossible2() {
        jeuTest.initialiserPartie(Joueur("oui"),Joueur("non"),20)

        assertTrue(jeuTest.deplacementPossible(2,2,3,3,null))
    }

    @Test
    fun deplacer() {
        jeuTest.initialiserPartie(Joueur("oui"),Joueur("non"),20)
        jeuTest.deplacer(2,2,3,3)
    }

    @Test
    fun joueurVainqueurNull() {
        assertNull(jeuTest.joueurVainqueur())
    }

    @Test
    fun joueurVainqueur() {
        val joueur1 = Joueur("Ludovikk")
        val joueur2 = Joueur("Mojito")
        jeuTest.initialiserPartie(joueur1,joueur2,160)
        joueur1.ajouterPionCaptures(GrandPion())
        joueur2.ajouterPionCaptures(PetitPion())
        assertEquals(joueur1,jeuTest.joueurVainqueur())
    }

    @ParameterizedTest
    @MethodSource("intCoordProvider")
    fun testThrowsDeplacer(coord1X : Int,coord1Y : Int,coord2X : Int,coord2Y : Int){
        jeuTest.initialiserPartie(Joueur("Râ"), Joueur("Dio"),12)
        assertThrows<Exception> { jeuTest.deplacer(coord1X, coord1Y, coord2X, coord2Y) }
    }
    companion object{
        @JvmStatic
        fun intCoordProvider() : Stream<Arguments?>?{
            return Stream.of(

                // Arguments à l'extérieur d'un plateau

                Arguments.of(-1,1,1,1),
                Arguments.of(1,-1,1,1),
                Arguments.of(1,1,-1,1),
                Arguments.of(1,1,1,-1),
                Arguments.of(4,1,1,1),
                Arguments.of(1,8,1,1),
                Arguments.of(1,1,4,1),
                Arguments.of(1,1,1,8),
                Arguments.of(3,3,1,1),

                // Arguments relatifs à un Grand Pion

                Arguments.of(0,0,1,1),
                Arguments.of(0,0,1,2),
                Arguments.of(0,0,3,3),

                // Arguments relatifs à un Moyen Pion

                Arguments.of(0,2,0,5),
                Arguments.of(0,2,1,4),

                // Arguments relatifs à un Petit Pion

                Arguments.of(2,2,2,3),
                Arguments.of(2,2,4,4)
            )
        }
    }
}