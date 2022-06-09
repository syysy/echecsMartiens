package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.TextInputDialog
import javafx.stage.Stage
import projet.echecmartien.vue.MainVue

class ControleurSave(vue: MainVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : MainVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        val dialog = TextInputDialog()
        dialog.title = " Entrez un nom de fichier"
        dialog.contentText = "Nom :"
        val result = dialog.showAndWait()
        if (result.isPresent){
            
        }
    }
}