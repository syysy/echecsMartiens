package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.AppliJeuEchecMartien
import projet.echecmartien.vue.JeuVue

class ControleurPlayButton(vue: JeuVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : JeuVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        primaryStage.scene = Scene(vue,500.0,920.0)
        primaryStage.centerOnScreen()
    }

}