package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.Stage
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class ControleurReset(vue: MainVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : MainVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        val dialog = Alert(Alert.AlertType.CONFIRMATION)
        dialog.title="boîte de dialogue de confirmation"
        dialog.headerText="Voulez-vous vraiment reset la partie ?"
        val resultat = dialog.showAndWait()
        if (resultat.get() == ButtonType.OK){
            primaryStage.scene = Scene(vue,400.0,500.0)
            primaryStage.centerOnScreen()
            vue.fixeListenerBouton(vue.playButton,ControleurPlayButton(vue,primaryStage))
            vue.fixeListenerBouton(vue.botButton,ControleurBot(vue,primaryStage))
            vue.fixeListenerBouton(vue.rulesButton,ControleurRules(vue,primaryStage))
        }
    }
}