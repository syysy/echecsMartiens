package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.shape.Circle
import javafx.stage.Stage
import projet.echecmartien.AppliJeuEchecMartien
import projet.echecmartien.modele.*
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue

class ControleurPlayButton(oldVue: MainVue,modele : Jeu,primaryStage: Stage): EventHandler<ActionEvent> {

    val primaryStage : Stage
    private val oldVue : MainVue
    val jeu : Jeu
    private val newVue : JeuVue

    init {
        this.primaryStage = primaryStage
        this.oldVue = oldVue
        this.jeu = modele
        this.newVue = JeuVue()
    }



    override fun handle(p0: ActionEvent?) {
        val scene = Scene(newVue,600.0,900.0)
        primaryStage.scene = scene
        primaryStage.centerOnScreen()
        newVue.joueur1.text = oldVue.textFieldPseudo1.text
        newVue.joueur2.text = oldVue.textFieldPseudo2.text
        newVue.IActive = oldVue.iActive
        //initialisation du jeu
        var row : Int
        var column : Int
        jeu.initialiserPartie(Joueur(newVue.joueur1.text), Joueur(newVue.joueur2.text),jeu.getNombreCoupsMax())
        //playerturn
        newVue.changeJoueurStyl(jeu)
        for (i in newVue.grille.children){
            if (i !is Group){
                row = GridPane.getColumnIndex(i)
                column = GridPane.getRowIndex(i)
                if (i is Circle){
                    if (jeu.plateau.getCases()[row][column].getPion() is MoyenPion){
                        newVue.setAsMoyenPion(i,jeu)
                    }else if (jeu.plateau.getCases()[row][column].getPion() is GrandPion){
                        newVue.setAsGrandPion(i,jeu)
                    }else if (jeu.plateau.getCases()[row][column].getPion() is PetitPion){
                        newVue.setAsPetitPion(i,jeu)
                    }else{
                        newVue.setAsNull(i,jeu)
                    }

                }
            }
        }

        scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/style.css").toExternalForm())
        newVue.addStyle()
        newVue.fixeListenerBouton(newVue.boutonReset,ControleurReset(MainVue(),jeu,primaryStage))
        newVue.fixeListenerBouton(newVue.boutonRegles,ControleurRulesJeu(newVue,jeu,primaryStage))
        newVue.fixeListenerBouton(newVue.boutonSave,ControleurSave(newVue,jeu,primaryStage))
        newVue.fixeListenerBouton(newVue.boutonCharge,ControleurChargerSave(MainVue(),jeu,primaryStage))

    }

}