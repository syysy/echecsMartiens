package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene

import javafx.stage.Stage
import projet.echecmartien.controleurs.ControleurBot
import projet.echecmartien.controleurs.ControleurPlayButton
import projet.echecmartien.controleurs.ControleurRetourRules
import projet.echecmartien.controleurs.ControleurRules
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        val vue = MainVue()
        vue.fixeListenerBouton(vue.botButton,ControleurBot(vue,primaryStage))
        vue.fixeListenerBouton(vue.rulesButton,ControleurRules(RulesVue(),primaryStage))
        vue.fixeListenerBouton(vue.playButton,ControleurPlayButton(JeuVue(),primaryStage))
        vue.fixeListenerBouton(vue.buttonBottomRules, ControleurRetourRules(vue,primaryStage))
        val scene = Scene(vue,400.0,500.0)
        primaryStage.title="TD5B MVC"
        primaryStage.scene=scene
        primaryStage.show()

    }
}

fun main() {
    Application.launch(AppliJeuEchecMartien::class.java)
}

//val img = Image("https://cdn-s-www.lejsl.com/images/B03DA098-E2DA-4F6A-937A-6AE2D6700B6D/NW_raw/la-galaxie-dans-laquelle-le-fluor-a-ete-detecte-se-trouve-a-12-milliards-d-annees-lumiere-image-d-illustration-pixabay-1636110175.jpg")
//val bImg = BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
//    BackgroundRepeat.NO_REPEAT,
//    BackgroundPosition.CENTER,
//    BackgroundSize.DEFAULT)
//val background = Background(bImg)
//vue.background = background


