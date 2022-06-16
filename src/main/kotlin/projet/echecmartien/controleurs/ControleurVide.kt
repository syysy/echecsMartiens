package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.MainVue

class ControleurVide(): EventHandler<MouseEvent> {
    override fun handle(p0: MouseEvent?) {
        println("case invalide")
    }

}
