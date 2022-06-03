package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.FlowPane
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight

class MainVue: BorderPane() {

    val loadButton = Button("Charger une partie")
    val botButton = Button("Jouer contre un robot")
    val rulesButton = Button("Règles")

    init {
        // Titre en haut
        val flowPaneTop = FlowPane()
        val labelTop = Label("Echecs Martiens")
        labelTop.font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelTop.textFill = Color.BLACK
        flowPaneTop.alignment = Pos.CENTER
        labelTop.padding = Insets(10.0)
        flowPaneTop.children.add(labelTop)
        this.top = flowPaneTop

        // Zone du centre

        val gridPaneCenter = GridPane()
        val textFieldPseudo1 = TextField("Joueur 1")
        val textFieldPseudo2 = TextField("Joueur 2")
        val playButton = Button("Jouer")
        gridPaneCenter.style ="-fx-font-size : 15 ;-fx-font-weight :bold;   -fx-border-color:lightgray "
        gridPaneCenter.add(textFieldPseudo1,0,1)
        gridPaneCenter.add(textFieldPseudo2,0,2)
        gridPaneCenter.add(playButton,0,3)
        gridPaneCenter.alignment = Pos.CENTER
        gridPaneCenter.padding = Insets(30.0)

        this.center = gridPaneCenter



        // Boutons en bas
        val flowPaneBoutons=FlowPane()
        flowPaneBoutons.hgap=10.0
        flowPaneBoutons.alignment = Pos.CENTER
        flowPaneBoutons.padding=Insets(10.0)
        flowPaneBoutons.children.addAll(loadButton,botButton,rulesButton)
        this.bottom=flowPaneBoutons
    }
}