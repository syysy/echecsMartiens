package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue

class ControleurRetourRules(vue: MainVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : MainVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        primaryStage.scene = Scene(vue,400.0,500.0)
        primaryStage.centerOnScreen()
        vue.fixeListenerBouton(vue.playButton,ControleurPlayButton(JeuVue(),primaryStage))
        vue.fixeListenerBouton(vue.botButton,ControleurBot(vue,primaryStage))
    }
}