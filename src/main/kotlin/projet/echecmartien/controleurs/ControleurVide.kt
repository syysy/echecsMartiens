package projet.echecmartien.controleurs

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

class ControleurVide: EventHandler<MouseEvent> {
    override fun handle(p0: MouseEvent?) {
        println("case invalide")
    }

}
