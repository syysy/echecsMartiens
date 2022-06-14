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
import javafx.scene.text.TextAlignment
import org.controlsfx.control.spreadsheet.Grid
import projet.echecmartien.controleurs.ControleurPlace
import projet.echecmartien.modele.*

class JeuVue() : BorderPane() {
    var labelTop = Label("Echecs Martiens")

    val centre = VBox()
    val info1 = HBox()
    val info2 = HBox()
    val joueur1 = Label("joueur1")
    var savePseudo1 : String
    val joueur2 = Label("joueur2")
    var savePseudo2 : String
    var pts1 = Label("0")
    var pts2 = Label("0")
    val point1 = Label(" 0 points")
    val point2 = Label(" 0 points")

    val grille = GridPane()

    val bot = GridPane()
    val compteTour = Label("Tour 1")
    val boutonCharge = Button("Charger")
    val boutonSave = Button("Save")
    val boutonRegles = Button("Règles")
    val boutonReset = Button("Reset")

    val nbPetit = Label("0")
    val nbMoyen = Label("0")
    val nbGrand = Label("0")

    val nbPetit2 = Label("0")
    val nbMoyen2 = Label("0")
    val nbGrand2 = Label("0")

    var nbTour = 1
    val tourSansPrises = Label("Tours sans prises : 0")

    init{
        // Titre en haut
        val flowPaneTop = FlowPane()
        labelTop.font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelTop.textFill = Color.BLACK
        flowPaneTop.alignment = Pos.CENTER
        labelTop.padding = Insets(30.0,0.0,30.0,0.0)
        flowPaneTop.children.add(labelTop)
        this.top = flowPaneTop


        // Centre
        val points1box =HBox()
        points1box.children.addAll(pts1,point1)
        val points2box =HBox()
        points2box.children.addAll(pts2,point2)
        savePseudo1 = joueur1.text
        info1.children.addAll(joueur1,point1)
        info1.spacing = 160.0
        info1.padding = Insets(10.0)
        savePseudo2 = joueur2.text
        info2.children.addAll(joueur2,point2)
        info2.spacing = 160.0
        info2.padding = Insets(10.0)
        centre.children.addAll(info1,grille,info2)
        centre.alignment = Pos.CENTER
        centre.padding = Insets(0.0,30.0,30.0,30.0)
        this.center = centre


        // Boutons du bas
        compteTour.style = " -fx-font-size : 15 ;-fx-font-weight :bold"
        tourSansPrises.style = " -fx-font-size : 15 ;-fx-font-weight :bold"
        bot.add(compteTour,1,0)
        bot.add(boutonCharge,0,2)
        bot.add(boutonSave,0,3)
        bot.add(boutonRegles,2,2)
        bot.add(boutonReset,2,3)
        bot.add(tourSansPrises,1,1)
        GridPane.setHalignment(compteTour,HPos.CENTER)
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


        // Compte des pions sur les côtés
        val leftgrid = GridPane()
        val vboxLeft1 = VBox()
        val petit = Circle()
        petit.radius = 5.0
        val moyen = Circle()
        moyen.radius = 10.0
        val grand = Circle()
        grand.radius = 20.0
        vboxLeft1.children.addAll(petit,moyen,grand)
        vboxLeft1.alignment = Pos.BOTTOM_CENTER
        vboxLeft1.spacing = 10.0
        leftgrid.padding = Insets(330.0,0.0,0.0,10.0)
        val vboxLeft2 = VBox()

        vboxLeft2.spacing = 18.0
        vboxLeft2.children.addAll(nbPetit,nbMoyen,nbGrand)
        leftgrid.hgap = 10.0
        leftgrid.add(vboxLeft1,0,0)
        leftgrid.add(vboxLeft2,1,0)
        this.left = leftgrid

        val rightgrid = GridPane()
        val vboxRight1 = VBox()
        val petit2 = Circle()
        petit2.radius = 5.0
        val moyen2 = Circle()
        moyen2.radius = 10.0
        val grand2 = Circle()
        grand2.radius = 20.0
        vboxRight1.children.addAll(grand2,moyen2,petit2)
        vboxRight1.alignment = Pos.TOP_CENTER
        vboxRight1.spacing = 13.0
        vboxRight1.padding = Insets(75.0,0.0,0.0,0.0)
        val vboxRight2 = VBox()

        vboxRight2.spacing = 18.0
        vboxRight2.padding = Insets(90.0,0.0,0.0,0.0)
        vboxRight2.children.addAll(nbGrand2,nbMoyen2,nbPetit2)
        rightgrid.hgap = 10.0
        rightgrid.add(vboxRight1,1,0)
        rightgrid.add(vboxRight2,0,0)
        this.right = rightgrid

    }



    fun fixeListenerCase(case : Circle, action: EventHandler<MouseEvent>) {
        case.onMouseClicked = action
    }


    fun fixeListenerBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }

    fun setAsNull(pion : Circle, jeu : Jeu){
        pion.radius = 20.0
        pion.fill = Color.WHITE
        pion.removeEventFilter(MouseEvent.MOUSE_CLICKED, ControleurPlace(this,jeu))
    }

    fun setAsGrandPion(pion : Circle,jeu :Jeu){
        pion.radius = 20.0
        pion.fill = Color.BLACK
        this.fixeListenerCase(pion,ControleurPlace(this,jeu))
    }

    fun setAsMoyenPion(pion : Circle,jeu :Jeu){
        pion.radius = 10.0
        pion.fill = Color.BLACK
        this.fixeListenerCase(pion,ControleurPlace(this,jeu))
    }

    fun setAsPetitPion(pion : Circle,jeu :Jeu){
        pion.radius = 5.0
        pion.fill = Color.BLACK
        this.fixeListenerCase(pion,ControleurPlace(this,jeu))
    }

    fun update( jeu : Jeu){
        for (i in 0 until 8){
            for (j in 0 until 4){
                if (jeu.plateau.getCases()[j][i].getPion() == null){
                    setAsNull(this.grille.children[j*(this.grille.rowCount)+i] as Circle,jeu)
                }else{
                    if (jeu.plateau.getCases()[j][i].getJoueur() == jeu.getJoueurCourant()){
                        this.fixeListenerCase(this.grille.children[j*(this.grille.rowCount)+i] as Circle,ControleurPlace(this,jeu))
                    }else{
                        (this.grille.children[j*(this.grille.rowCount)+i] as Circle).removeEventFilter(MouseEvent.MOUSE_CLICKED, ControleurPlace(this,jeu))
                    }
                    if (jeu.plateau.getCases()[j][i].getPion() is MoyenPion){
                        setAsMoyenPion(this.grille.children[j*(this.grille.rowCount)+i] as Circle,jeu)
                    }else if (jeu.plateau.getCases()[j][i].getPion() is GrandPion){
                        setAsGrandPion(this.grille.children[j*(this.grille.rowCount)+i] as Circle,jeu)
                    }else{
                        setAsPetitPion(this.grille.children[j*(this.grille.rowCount)+i] as Circle,jeu)
                    }
                }
            }
        }
    }

}