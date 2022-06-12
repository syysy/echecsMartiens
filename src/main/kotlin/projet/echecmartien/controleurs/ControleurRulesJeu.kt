package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class ControleurRulesJeu(vue: JeuVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : JeuVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        val rules = RulesVue()
        primaryStage.scene = Scene(rules,1080.0,600.0)
        primaryStage.centerOnScreen()
        vue.savePseudo1 = vue.joueur1.text
        vue.savePseudo2 = vue.joueur2.text
        vue.saveJeu = vue.jeu
        rules.fixeListenerBouton(rules.buttonBottomRules,ControleurRetourRulesJeu(vue,primaryStage))
    }
}