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
        var new = JeuVue()
        new.joueur1.text = vue.savePseudo1
        new.joueur2.text = vue.savePseudo2
        new.pts1.text = vue.pts1.text
        new.pts2.text = vue.pts2.text
        new.compteTour.text = vue.compteTour.text
        new.jeu.plateau = vue.jeu.plateau
        new.initialisationJeu()
        primaryStage.scene = Scene(new,500.0,800.0)
        primaryStage.centerOnScreen()
        new.fixeListenerBouton(new.boutonReset,ControleurReset(MainVue(),primaryStage))
        new.fixeListenerBouton(new.boutonRegles,ControleurRulesJeu(new,primaryStage))
    }
}