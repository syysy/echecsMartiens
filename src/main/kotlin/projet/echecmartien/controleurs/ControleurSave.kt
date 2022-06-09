package projet.echecmartien.controleurs

import com.google.gson.Gson
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.TextInputDialog
import javafx.stage.Stage
import projet.echecmartien.vue.MainVue
import java.io.FileWriter

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
            serialiser(result.get())
        }
    }
    fun serialiser(nomFichier : String) {
        val writer = FileWriter(nomFichier)
        Gson().toJson(this, writer)
        writer.flush()
        writer.close()
    }
}