package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.Stage
import projet.echecmartien.AppliJeuEchecMartien
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class ControleurReset(vue: MainVue, modele : Jeu, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : MainVue
    val primaryStage : Stage
    val jeu : Jeu
    init {
        this.vue = vue
        this.primaryStage = primaryStage
        this.jeu = modele
    }

    override fun handle(p0: ActionEvent?) {
        val dialog = Alert(Alert.AlertType.CONFIRMATION)
        dialog.title="Are you sure about that ?"
        dialog.headerText = null
        dialog.graphic = null
        dialog.contentText = "Voulez-vous reset ?"
        dialog.dialogPane.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/style.css").toExternalForm())
        dialog.dialogPane.styleClass.add("dialog2")
        val resultat = dialog.showAndWait()
        if (resultat.get() == ButtonType.OK){
            primaryStage.scene = Scene(vue,400.0,600.0)
            primaryStage.centerOnScreen()
            primaryStage.scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/style.css").toExternalForm())
            vue.addStyle()
            vue.fixeListenerBouton(vue.playButton,ControleurPlayButton(vue,Jeu(),primaryStage))
            vue.fixeListenerBouton(vue.botButton,ControleurBot(vue, Jeu(), primaryStage))
            vue.fixeListenerBouton(vue.rulesButton,ControleurRules(vue, Jeu(), primaryStage))
            vue.fixeListenerBouton(vue.loadButton,ControleurChargerSave(vue,Jeu(),primaryStage))
        }
    }
}