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
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
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
    }

    fun fixeListenerBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }

    fun originUpdate() {
        this.labelTop = Label("Echecs Martiens")
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
        buttonBottomRules.isCancelButton = true
        buttonBottomRules.isVisible = false
        flowPaneBoutons.children.addAll(loadButton,botButton,rulesButton)
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

    fun jeuUpdate(){
        var labelTop = Label("Echecs Martiens")

        val centre = VBox()
        val info1 = HBox()
        val info2 = HBox()
        val joueur1 = Label("joueur1")
        val joueur2 = Label("joueur2")
        val point1 = Label("0 points")
        val point2 = Label("0 points")

        val pions1 = VBox()
        val grand1 = Circle()
        val moyen1 = Circle()
        val petit1 = Circle()

        val pions2 = VBox()
        val grand2 = Circle()
        val moyen2 = Circle()
        val petit2 = Circle()

        val grille = GridPane()

        val bot = GridPane()
        val compteTour = Label("Tour 1")
        val boutonCharge = Button("Charger")
        val boutonSave = Button("Save")
        val boutonRegles = Button("Règles")
        val boutonReset = Button("Reset")

        // Titre en haut
        val flowPaneTop = FlowPane()
        labelTop.font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelTop.textFill = Color.BLACK
        flowPaneTop.alignment = Pos.CENTER
        labelTop.padding = Insets(30.0,0.0,30.0,0.0)
        flowPaneTop.children.add(labelTop)
        this.top = flowPaneTop

        pions1.children.addAll(grand1,moyen1,petit1)
        pions2.children.addAll(grand2,moyen2,petit2)
        this.left = pions1
        this.right = pions2

        // Centre
        info1.children.addAll(joueur1,point1)
        info1.spacing = 160.0
        info1.padding = Insets(10.0)
        info2.children.addAll(joueur2,point2)
        info2.spacing = 160.0
        info2.padding = Insets(10.0)
        centre.children.addAll(info1,grille,info2)
        centre.alignment = Pos.CENTER
        centre.padding = Insets(0.0,30.0,30.0,30.0)
        this.center = centre


        // Boutons du bas
        compteTour.style = " -fx-font-size : 15 ;-fx-font-weight :bold"
        bot.add(compteTour,1,0)
        bot.add(boutonCharge,0,1)
        bot.add(boutonSave,0,2)
        bot.add(boutonRegles,2,1)
        bot.add(boutonReset,2,2)
        bot.vgap = 20.0
        bot.hgap = 50.0
        bot.padding = Insets(30.0)
        bot.alignment = Pos.CENTER
        this.bottom = bot


        for (i in 0 until 4){
            for (j in 0 until 8){
                val case = Button()
                case.isVisible = false
                case.maxHeight = 30.0
                case.maxWidth = 30.0
                grille.style = "-fx-border-color : blue;-border-width:1"
                var cercle = Circle()
                cercle.radius = 10.0

                grille.add(cercle,i,j)
                grille.add(case,i,j)
                }
            }

        this.padding = Insets(10.0)


        }
}

