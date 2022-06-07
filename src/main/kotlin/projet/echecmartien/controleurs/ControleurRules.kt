package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class ControleurRules(vue : MainVue): EventHandler<ActionEvent> {
    val vue : MainVue

    init {
        this.vue = vue
    }

    override fun handle(p0: ActionEvent?) {
        vue.rulesUpdate()
        vue.fixeListenerBouton(vue.buttonBottomRules,ControleurRetour(vue))
    }
}
