package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class ControleurRetourRulesJeu (vue: JeuVue, primaryStage: Stage): EventHandler<ActionEvent> {

    val primaryStage : Stage
    val vue : JeuVue

    init {
        this.primaryStage = primaryStage
        this.vue = vue
    }

    override fun handle(p0: ActionEvent?) {
        primaryStage.scene = Scene(vue,500.0,920.0)
        primaryStage.centerOnScreen()
        vue.fixeListenerBouton(vue.boutonReset,ControleurReset(MainVue(),primaryStage))
        vue.fixeListenerBouton(vue.boutonRegles,ControleurRulesJeu(RulesVue(),primaryStage))
    }
}