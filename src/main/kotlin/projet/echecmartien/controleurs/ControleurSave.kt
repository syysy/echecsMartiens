package projet.echecmartien.controleurs

import com.google.gson.Gson
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.TextInputDialog
import javafx.stage.Stage
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import java.io.FileWriter

open class ControleurSave(vue: JeuVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : JeuVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }

    override fun handle(p0: ActionEvent?) {
        val dialog = TextInputDialog()
        dialog.title = "Sauvegarde d'une partie"
        dialog.headerText = "Entrez un nom pour votre sauvegarde"
        dialog.contentText = "Nom :"
        val result = dialog.showAndWait()
        val titre = result.get()
        if (result.isPresent){
            val message = Message(titre,"${vue.joueur1.text},${vue.joueur2.text} ")
            println(message)
            message.serialiser("sauvegarde/$titre.json")
        }
    }

}