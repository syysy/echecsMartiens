package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.AppliJeuEchecMartien
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue

class ControleurRetourRulesJeu (vue: JeuVue, modele:Jeu, primaryStage: Stage): EventHandler<ActionEvent> {

    val primaryStage : Stage
    val vue : JeuVue
    val jeu : Jeu

    init {
        this.primaryStage = primaryStage
        this.vue = vue
        this.jeu = modele
    }

    override fun handle(p0: ActionEvent?) {
        val new = JeuVue()
        new.joueur1.text = vue.savePseudo1
        new.joueur2.text = vue.savePseudo2
        new.point1.text = vue.point1.text
        new.point2.text = vue.point2.text
        new.compteTour.text = vue.compteTour.text
        new.nbPetit.text = vue.nbPetit.text
        new.nbMoyen.text = vue.nbMoyen.text
        new.nbGrand.text = vue.nbGrand.text
        new.nbPetit2.text = vue.nbPetit2.text
        new.nbMoyen2.text = vue.nbMoyen2.text
        new.nbGrand2.text = vue.nbGrand2.text
        new.changeJoueurStyl(jeu)
        new.update(jeu)
        primaryStage.scene = Scene(new,600.0,800.0)
        primaryStage.centerOnScreen()
        primaryStage.scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/style.css").toExternalForm())
        new.addStyle()
        new.fixeListenerBouton(new.boutonReset,ControleurReset(MainVue(),jeu,primaryStage))
        new.fixeListenerBouton(new.boutonRegles,ControleurRulesJeu(new,jeu,primaryStage))
    }
}