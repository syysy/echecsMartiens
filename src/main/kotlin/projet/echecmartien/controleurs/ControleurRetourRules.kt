package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class ControleurRetourRules(oldVue: MainVue, primaryStage: Stage): EventHandler<ActionEvent> {

    val primaryStage : Stage
    val vue : MainVue

    init {
        this.primaryStage = primaryStage
        this.vue = oldVue
    }

    override fun handle(p0: ActionEvent?) {
        val new = MainVue()
        primaryStage.scene = Scene(new,400.0,600.0)
        primaryStage.centerOnScreen()
        new.textFieldPseudo1.text = vue.savePseudo1
        new.textFieldPseudo2.text = vue.savePseudo2
        new.fixeListenerBouton(new.playButton,ControleurPlayButton(new,primaryStage))
        new.fixeListenerBouton(new.botButton,ControleurBot(new,primaryStage))
        new.fixeListenerBouton(new.rulesButton,ControleurRules(new,primaryStage))
        new.fixeListenerBouton(new.loadButton,ControleurChargerSave(new, Jeu(), primaryStage))
    }
}