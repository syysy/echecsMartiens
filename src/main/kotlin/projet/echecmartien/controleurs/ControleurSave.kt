package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.TextInputDialog
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Pion
import projet.echecmartien.vue.JeuVue


open class ControleurSave(vue: JeuVue, modele : Jeu ,primaryStage: Stage): EventHandler<ActionEvent> {

    val vue : JeuVue
    val jeu : Jeu
    val primaryStage : Stage

    init {
        this.vue = vue
        this.jeu = modele
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
            val message = Message(titre, "|"+vue.joueur1.text + "|", "|"+vue.joueur2.text + "|",
                vue.point1.text[1] + "/points" ,vue.point2.text[1] + "/points" ,vue.compteTour.text+"/", "|"+jeu.getJoueurCourant()!!.nom+"|" + " plateau\n"
                        + save()
            + "#"+jeu.getJoueur()[0].stringPions()+"##"+jeu.getJoueur()[1].stringPions()+"#")
            println(message)
            message.serialiser("sauvegarde/$titre.json")
        }
    }

    fun save(): String {
        var res = ""
        for (i in 0 until 8){
            res += '_'
            for (j in 0 until 4){
                if (jeu.plateau.getCases()[j][i].getPion() !is Pion){
                    res += "0_"
                }else{
                    res += "${jeu.plateau.getCases()[j][i].getPion()!!.getScore()}_"
                }
            }
        }
        return res
    }

}