package projet.echecmartien.controleurs

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.MainVue
import java.io.File
import java.io.FileReader
import java.util.*

class ControleurChargerSave(vue: MainVue,modele : Jeu, primaryStage: Stage): EventHandler<ActionEvent> {
    val vue : MainVue
    val primaryStage : Stage
    val jeu : Jeu
    init {
        this.vue = vue
        this.primaryStage = primaryStage
        this.jeu = modele
    }
    override fun handle(p0: ActionEvent?) {
        val file = FileChooser()
        val result = file.showOpenDialog(primaryStage)
        if (result != null){
            println(result)
            val reader = FileReader(result)
            val readJson = Gson().fromJson(reader, JsonPrimitive::class.java)
            println(readJson.asJsonObject["joueur1"])
            println(readJson)
            /*jeu.initialiserPartie(Joueur(readJson.toString()),
                Joueur(readJson["joueur2"].toString()), jeu.getNombreCoupsMax())

            jeu.getJoueurCourant()!!.nom = readJson["jCourant"].toString()
            if (jeu.getJoueurCourant()!!.nom == readJson["joueur1"].toString() ) {
                Joueur(readJson["joueur1"].toString()).pionCapture
            } */

        }
    }
    fun scanner(file : String){
        val scanner = Scanner(File(file))
        while (scanner.hasNextLine()){
            val contenu = scanner.nextLine()

        }
    }
}