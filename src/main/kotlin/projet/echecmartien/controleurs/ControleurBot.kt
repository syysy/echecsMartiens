package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.MainVue

class ControleurBot(vue: MainVue,modele: Jeu, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : MainVue
    val primaryStage : Stage
    val jeu : Jeu

    init {
        this.vue = vue
        this.primaryStage = primaryStage
        this.jeu = modele
    }

    override fun handle(p0: ActionEvent?) {
        vue.savePseudo2 = vue.textFieldPseudo2.text
        vue.textFieldPseudo2.text = "BOT"
        vue.textFieldPseudo2.style = "-fx-background-color:lightgray"
        vue.textFieldPseudo2.isEditable = false
        vue.botButton.text = "Retour"
        vue.iActive = true
        vue.fixeListenerBouton(vue.botButton,ControleurRetour(vue,jeu,primaryStage))
    }
}