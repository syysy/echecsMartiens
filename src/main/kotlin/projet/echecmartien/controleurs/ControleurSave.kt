package projet.echecmartien.controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.TextInputDialog
import javafx.stage.Stage
import projet.echecmartien.modele.Coordonnee
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
        /**
         * On set up une boite de dialogue pour récupérer le nom de la sauvegarde
         * */
        val dialog = TextInputDialog()
        dialog.title = "Sauvegarde d'une partie"
        dialog.headerText = "Entrez un nom pour votre sauvegarde"
        dialog.contentText = "Nom :"
        val result = dialog.showAndWait()

        /**
         *  Lorsqu'on a le résultat, on sauvegarde dans un json le message contenant
         * les noms des joueurs, leurs points, le tour, le joueur courant, le plateau, les pions capturés par les joueurs
         * et le nombre de coups sans prises.
         * */
        var titre = ""
        if (result.isPresent){
            titre = result.get()
            val message = Message(titre, "|"+vue.joueur1.text + "|", "|"+vue.joueur2.text + "|",
                vue.point1.text+ "/" ,vue.point2.text + "/" ,vue.compteTour.text+"/"+jeu.getNombreCoupsSansPrise()+"/", "|"+jeu.getJoueurCourant()!!.nom+"|" + " plateau\n"
                        + save()
            + "#"+jeu.getJoueur()[0].stringPions()+"##"+jeu.getJoueur()[1].stringPions()+"#"+"|"+vue.IActive+"|"+"|"+arriveDeZone()+"|")
            message.serialiser("sauvegarde/$titre.json")
        }
    }

    private fun save(): String {
        /**
         * Permet d'obtenir un string facilement lisible et analysable du plateau
         * */
        var res = ""
        for (i in 0 until 8){
            res += '_'
            for (j in 0 until 4){
                res += if (jeu.plateau.getCases()[j][i].getPion() !is Pion){
                    "0_"
                }else{
                    "${jeu.plateau.getCases()[j][i].getPion()!!.getScore()}_"
                }
            }
        }
        return res
    }

    fun arriveDeZone(): Coordonnee?{
        for (i in 0 until 4){
            for (j in 0 until 8){
                if (jeu.plateau.getCases()[i][j].getPion() === jeu.pionArriveDeZone && jeu.pionArriveDeZone is Pion){
                    return Coordonnee(i,j)
                }
            }
        }
        return null
    }
}