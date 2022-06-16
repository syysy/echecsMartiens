package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene

import javafx.stage.Stage
import projet.echecmartien.controleurs.*
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        val vue = MainVue()
        val modele = Jeu()
        vue.fixeListenerBouton(vue.botButton,ControleurBot(vue,modele,primaryStage))
        vue.fixeListenerBouton(vue.rulesButton,ControleurRules(vue,modele,primaryStage))
        vue.fixeListenerBouton(vue.playButton,ControleurPlayButton(vue,modele,primaryStage))
        vue.fixeListenerBouton(vue.buttonBottomRules, ControleurRetourRules(vue,modele,primaryStage))
        vue.fixeListenerBouton(vue.loadButton,ControleurChargerSave(vue,modele,primaryStage))
        val scene = Scene(vue,400.0,600.0)
        scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/style.css").toExternalForm())
        vue.addStyle()
        primaryStage.title="ECHECS MARTIENS"
        primaryStage.scene=scene
        primaryStage.show()
    }
}

fun main() {
    Application.launch(AppliJeuEchecMartien::class.java)
}



