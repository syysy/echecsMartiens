package projet.echecmartien.vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.*
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import java.io.File
import java.io.FileInputStream
import java.io.FileReader


class MainVue: BorderPane() {

    val loadButton = Button("Charger une partie")
    val botButton = Button("Jouer contre un robot")
    val rulesButton = Button("Règles")
    var textFieldPseudo1 : TextField
    var textFieldPseudo2 : TextField
    var savePseudo1 : String
    var savePseudo2 : String
    var playButton : Button
    var labelTop = Label("Echecs Martiens")
    var buttonBottomRules  = Button("Retour")
    var IActive : Boolean? = false


    init {

        // Titre en haut
        val flowPaneTop = FlowPane()
        labelTop.font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelTop.textFill = Color.WHITE
        flowPaneTop.alignment = Pos.CENTER
        labelTop.padding = Insets(30.0,0.0,30.0,0.0)
        flowPaneTop.children.add(labelTop)
        this.top = flowPaneTop

        // Zone du centre

        val gridPaneCenter = GridPane()
        val contrainteLine3 = RowConstraints()
        val contrainteLine1 = RowConstraints()
        val contrainteLine2 = RowConstraints()
        val contrainteColumn1 = ColumnConstraints()
        contrainteColumn1.halignment = HPos.CENTER
        contrainteLine3.valignment = VPos.CENTER
        textFieldPseudo1 = TextField("Joueur 1")
        textFieldPseudo1.alignment = Pos.CENTER
        savePseudo1 = textFieldPseudo1.text
        textFieldPseudo2 = TextField("Joueur 2")
        textFieldPseudo2.alignment = Pos.CENTER
        savePseudo2 = textFieldPseudo2.text
        playButton = Button("Jouer")
        playButton.alignment = Pos.CENTER
        gridPaneCenter.style ="-fx-font-size : 15 ;-fx-font-weight :bold;   -fx-border-color:lightgray "
        gridPaneCenter.add(textFieldPseudo1,0,1)
        gridPaneCenter.add(textFieldPseudo2,0,2)
        gridPaneCenter.add(playButton,0,3)
        gridPaneCenter.vgap = 10.0
        gridPaneCenter.alignment = Pos.CENTER
        gridPaneCenter.padding = Insets(30.0)
        gridPaneCenter.rowConstraints.addAll(contrainteLine1,contrainteLine2,contrainteLine3)
        gridPaneCenter.columnConstraints.add(contrainteColumn1)
        this.center = gridPaneCenter


        // Boutons en bas
        val flowPaneBoutons=FlowPane()
        flowPaneBoutons.hgap=10.0
        flowPaneBoutons.vgap=10.0
        flowPaneBoutons.alignment = Pos.CENTER
        flowPaneBoutons.padding=Insets(30.0,0.0,40.0,0.0)
        buttonBottomRules.isDisable = true
        buttonBottomRules.isVisible = false
        buttonBottomRules.isCancelButton = true
        flowPaneBoutons.children.addAll(loadButton,botButton,rulesButton)
        this.bottom=flowPaneBoutons

        // Vbox côtés
        val vboxLeft = VBox()
        vboxLeft.padding = Insets(0.0,40.0,0.0,0.0)
        this.left = vboxLeft
        val vboxRight = VBox()
        vboxRight.padding = Insets(0.0,0.0,0.0,40.0)
        this.right = vboxRight
        /*val input = FileInputStream("./sauvegarde/pngtree-flat-space-rocket-background-image_149296.png")
        val img = Image(input)
        val backgroundImage = BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER, BackgroundSize.DEFAULT)
        val bckgrnd = Background(backgroundImage)
        this.background = bckgrnd*/
        // -fx-background-image: url('sauvegarde/pngtree-flat-space-rocket-background-image_149296.png')
        this.style = ("-fx-background-color: #383344; -fx-background-repeat: no-repeat; -fx-background-size: 500 600; -fx-background-position: center center;")
        loadButton.style = "-fx-background-color: #222740; \n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;"
        botButton.style = "-fx-background-color: #222740;\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;"
        rulesButton.style = "-fx-background-color: #222740;\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;"
        playButton.style =  "-fx-background-color: #201d27;\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-padding: 10 20 10 20;"
    }

    fun fixeListenerBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }




}

