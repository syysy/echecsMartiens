package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.AppliJeuEchecMartien
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.RulesVue

class ControleurRulesJeu(vue: JeuVue, modele : Jeu, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : JeuVue
    val primaryStage : Stage
    val jeu : Jeu

    init {
        this.vue = vue
        this.primaryStage = primaryStage
        this.jeu = modele
    }

    override fun handle(p0: ActionEvent?) {
        val rules = RulesVue()
        primaryStage.scene = Scene(rules,1250.0,700.0)
        primaryStage.centerOnScreen()
        vue.savePseudo1 = vue.joueur1.text
        vue.savePseudo2 = vue.joueur2.text
        primaryStage.scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/style.css").toExternalForm())
        rules.addStyle()
        rules.fixeListenerBouton(rules.buttonBottomRules,ControleurRetourRulesJeu(vue,jeu,primaryStage))
    }
}