package projet.echecmartien.vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import projet.echecmartien.controleurs.ControleurPlace
import projet.echecmartien.modele.Jeu

class JeuVue() : BorderPane() {
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

    init{
        // Titre en haut
        val flowPaneTop = FlowPane()
        labelTop.font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelTop.textFill = Color.BLACK
        flowPaneTop.alignment = Pos.CENTER
        labelTop.padding = Insets(30.0,0.0,30.0,0.0)
        flowPaneTop.children.add(labelTop)
        this.top = flowPaneTop

        this.pions1.children.addAll(grand1,moyen1,petit1)
        this.pions2.children.addAll(grand2,moyen2,petit2)
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
                var cercle = Circle()
                grille.style = "-fx-border-color : blue;-border-width:1"
                cercle.radius = 30.0
                fixeListenercase(cercle,ControleurPlace(this))
                grille.add(cercle,i,j)
            }
        }
        val colonne1 = ColumnConstraints()
        val colonne2 = ColumnConstraints()
        val colonne3 = ColumnConstraints()
        val colonne4 = ColumnConstraints()
        colonne1.percentWidth = 25.0
        colonne2.percentWidth = 25.0
        colonne3.percentWidth = 25.0
        colonne4.percentWidth = 25.0
        grille.columnConstraints.addAll(colonne1,colonne2,colonne3,colonne4)
        this.padding = Insets(10.0)

    }

    fun fixeListenercase(case: Circle, action: EventHandler<MouseEvent>) {
        case.onMouseClicked = action
    }

    fun initialisePartie(){
        val jeu = Jeu()
        jeu.initialiserPartie()
    }

    fun setAsGrandPion(pion: Circle){
        pion.radius = 30.0
    }
    fun setAsMoyenPion(pion: Circle){
        pion.radius = 20.0
    }
    fun setAsPetitPion(pion: Circle){
        pion.radius = 10.0
    }
    fun setAsNull(pion: Circle){
        pion.radius = 0.0
    }

}