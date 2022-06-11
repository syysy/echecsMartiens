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

class ControleurDeplace(vue :JeuVue) : EventHandler<MouseEvent>{
    private val vue = vue

    override fun handle(event: MouseEvent) {
        val row = GridPane.getRowIndex(event.source as Node)
        val column = GridPane.getColumnIndex(event.source as Node)
        vue.jeu.setCoordDestinationDeplacement(Coordonnee(column,row))
        val originCords = vue.jeu.getCoordOrigineDeplacement()!!
        val originRow = originCords.getY()
        val originColumn = originCords.getX()
        val type = vue.jeu.plateau.getCases()[originColumn][originRow].getPion()!!.getScore()
        vue.jeu.deplacer(originColumn,originRow,column,row)
        vue.setAsNull(vue.grille.children[originColumn*(vue.grille.rowCount)+originRow] as Circle)
        if (type == 1){
            vue.setAsPetitPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle)
        }else if (type == 2){
            vue.setAsMoyenPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle)
        }else if (type == 3){
            vue.setAsGrandPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle)
        }
        var nbpts1 = 0
        var nbpts2 = 0
        if (vue.jeu.plateau.getCases()[column][row].estLibre() == false && vue.jeu.plateau.getCases()[column][row].getJoueur() != vue.jeu.getJoueurCourant()){
            if (vue.jeu.getJoueurCourant()!!.nom == vue.joueur1.text){
                nbpts1 += 1
                vue.pts1.text = nbpts1.toString()
            }
            if (vue.jeu.getJoueurCourant()!!.nom == vue.joueur2.text){
                nbpts2 += 1
                vue.pts2.text += nbpts2.toString()
            }
        }
        vue.jeu.changeJoueurCourant()
        vue.update()
        vue.compteTour.text = "Tour ${vue.compteTour.text[vue.compteTour.text.length-1]+1}"
        vue.playerTurn()
    }
}
