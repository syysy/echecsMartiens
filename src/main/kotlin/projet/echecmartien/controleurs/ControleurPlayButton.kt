package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.AppliJeuEchecMartien
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class ControleurPlayButton(oldVue: MainVue, primaryStage: Stage): EventHandler<ActionEvent> {

    val primaryStage : Stage
    val oldVue : MainVue

    init {
        this.primaryStage = primaryStage
        this.oldVue = oldVue
    }

    override fun handle(p0: ActionEvent?) {
        val modele = Jeu()
        val newVue = JeuVue()
        val scene = Scene(newVue,500.0,800.0)
        primaryStage.scene = scene
        primaryStage.centerOnScreen()
        newVue.joueur1.text = oldVue.textFieldPseudo1.text
        newVue.joueur2.text = oldVue.textFieldPseudo2.text
        newVue.initialisationJeu()
        newVue.fixeListenerBouton(newVue.boutonReset,ControleurReset(MainVue(),primaryStage))
        newVue.fixeListenerBouton(newVue.boutonRegles,ControleurRulesJeu(newVue,primaryStage))
        newVue.fixeListenerBouton(newVue.boutonSave,ControleurSave(newVue,modele,primaryStage))
    }

}