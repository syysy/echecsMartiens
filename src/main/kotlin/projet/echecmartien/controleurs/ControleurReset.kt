package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import projet.echecmartien.vue.MainVue

class ControleurReset(vue : MainVue) : EventHandler<ActionEvent> {
    val vue : MainVue

    init {
        this.vue = vue
    }

    override fun handle(p0: ActionEvent?) {
        TODO()
    }
}