package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene

import javafx.stage.Stage
import projet.echecmartien.modele.*
import projet.echecmartien.vue.MainVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {
        val vue = MainVue()
        val scene = Scene(vue, 350.0, 350.0)
        primaryStage.title="TD5B MVC"
        primaryStage.scene=scene
        primaryStage.show()
    }

}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
    val jeu = Jeu()
    val p = Plateau()
    val j1 = Joueur("Mar")
    val j2 = Joueur("Tin")
    jeu.initialiserPartie(j1,j2,5)
    println(jeu.getJoueurCourant()!!.nom)
    jeu.changeJoueurCourant()
    println(jeu.getJoueurCourant()!!.nom)
    println(jeu.plateau.getCases()[0][7].getJoueur()!!.nom)
    jeu.deplacer(3,5,3,4)
    jeu.deplacer(3,4,3,3)
    println(jeu.plateau.getCases()[3][3].getJoueur()!!.nom)
    println(jeu.plateau)
}



