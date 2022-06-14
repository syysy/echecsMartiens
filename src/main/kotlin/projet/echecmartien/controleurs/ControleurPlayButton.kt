package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.stage.Stage
import projet.echecmartien.AppliJeuEchecMartien
import projet.echecmartien.modele.*
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class ControleurPlayButton(oldVue: MainVue,modele : Jeu,primaryStage: Stage): EventHandler<ActionEvent> {

    val primaryStage : Stage
    val oldVue : MainVue
    val jeu : Jeu
    val newVue : JeuVue

    init {
        this.primaryStage = primaryStage
        this.oldVue = oldVue
        this.jeu = modele
        this.newVue = JeuVue()
    }

    fun setAsGrandPion(pion : Circle){
        pion.radius = 20.0
        pion.fill = Color.BLACK
        newVue.fixeListenerCase(pion,ControleurPlace(newVue,jeu))
    }

    fun setAsMoyenPion(pion : Circle){
        pion.radius = 10.0
        pion.fill = Color.BLACK
        newVue.fixeListenerCase(pion,ControleurPlace(newVue,jeu))
    }

    fun setAsPetitPion(pion : Circle){
        pion.radius = 5.0
        pion.fill = Color.BLACK
        newVue.fixeListenerCase(pion,ControleurPlace(newVue,jeu))
    }

    fun setAsNull(pion : Circle){
        pion.radius = 20.0
        pion.fill = Color.WHITE
        pion.removeEventFilter(MouseEvent.MOUSE_CLICKED, ControleurPlace(newVue,jeu))
    }

    override fun handle(p0: ActionEvent?) {
        if (oldVue.textFieldPseudo1.text.isBlank() || oldVue.textFieldPseudo2.text.isBlank() || oldVue.textFieldPseudo1.text == oldVue.textFieldPseudo2.text || oldVue.textFieldPseudo2.text == "BOT"){
            val dialog = Alert(Alert.AlertType.INFORMATION)
            dialog.title = "INFORMATION"
            dialog.headerText = "CHAMPS NON CORRECT"
            dialog.contentText = "Veuillez remplir le nom des deux jouerrs ou ne pas mettre des noms égaux"
            dialog.showAndWait()
        } else {
            val scene = Scene(newVue, 500.0, 800.0)
            primaryStage.scene = scene
            primaryStage.centerOnScreen()
            newVue.joueur1.text = oldVue.textFieldPseudo1.text
            newVue.joueur2.text = oldVue.textFieldPseudo2.text

            //initialisation du jeu
            var row: Int
            var column: Int
            jeu.initialiserPartie(Joueur(newVue.joueur1.text), Joueur(newVue.joueur2.text), jeu.getNombreCoupsMax())
            //playerturn
            if (Joueur(newVue.joueur1.text) == jeu.getJoueurCourant()) {
                newVue.joueur1.style = "-fx-font-weight : bold;"
                newVue.joueur2.style = ""
            } else {
                newVue.joueur2.style = "-fx-font-weight : bold;"
                newVue.joueur1.style = ""
            }
            for (i in newVue.grille.children) {
                if (i !is Group) {
                    row = GridPane.getColumnIndex(i)
                    column = GridPane.getRowIndex(i)
                    if (i is Circle) {
                        if (jeu.plateau.getCases()[row][column].getPion() is MoyenPion) {
                            setAsMoyenPion(i)
                        } else if (jeu.plateau.getCases()[row][column].getPion() is GrandPion) {
                            setAsGrandPion(i)
                        } else if (jeu.plateau.getCases()[row][column].getPion() is PetitPion) {
                            setAsPetitPion(i)
                        } else {
                            setAsNull(i)
                        }

                    }
                }

            }
            newVue.fixeListenerBouton(newVue.boutonReset, ControleurReset(MainVue(), jeu, primaryStage))
            newVue.fixeListenerBouton(newVue.boutonRegles, ControleurRulesJeu(newVue, jeu, primaryStage))
            newVue.fixeListenerBouton(newVue.boutonCharge, ControleurSave(newVue, jeu, primaryStage))
            newVue.fixeListenerBouton(newVue.boutonSave, ControleurChargerSave(MainVue(), jeu, primaryStage))
        }
    }

}