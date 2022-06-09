package projet.echecmartien.controleurs

import com.google.gson.Gson
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.TextInputDialog
import javafx.stage.Stage
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import java.io.FileWriter

class ControleurSave(vue: JeuVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : JeuVue
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
            val message = Message(result.get(),"${vue.joueur1.text},${vue.joueur2.text} ")
            message.serialiser("sauvegarde/${result.get()}.json")
        }
    }

}