package projet.echecmartien.modele

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class TestJoueur {

    private val joueur1= Joueur("toto")

    @Test
    fun getPionsCaptures() {
        assertTrue(joueur1.getPionsCaptures().isEmpty())
    }

    @Test
    fun ajouterPionCaptures() {
        val pion = MoyenPion()
        joueur1.ajouterPionCaptures(pion)
        assertTrue(pion in joueur1.getPionsCaptures())
    }

    @Test
    fun getNbPionsCaptures() {
        val mPion = MoyenPion()
        val gPion = GrandPion()
        joueur1.ajouterPionCaptures(mPion)
        joueur1.ajouterPionCaptures(gPion)
        assertTrue(joueur1.getNbPionsCaptures() == 2)
    }

    @Test
    fun getPseudo() {
        assertEquals("toto", joueur1.getPseudo())
    }

    @Test
    fun calculerScore() {
        val mPion = MoyenPion()
        val gPion = GrandPion()
        joueur1.ajouterPionCaptures(mPion)
        joueur1.ajouterPionCaptures(gPion)
        assertTrue( joueur1.calculerScore() == 5)
    }
}