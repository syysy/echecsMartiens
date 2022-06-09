package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class ControleurRulesJeu(vue: RulesVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : RulesVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        primaryStage.scene = Scene(vue,1080.0,500.0)
        primaryStage.centerOnScreen()
        vue.fixeListenerBouton(vue.buttonBottomRules,ControleurRetourRulesJeu(JeuVue(),primaryStage))
    }
}