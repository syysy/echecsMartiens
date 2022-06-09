package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue

class ControleurRetourRules(vue: Parent, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : Parent
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        primaryStage.centerOnScreen()
        if (vue is MainVue){
            vue.fixeListenerBouton(vue.playButton,ControleurPlayButton(vue,primaryStage))
            vue.fixeListenerBouton(vue.botButton,ControleurBot(vue,primaryStage))
            primaryStage.scene = Scene(vue,400.0,500.0)
        }
        if (vue is JeuVue){
            primaryStage.scene = Scene(vue,500.0,920.0)
            vue.fixeListenerBouton(vue.boutonReset,ControleurReset(MainVue(),primaryStage))
        }
    }
}