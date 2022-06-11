package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.vue.MainVue
import java.io.File
import java.util.*

class ControleurChargerSave(vue: MainVue, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : MainVue
    val primaryStage : Stage

    init {
        this.vue = vue
        this.primaryStage = primaryStage
    }
    override fun handle(p0: ActionEvent?) {
        val file = FileChooser()
        val result = file.showOpenDialog(primaryStage)
        if (result != null){
            println(result)
        }
    }
    fun scanner(file : String){
        val scanner = Scanner(File(file))
        while (scanner.hasNextLine()){
            val contenu = scanner.nextLine()

        }
    }
}