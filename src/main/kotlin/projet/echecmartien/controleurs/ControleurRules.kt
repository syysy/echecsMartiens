package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue


class ControleurRules(vue: MainVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : MainVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        val rules = RulesVue()
        vue.savePseudo1 = vue.textFieldPseudo1.text
        vue.savePseudo2 = vue.textFieldPseudo2.text
        primaryStage.scene = Scene(rules,1080.0,600.0)
        primaryStage.centerOnScreen()
        rules.fixeListenerBouton(rules.buttonBottomRules,ControleurRetourRules(vue,primaryStage))
    }
}

