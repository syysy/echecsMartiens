package projet.echecmartien.controleurs

import com.google.gson.Gson
import com.google.gson.JsonObject
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
    val modele : Jeu
    init {
        this.vue = vue
        this.primaryStage = primaryStage
        this.modele = modele
    }
    override fun handle(p0: ActionEvent?) {
        val file = FileChooser()
        val result = file.showOpenDialog(primaryStage)
        if (result != null){
            println(result)
            val reader = FileReader(result)
            val readJson = Gson().fromJson(reader, JsonObject::class.java)
            modele.initialiserPartie(Joueur(readJson["joueur1"].toString()),
                Joueur(readJson["joueur2"].toString()), modele.getNombreCoupsMax())
            modele.getJoueurCourant()!!.nom = readJson["jCourant"].toString()
            if (modele.getJoueurCourant()!!.nom == readJson["joueur1"].toString() ){
                Joueur(readJson["joueur1"].toString()).pionCapture
            }
        }
    }
    fun scanner(file : String){
        val scanner = Scanner(File(file))
        while (scanner.hasNextLine()){
            val contenu = scanner.nextLine()

        }
    }
}