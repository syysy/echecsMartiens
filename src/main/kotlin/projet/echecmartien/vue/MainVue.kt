package projet.echecmartien.vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.FlowPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.RowConstraints
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight


class MainVue: BorderPane() {

    val loadButton = Button("Charger une partie")
    val botButton = Button("Jouer contre un robot")
    val rulesButton = Button("Règles")
    var textFieldPseudo1 : TextField
    var textFieldPseudo2 : TextField
    var savePseudo2 : String
    var playButton : Button
    var labelTop = Label("Echecs Martiens")
    var buttonBottomRules  = Button("Retour")

    init {

        // Titre en haut
        val flowPaneTop = FlowPane()
        labelTop.font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelTop.textFill = Color.BLACK
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
        flowPaneBoutons.alignment = Pos.CENTER
        flowPaneBoutons.padding=Insets(30.0,0.0,40.0,0.0)
        buttonBottomRules.isDisable = true
        buttonBottomRules.isVisible = false
        flowPaneBoutons.children.addAll(loadButton,botButton,rulesButton, buttonBottomRules)
        this.bottom=flowPaneBoutons

        // Vbox côtés
        val vboxLeft = VBox()
        vboxLeft.padding = Insets(0.0,40.0,0.0,0.0)
        this.left = vboxLeft
        val vboxRight = VBox()
        vboxRight.padding = Insets(0.0,0.0,0.0,40.0)
        this.right = vboxRight
    }

    fun fixeListenerBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }

    fun originUpdate() {
        // Titre en haut
        val flowPaneTop = FlowPane()
        labelTop.font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelTop.textFill = Color.BLACK
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
        flowPaneBoutons.alignment = Pos.CENTER
        flowPaneBoutons.padding=Insets(30.0,0.0,40.0,0.0)
        buttonBottomRules.isDisable = true
        buttonBottomRules.isVisible = false
        flowPaneBoutons.children.addAll(loadButton,botButton,rulesButton,buttonBottomRules)
        this.bottom=flowPaneBoutons

        // Vbox côtés
        val vboxLeft = VBox()
        vboxLeft.padding = Insets(0.0,40.0,0.0,0.0)
        this.left = vboxLeft
        val vboxRight = VBox()
        vboxRight.padding = Insets(0.0,0.0,0.0,40.0)
        this.right = vboxRight
    }

    fun rulesUpdate() {
        this.labelTop = Label("Règles de l'échec martien")
        this.center = Label("PREPARATION\n" +
                "Disposez les 18 pions comme sur la figure ci-contre.\n" +
                "Un joueur identifie ses pièces par leur position à un instant donné.\n" +
                "Le damier est divisé en 2 zones, une pour chaque joueur. Toute pièce dans la zone d'un joueur est la sienne.\n" +
                "\n" +
                "DEROULEMENT DU JEU\n" +
                "Chaque joueur, à son tour de jeu, déplace une de ses pièces.\n" +
                "Les grands pions se déplacent verticalement, horizontalement et diagonalement de n cases (comme la dame aux échecs traditionnel).\n" +
                "Les pions moyens se déplacent verticalement, horizontalement et diagonalement de 1 ou 2 cases.\n" +
                "Les petits pions se déplacent diagonalement de 1 case.\n" +
                "A son tour de jeu un joueur peut déplacer n'importe quel pion de son camp, soit à l'intérieur de sa zone soit vers la zone adverse. \t\n" +
                "Exception: Il est interdit de renvoyer dans la zone adverse un pion qui vient d'arriver dans sa zone. Mais on peut déplacer ce même pion à l'intérieur de sa zone\n" +
                "\n" +
                "On capture un pion adverse en prenant sa place (donc fatalement en prenant un pion de sa zone et en allant dans la zone adverse). Le pion capturé est retiré du damier..\n" +
                "Le saut par dessus un ou n pions adverses ou non n'est pas autorisé.\n" +
                "\n" +
                "FIN DE LA PARTIE\n" +
                "Une fois la partie finie (plus de pions à capturer car ils sont tous capturés ou plus aucunes prises n'est possibles), on compte 3 points par grand pion capturés, 2 par moyen et 1 par petit.\n" +
                "\n" +
                "Le gagnant est évidement le joueur qui à le plus de points")
        this.buttonBottomRules.isDisable = false
        this.buttonBottomRules.isVisible = true
        val neoFlow = FlowPane()
        this.bottom = neoFlow
        neoFlow.children.add(buttonBottomRules)
        neoFlow.padding = Insets(50.0,0.0,50.0,500.0)
    }
}