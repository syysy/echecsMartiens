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
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue

class ControleurDeplace(vue :JeuVue,modele : Jeu) : EventHandler<MouseEvent>{
    private val vue = vue
    val jeu = modele
    var nbpts1 = 0
    var nbpts2 = 0

    fun setAsGrandPion(pion : Circle){
        pion.radius = 20.0
        pion.fill = Color.BLACK
        vue.fixeListenerCase(pion,ControleurPlace(vue,jeu))
    }

    fun setAsMoyenPion(pion : Circle){
        pion.radius = 10.0
        pion.fill = Color.BLACK
        vue.fixeListenerCase(pion,ControleurPlace(vue,jeu))
    }

    fun setAsPetitPion(pion : Circle){
        pion.radius = 5.0
        pion.fill = Color.BLACK
        vue.fixeListenerCase(pion,ControleurPlace(vue,jeu))
    }

    fun setAsNull(pion : Circle){
        pion.radius = 20.0
        pion.fill = Color.WHITE
        pion.removeEventFilter(MouseEvent.MOUSE_CLICKED, ControleurPlace(vue,jeu))
    }
    override fun handle(event: MouseEvent) {
        val row = GridPane.getRowIndex(event.source as Node)
        val column = GridPane.getColumnIndex(event.source as Node)
        jeu.setCoordDestinationDeplacement(Coordonnee(column,row))
        val originCords = jeu.getCoordOrigineDeplacement()!!
        val originRow = originCords.getY()
        val originColumn = originCords.getX()
        val type = jeu.plateau.getCases()[originColumn][originRow].getPion()!!.getScore()
        val typePris = jeu.plateau.getCases()[originColumn][originRow].getPion()!!.getScore()
        jeu.deplacer(originColumn,originRow,column,row)
        setAsNull(vue.grille.children[originColumn*(vue.grille.rowCount)+originRow] as Circle)
        if (type == 1){
            setAsPetitPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle)
        }else if (type == 2){
            setAsMoyenPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle)
        }else if (type == 3){
            setAsGrandPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle)
        }
        if (jeu.plateau.getCases()[column][row].estLibre() == false && jeu.plateau.getCases()[column][row].getJoueur() != jeu.getJoueurCourant()){
            if (jeu.getJoueurCourant()!!.nom == vue.joueur1.text){
                vue.point1.text = "${vue.point1.text[1]+type} Points"
            }
            if (jeu.getJoueurCourant()!!.nom == vue.joueur2.text){
                vue.point2.text = "${vue.point2.text[1]+type} Points"
            }
        }
        jeu.changeJoueurCourant()
        //update
        for (i in 0 until 8){
            for (j in 0 until 4){
                if (jeu.plateau.getCases()[j][i].getPion() == null){
                    setAsNull(vue.grille.children[j*(vue.grille.rowCount)+i] as Circle)

                }else{
                    if (jeu.plateau.getCases()[j][i].getJoueur() == jeu.getJoueurCourant()){
                        vue.fixeListenerCase(vue.grille.children[j*(vue.grille.rowCount)+i] as Circle,ControleurPlace(vue,jeu))
                    }else{
                        (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).removeEventFilter(MouseEvent.MOUSE_CLICKED, ControleurPlace(vue,jeu))
                    }
                    (vue.grille.children[j*(vue.grille.rowCount)+i] as Circle).fill = Color.BLACK
                }
            }
        }

        vue.compteTour.text = "Tour ${vue.compteTour.text[vue.compteTour.text.length-1]+1}"
        //playerturn
        if (Joueur(vue.joueur1.text) == jeu.getJoueurCourant()){
            vue.joueur1.style = "-fx-font-weight : bold;"
            vue.joueur2.style = ""
        }else{
            vue.joueur2.style = "-fx-font-weight : bold;"
            vue.joueur1.style = ""
        }

    }
}
