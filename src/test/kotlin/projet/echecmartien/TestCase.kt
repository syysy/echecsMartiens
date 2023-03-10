package projet.echecmartien

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import projet.echecmartien.modele.*

class TestCase{

    var case1 = Case()
    var case2 = Case()
    var case3 = Case()
    var caseLib = Case()

    init{
        case1.setPion(GrandPion())
        case2.setPion(MoyenPion())
        case3.setPion(PetitPion())
        case1.setJoueur(Joueur("maurice"))
        case2.setJoueur(Joueur("Pierre"))
        case3.setJoueur(null)
    }

    @Test
    fun testJoueur(){
        assertEquals(Joueur("maurice"),case1.getJoueur())
        assertEquals(Joueur("Pierre"),case2.getJoueur())
        assertNotEquals(Joueur("Pierre"),case3.getJoueur())
    }

    @Test
    fun testPion(){
        assertTrue(case1.getPion() is GrandPion)
        assertTrue(case2.getPion() is GrandPion)
        assertTrue(case2.getPion() is MoyenPion)
        assertTrue(case3.getPion() is PetitPion)
    }

    @Test
    fun testLibre(){
        assert(caseLib.estLibre())
        assertFalse(case1.estLibre())
    }

    @Test
    fun caseEquals(){
        val casePion = Case()
        val caseEgal = Case()
        casePion.setJoueur(Joueur("maurice"))
        casePion.setPion(PetitPion())
        caseEgal.setPion(GrandPion())
        caseEgal.setJoueur(Joueur("maurice"))
        assertFalse(caseLib.equals(case1))
        assertFalse(caseLib.equals("feur"))
        assertFalse(case1.equals(case2))
        assertFalse(case1.equals(casePion))
        assertTrue(case1.equals(caseEgal))
    }
}