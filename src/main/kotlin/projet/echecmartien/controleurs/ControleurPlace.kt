package projet.echecmartien.controleurs

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import projet.echecmartien.exceptions.DeplacementException
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Deplacement
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue

class ControleurPlace(vue :JeuVue) : EventHandler<MouseEvent>{
    private val vue = vue

    override fun handle(event: MouseEvent) {
        var row = GridPane.getRowIndex(event.source as Node)
        var column = GridPane.getColumnIndex(event.source as Node)
        for (i in 0 until 8){
            for(j in 0 until 4){
                if (vue.grille.children[i*(vue.grille.columnCount)+j] is Circle){
                    println("${i*(vue.grille.columnCount)+j}, verti = $j, horri = $i")
                    try {
                        vue.jeu.plateau.getCases()[column][row].getPion()!!.getDeplacement(Deplacement(Coordonnee(column,row),Coordonnee(j,i)))
                        vue.fixeListenerCase((vue.grille.children[j*(vue.grille.rowCount)+i] as Circle),ControleurDeplace(vue))
                        if (vue.jeu.plateau.getCases()[j][i].getPion() == null){
                            (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.RED
                        }else{
                            (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.BLUE
                        }
                    }catch (e : DeplacementException){
                        if (vue.jeu.plateau.getCases()[j][i].getPion() == null){
                            (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.WHITE
                        }else{

                            vue.fixeListenerCase((vue.grille.children[j*(vue.grille.rowCount)+i] as Circle),ControleurPlace(vue))
                            (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.BLACK
                        }
                    }
                }
            }
        }

    }


}