package projet.echecmartien.vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import projet.echecmartien.controleurs.ControleurPlace
import projet.echecmartien.controleurs.ControleurVide
import projet.echecmartien.modele.*


class JeuVue(
    var joueur1:Label = Label("joueur1"),
    var joueur2:Label = Label("joueur2"),


    var point1:Label= Label(" 0 points"),
    var point2:Label= Label(" 0 points"),

    val nbPetit:Label= Label("0"),
    val nbMoyen:Label= Label("0"),
    val nbGrand:Label= Label("0"),

    val nbPetit2:Label= Label("0"),
    val nbMoyen2:Label= Label("0"),
    val nbGrand2:Label= Label("0"),

    val compteTour :Label= Label("Tour 1"),
    var nbTour : Int = 1,
    val tourSansPrises :Label= Label("Tours sans prises : 0"),
    var IActive : Boolean? = false,
    ) : BorderPane() {

    val grille :GridPane= GridPane()

    private val bot :GridPane= GridPane()

    val boutonCharge :Button= Button("Charger")
    val boutonSave :Button= Button("Save")
    val boutonRegles :Button= Button("Règles")
    val boutonReset :Button= Button("Reset")
    private var pts1 :Label= Label("0")
    private var pts2 :Label= Label("0")
    private var labelTop :Label = Label("Echecs Martiens")
    private val centre :VBox = VBox()
    private val info1 :HBox= HBox()
    private val info2 :HBox= HBox()
    var savePseudo1 : String
    var savePseudo2 : String
    var endGame : Label = Label("Partie finie, veuillez cliquer sur Reset ")

    init{

        // Titre en haut
        val flowPaneTop = FlowPane()
        labelTop.font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelTop.textFill = Color.WHITE
        flowPaneTop.alignment = Pos.CENTER
        labelTop.padding = Insets(30.0,0.0,20.0,0.0)
        flowPaneTop.children.add(labelTop)
        this.top = flowPaneTop

        pts2.textFill = Color.WHITE
        pts1.textFill = Color.WHITE
        tourSansPrises.textFill = Color.WHITE
        compteTour.textFill = Color.WHITE
        joueur1.textFill = Color.WHITE
        joueur2.textFill = Color.WHITE
        point1.textFill = Color.WHITE
        point2.textFill = Color.WHITE
        nbGrand.textFill = Color.WHITE
        nbGrand2.textFill = Color.WHITE
        nbMoyen2.textFill = Color.WHITE
        nbPetit2.textFill = Color.WHITE
        nbMoyen.textFill = Color.WHITE
        nbPetit.textFill = Color.WHITE

        // Centre
        val points1box =HBox()
        points1box.children.addAll(pts1,point1)
        val points2box =HBox()
        points2box.children.addAll(pts2,point2)
        savePseudo1 = joueur1.text
        info1.children.addAll(joueur1,point1)
        info1.spacing = 250.0
        info1.padding = Insets(10.0)
        savePseudo2 = joueur2.text
        info2.children.addAll(joueur2,point2)
        info2.spacing = 250.0
        info2.padding = Insets(10.0)
        endGame.style = " -fx-font-size : 15 ;-fx-font-weight :bold; -fx-text-fill: red"
        endGame.isVisible = false
        centre.children.addAll(info1,grille,info2,endGame)
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
                grille.style = "-fx-border-color : black;-border-width:2"
                val cercle = Circle()
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
        grille.columnConstraints.addAll(colonne1,colonne2,colonne3,colonne4)
        grille.rowConstraints.addAll(ligne1,ligne2,ligne3,ligne4,ligne5,ligne6,ligne7,ligne8)
        grille.isGridLinesVisible = true
        this.padding = Insets(10.0)

        grille.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))


        // Compte des pions sur les côtés

        val leftgrid = GridPane()
        val vboxLeft1 = VBox()
        val petit = Circle()
        petit.radius = 5.0
        petit.fill = Color.ROSYBROWN
        val moyen = Circle()
        moyen.radius = 10.0
        moyen.fill = Color.SANDYBROWN
        val grand = Circle()
        grand.radius = 20.0
        grand.fill = Color.SADDLEBROWN
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
        petit2.fill = Color.ROSYBROWN
        val moyen2 = Circle()
        moyen2.radius = 10.0
        moyen2.fill = Color.SANDYBROWN
        val grand2 = Circle()
        grand2.radius = 20.0
        grand2.fill = Color.SADDLEBROWN
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

        IActive = (joueur1.text == "BOT")

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
        this.fixeListenerCase(pion,ControleurVide())
    }

    fun setAsGrandPion(pion : Circle,jeu :Jeu){
        pion.radius = 20.0
        pion.fill = Color.SADDLEBROWN
        this.fixeListenerCase(pion,ControleurPlace(this,jeu))
    }

    fun setAsMoyenPion(pion : Circle,jeu :Jeu){
        pion.radius = 10.0
        pion.fill = Color.SANDYBROWN
        this.fixeListenerCase(pion,ControleurPlace(this,jeu))
    }

    fun setAsPetitPion(pion : Circle,jeu :Jeu){
        pion.radius = 5.0
        pion.fill = Color.ROSYBROWN
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
                        this.fixeListenerCase(this.grille.children[j*(this.grille.rowCount)+i] as Circle,ControleurPlace(this,jeu))
                        (this.grille.children[j*(this.grille.rowCount)+i] as Circle).removeEventFilter(MouseEvent.MOUSE_CLICKED, ControleurPlace(this,jeu))
                    }else{
                        setAsPetitPion(this.grille.children[j*(this.grille.rowCount)+i] as Circle,jeu)
                    }
                }

            }
        }
    }
    fun chargement(jeu : Jeu,jCourant : String, plateau: Plateau, listPion1 : MutableSet<Pion>, listPion2 : MutableSet<Pion> , IActive: Boolean){
        jeu.getJoueur()[0] = Joueur(joueur1.text)
        jeu.getJoueur()[1] = Joueur(joueur2.text)
        jeu.initialiserJoueur(jeu.getJoueur()[0],jeu.getJoueur()[1])
        if (jeu.getJoueurCourant()!!.nom != jCourant){
            jeu.changeJoueurCourant()
        }
        jeu.plateau = plateau
        jeu.getJoueur()[0].pionCapture = listPion1
        jeu.getJoueur()[1].pionCapture = listPion2
        this.IActive = IActive

    }

    fun addStyle(){
        this.styleClass.add("jeu2")
        boutonSave.styleClass.add("bouton")
        boutonReset.styleClass.add("bouton")
        boutonRegles.styleClass.add("bouton")
        boutonCharge.styleClass.add("bouton")
    }

    fun changeJoueurStyl(jeu : Jeu){
        if (Joueur(this.joueur1.text) == jeu.getJoueurCourant()){
            this.joueur1.style = "-fx-font-weight : bold; -fx-text-fill : red;"
            this.joueur2.style = ""
        }else{
            this.joueur2.style = "-fx-font-weight : bold; -fx-text-fill : red;"
            this.joueur1.style = ""
        }
    }
}

