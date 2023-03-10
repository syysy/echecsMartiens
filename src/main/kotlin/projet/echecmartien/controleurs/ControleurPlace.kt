package projet.echecmartien.controleurs

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import projet.echecmartien.exceptions.DeplacementException
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.JeuVue

class ControleurPlace(private val vue: JeuVue, modele : Jeu) : EventHandler<MouseEvent>{
    val jeu = modele

    override fun handle(event: MouseEvent) {
        vue.update(jeu)
        val row = GridPane.getRowIndex(event.source as Node)
        val column = GridPane.getColumnIndex(event.source as Node)
        jeu.setCoordOrigineDeplacement(Coordonnee(column,row))
        for (i in 0 until 8){
            for(j in 0 until 4){
                if (vue.grille.children[i*(vue.grille.columnCount)+j] is Circle){
                    try {
                        if(!jeu.deplacementPossible(column,row,j,i,jeu.plateau.getCases()[column][row].getJoueur())){
                            throw DeplacementException()
                        }
                        vue.fixeListenerCase((vue.grille.children[j*(vue.grille.rowCount)+i] as Circle),ControleurDeplace(vue,jeu))
                        if (jeu.plateau.getCases()[j][i].getPion() == null){
                            (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.RED
                        }else{
                            (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.BLUE
                        }

                    }catch (e : DeplacementException){
                        if (jeu.plateau.getCases()[j][i].getPion() == null){
                            (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.WHITE

                        }
                        else{
                            vue.fixeListenerCase((vue.grille.children[j*(vue.grille.rowCount)+i] as Circle),ControleurPlace(vue,jeu))
                            if(jeu.plateau.getCases()[j][i].getPion()!!.getScore() == 3){
                                (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.SADDLEBROWN
                            }
                            if(jeu.plateau.getCases()[j][i].getPion()!!.getScore() == 2){
                                (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.SANDYBROWN
                            }
                            if(jeu.plateau.getCases()[j][i].getPion()!!.getScore() == 1){
                                (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.ROSYBROWN
                            }
                        }
                    }
                }
            }
        }

    }


}