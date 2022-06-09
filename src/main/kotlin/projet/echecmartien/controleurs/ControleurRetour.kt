package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import projet.echecmartien.vue.MainVue

class ControleurRetour(vue : MainVue) : EventHandler<ActionEvent> {
    val vue : MainVue

    init {
        this.vue = vue
    }

    override fun handle(p0: ActionEvent?) {
        vue.textFieldPseudo2.text = vue.savePseudo2
        vue.textFieldPseudo2.style = "-fx-background-color:white ; -fx-border-color:lightgray"
        vue.textFieldPseudo2.isEditable = true
        vue.botButton.text = "Jouer contre un robot"
        vue.fixeListenerBouton(vue.botButton,ControleurBot(vue))
    }
}