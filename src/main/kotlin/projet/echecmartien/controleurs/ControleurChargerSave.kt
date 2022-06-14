package projet.echecmartien.controleurs

import com.google.gson.Gson
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.modele.*
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
            val readJson = Gson().fromJson(reader, String::class.java)
            var extent = ""
            var joueur1points : Int? = null
            var joueur2points : Int? = null
            var joueur1name = ""
            var joueur2name = ""
            var joueurCourantName = ""
            var listPion1 = mutableSetOf<Pion>()
            var listPion2 = mutableSetOf<Pion>()
            for (i in readJson.indices) {
                extent += readJson[i]
                if (countPipe(extent) == 1 && readJson[i] != '|'){
                    joueur1name += readJson[i]
                }
                if (countPipe(extent) == 3 && readJson[i] != '|'){
                    joueur2name += readJson[i]
                }
                if (countPipe(extent) == 5 && readJson[i] != '|'){
                    joueurCourantName += readJson[i]
                }
                if (countSlash(extent) == 1 && joueur1points !is Int){
                    joueur1points = Integer.parseInt(extent[i-1].toString())
                }else if (countSlash(extent) == 2 && joueur2points !is Int){
                    joueur2points = Integer.parseInt(extent[i-1].toString())
                }
                if (countHashtag(extent) == 1){
                    if (extent[i] == '1'){
                        listPion1.add(PetitPion())
                    }else if(extent[i] == '2'){
                        listPion1.add(MoyenPion())
                    }else{
                        listPion1.add(GrandPion())
                    }
                }
                if (countHashtag(extent) == 3){
                    if (extent[i] == '1'){
                        listPion2.add(PetitPion())
                    }else if(extent[i] == '2'){
                        listPion2.add(MoyenPion())
                    }else{
                        listPion2.add(GrandPion())
                    }
                }
            }
            println("/Joueur 1/ $joueur1name")
            println("/Joueur 2/ $joueur2name")
            println("/Points 1/ $joueur1points")
            println("/Points 2/ $joueur2points")
            println("/Joueur courant $joueurCourantName")
            println("/Pions Joueur 1/ $listPion1")
            println("/Pions Joueur 2/ $listPion2")

            jeu.initialiserPartie(
                Joueur(joueur1name),
                Joueur(joueur2name), jeu.getNombreCoupsMax())

            jeu.getJoueurCourant()!!.nom = joueurCourantName
            jeu.getJoueur()[0].pionCapture = listPion1
            jeu.getJoueur()[1].pionCapture = listPion2

        }
    }
    fun scanner(file : String){
        val scanner = Scanner(File(file))
        while (scanner.hasNextLine()){
            val contenu = scanner.nextLine()

        }
    }

    fun countSlash(extent : String) : Int{
        var count = 0
        for (i in extent){
            if (i == '/'){
                count += 1
            }
        }
        return count
    }

    fun countPipe(extent : String) : Int{
        var count = 0
        for (i in extent){
            if (i == '|'){
                count += 1
            }
        }
        return count
    }

    fun countHashtag(extent : String) : Int{
        var count = 0
        for (i in extent){
            if (i == '#'){
                count += 1
            }
        }
        return count
    }
}