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

class ControleurRetourRules(oldVue: MainVue, modele : Jeu, primaryStage: Stage): EventHandler<ActionEvent> {

    val primaryStage : Stage
    val vue : MainVue
    val jeu : Jeu

    init {
        this.primaryStage = primaryStage
        this.vue = oldVue
        this.jeu = modele
    }

    override fun handle(p0: ActionEvent?) {
        val new = MainVue()
        primaryStage.scene = Scene(new,400.0,500.0)
        primaryStage.centerOnScreen()
        new.textFieldPseudo1.text = vue.savePseudo1
        new.textFieldPseudo2.text = vue.savePseudo2
        new.fixeListenerBouton(new.playButton,ControleurPlayButton(new,jeu,primaryStage))
        new.fixeListenerBouton(new.botButton,ControleurBot(new,jeu,primaryStage))
        new.fixeListenerBouton(new.rulesButton,ControleurRules(new,jeu,primaryStage))
        new.fixeListenerBouton(new.loadButton,ControleurChargerSave(new,jeu,primaryStage))
    }
}