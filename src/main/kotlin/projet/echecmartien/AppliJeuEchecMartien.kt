package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image

import javafx.stage.Stage
import projet.echecmartien.controleurs.*
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import java.io.FileInputStream

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
        val test = FileInputStream("src/main/resources/projet/echecmartien/logo.png")
        val zebu = Image(test)
        primaryStage.isResizable = false
        primaryStage.icons.add(zebu)
        vue.addStyle()
        primaryStage.title="ECHECS MARTIENS by ZebuShield"
        primaryStage.scene=scene
        primaryStage.show()
    }
}

fun main() {
    Application.launch(AppliJeuEchecMartien::class.java)
}



