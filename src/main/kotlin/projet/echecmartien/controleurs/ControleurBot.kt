package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import projet.echecmartien.vue.MainVue

class ControleurBot(vue: MainVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : MainVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        vue.savePseudo2 = vue.textFieldPseudo2.text
        vue.textFieldPseudo2.text = "BOT"
        vue.textFieldPseudo2.style = "-fx-background-color:lightgray"
        vue.textFieldPseudo2.isEditable = false
        vue.botButton.text = "Retour"
        vue.fixeListenerBouton(vue.botButton,ControleurRetour(vue,primaryStage))
    }
}