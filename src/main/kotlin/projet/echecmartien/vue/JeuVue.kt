package projet.echecmartien.vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.event.EventType
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.Node
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
import projet.echecmartien.modele.*

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

    val jeu = Jeu()

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
                grille.style = "-fx-border-color : blue;-border-width:1"
                var cercle = Circle()
                cercle.radius = 20.0
                grille.add(cercle,i,j)
                if (j == 3){
                    cercle.style = "-fx-size:20px"
                }
            }
        }

        val colonne1 = ColumnConstraints()
        val colonne2 = ColumnConstraints()
        val colonne3 = ColumnConstraints()
        val colonne4 = ColumnConstraints()
        val ligne1 = RowConstraints()
        val ligne2 = RowConstraints()
        val ligne3 = RowConstraints()
        val ligne4 = RowConstraints()
        val ligne5 = RowConstraints()
        val ligne6 = RowConstraints()
        val ligne7 = RowConstraints()
        val ligne8 = RowConstraints()
        colonne1.percentWidth = 25.0
        colonne2.percentWidth = 25.0
        colonne3.percentWidth = 25.0
        colonne4.percentWidth = 25.0
        ligne1.percentHeight = 100.0/8
        ligne2.percentHeight = 100.0/8
        ligne3.percentHeight = 100.0/8
        ligne4.percentHeight = 100.0/8
        ligne5.percentHeight = 100.0/8
        ligne6.percentHeight = 100.0/8
        ligne7.percentHeight = 100.0/8
        ligne8.percentHeight = 100.0/8
        colonne1.halignment = HPos.CENTER
        colonne2.halignment = HPos.CENTER
        colonne3.halignment = HPos.CENTER
        colonne4.halignment = HPos.CENTER
        grille.vgap = 5.0


        grille.columnConstraints.addAll(colonne1,colonne2,colonne3,colonne4)
        grille.rowConstraints.addAll(ligne1,ligne2,ligne3,ligne4,ligne5,ligne6,ligne7,ligne8)
        grille.isGridLinesVisible = true
        this.padding = Insets(10.0)
        initialisationJeu()
    }

    fun initialisationJeu(){
        var row : Int
        var column : Int
        jeu.initialiserPartie(Joueur(joueur1.text), Joueur(joueur2.text),jeu.getNombreCoupsMax())
        for (i in grille.children){
            if (i !is Group){
                row = GridPane.getColumnIndex(i)
                column = GridPane.getRowIndex(i)
                if (i is Circle){
                    if (jeu.plateau.getCases()[row][column].getPion() is MoyenPion){
                        setAsMoyenPion(i)
                    }else if (jeu.plateau.getCases()[row][column].getPion() is GrandPion){
                        setAsGrandPion(i)
                    }else if (jeu.plateau.getCases()[row][column].getPion() is PetitPion){
                        setAsPetitPion(i)
                    }else{
                        setAsNull(i)
                    }

                }
            }

        }
    }

    fun fixeListenerCase(case : Circle, action: EventHandler<MouseEvent>) {
        case.onMouseClicked = action
    }


    fun setAsGrandPion(pion : Circle){
        pion.radius = 20.0
        pion.fill = Color.BLACK
        fixeListenerCase(pion,ControleurPlace(this))
    }

    fun setAsMoyenPion(pion : Circle){
        pion.radius = 10.0
        pion.fill = Color.BLACK
        fixeListenerCase(pion,ControleurPlace(this))
    }

    fun setAsPetitPion(pion : Circle){
        pion.radius = 5.0
        pion.fill = Color.BLACK
        fixeListenerCase(pion,ControleurPlace(this))
    }

    fun setAsNull(pion : Circle){
        pion.radius = 20.0
        pion.fill = Color.WHITE
        pion.removeEventFilter(MouseEvent.MOUSE_CLICKED, ControleurPlace(this))
    }






    fun fixeListenerBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }

}