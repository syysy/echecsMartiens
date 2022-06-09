package projet.echecmartien.controleurs

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue

class ControleurPlace(vue :JeuVue) : EventHandler<MouseEvent>{
    private val vue = vue

    override fun handle(event: MouseEvent) {
        var row = GridPane.getRowIndex(event.source as Node)
        var column = GridPane.getColumnIndex(event.source as Node)
        print(vue.grille.children)
    }


}