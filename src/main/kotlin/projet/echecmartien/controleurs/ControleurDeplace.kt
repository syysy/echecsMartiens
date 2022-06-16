package projet.echecmartien.controleurs

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.Alert
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.shape.Circle
import projet.echecmartien.AppliJeuEchecMartien
import projet.echecmartien.exceptions.DeplacementException
import projet.echecmartien.modele.*
import projet.echecmartien.vue.JeuVue
import kotlin.random.Random

class ControleurDeplace(private val vue: JeuVue, modele : Jeu) : EventHandler<MouseEvent>{
    val jeu = modele



    override fun handle(event: MouseEvent) {
        var row = GridPane.getRowIndex(event.source as Node)
        var column = GridPane.getColumnIndex(event.source as Node)
        jeu.setCoordDestinationDeplacement(Coordonnee(column,row))
        var originCords = jeu.getCoordOrigineDeplacement()!!
        var originRow = originCords.getY()
        var originColumn = originCords.getX()
        var type = jeu.plateau.getCases()[originColumn][originRow].getPion()!!.getScore()
        var typePris = 0
        if (!jeu.plateau.getCases()[column][row].estLibre()){
            typePris = jeu.plateau.getCases()[column][row].getPion()!!.getScore()
            jeu.getJoueurCourant()!!.pionCapture.add(jeu.plateau.getCases()[column][row].getPion()!!)
        }
        jeu.deplacer(originColumn,originRow,column,row)
        vue.setAsNull(vue.grille.children[originColumn*(vue.grille.rowCount)+originRow] as Circle,jeu)
        when (type) {
            1 -> {
                vue.setAsPetitPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle,jeu)
            }
            2 -> {
                vue.setAsMoyenPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle,jeu)
            }
            3 -> {
                vue.setAsGrandPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle,jeu)
            }
        }


        // Compteur des points
        if (!jeu.plateau.getCases()[column][row].estLibre() && jeu.plateau.getCases()[column][row].getJoueur() != jeu.getJoueurCourant()){
            if (jeu.getJoueurCourant()!!.nom == vue.joueur1.text){
                vue.point1.text = "${jeu.getJoueurCourant()!!.calculerScore()} Points"
                if (typePris == 1){
                    vue.nbPetit2.text = (vue.nbPetit2.text.toInt() + 1).toString()
                }
                if (typePris == 2){
                    vue.nbMoyen2.text = (vue.nbMoyen2.text.toInt() + 1).toString()
                }
                if (typePris == 3){
                    vue.nbGrand2.text = (vue.nbGrand2.text.toInt() + 1).toString()
                }
            }
            if (jeu.getJoueurCourant()!!.nom == vue.joueur2.text){
                vue.point2.text = "${jeu.getJoueurCourant()!!.calculerScore()} Points"
                if (typePris == 1 ){
                    vue.nbPetit.text = (vue.nbPetit.text.toInt() + 1).toString()
                }
                if (typePris == 2 ){
                    vue.nbMoyen.text = (vue.nbMoyen.text.toInt() + 1).toString()
                }
                if (typePris == 3 ){
                    vue.nbGrand.text = (vue.nbGrand.text.toInt() + 1).toString()
                }
            }
        }
        jeu.changeJoueurCourant()

        vue.update(jeu)

        vue.nbTour += 1
        vue.compteTour.text = "Tour ${vue.nbTour}"
        vue.tourSansPrises.text = "Tours sans prises : ${jeu.getNombreCoupsSansPrise()}"

        //playerturn
        vue.changeJoueurStyl(jeu)

        if (jeu.arretPartie()){
            val dialog = Alert(Alert.AlertType.INFORMATION)
            dialog.title = "Fin de partie"
            dialog.headerText = null
            dialog.graphic = null
            if (jeu.joueurVainqueur() != null) {
                if (jeu.joueurVainqueur()!!.nom == jeu.getJoueurCourant()!!.nom) {
                    jeu.changeJoueurCourant()
                }
                if (jeu.joueurVainqueur() == Joueur("Nachouki")){
                    dialog.contentText = "GET NACHOUKED"
                }else{
                    dialog.contentText = "FIN DE PARTIE \n" + "Le gagnant est ${jeu.joueurVainqueur()!!.nom} avec ${
                        jeu.joueurVainqueur()!!.calculerScore()
                    } Point(s) \n" + "Le joueur ${jeu.getJoueurCourant()!!.nom} à perdu, il avait ${
                        jeu.getJoueurCourant()!!.calculerScore()
                    } Point(s) "
                }
            } else {
                dialog.contentText = "FIN DE PARTIE \n" + "Egalité, chaque joueurs avaient ${jeu.getJoueurCourant()!!.calculerScore()} Points"
            }
            dialog.dialogPane.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/style.css").toExternalForm())
            dialog.dialogPane.styleClass.add("dialog")
            dialog.showAndWait()
            vue.grille.isDisable = true
            vue.endGame.isVisible = true
        }




        //contrôle IA

        if (vue.IActive == true){
            val listeDeplace = mutableListOf<Coordonnee>()
            val listeDeplaceCos = mutableListOf<Coordonnee>()
            val listePrendre = mutableListOf<Coordonnee>()
            val listePrendreCos = mutableListOf<Coordonnee>()
            for (row in 4 until 8){
                for (column in 0 until 4){
                    for (i in 0 until 8){
                        for(j in 0 until 4){
                            if (vue.grille.children[i*(vue.grille.columnCount)+j] is Circle && jeu.plateau.getCases()[column][row].getPion() != null){
                                try {
                                    jeu.plateau.getCases()[column][row].getPion()!!.getDeplacement(Deplacement(Coordonnee(column,row),Coordonnee(j,i)))
                                    if(!jeu.deplacementPossible(column,row,j,i,jeu.plateau.getCases()[column][row].getJoueur())){
                                        throw DeplacementException()
                                    }
                                    vue.fixeListenerCase((vue.grille.children[j*(vue.grille.rowCount)+i] as Circle),ControleurDeplace(vue,jeu))
                                    if (jeu.plateau.getCases()[j][i].getPion() == null){
                                        listeDeplace.add(Coordonnee(j,i))
                                        listeDeplaceCos.add(Coordonnee(column,row))
                                    }else{
                                        listePrendre.add(Coordonnee(j,i))
                                        listePrendreCos.add(Coordonnee(column,row))
                                    }
                                }catch (e : DeplacementException){
                                }
                            }

                        }
                    }
                }
            }

            if (!triDefense(listeDeplace,listeDeplaceCos)){
                if (listePrendre.size != 0){
                    triPrendre(listePrendre,listePrendreCos)
                }else{
                    triDeplace(listeDeplace,listeDeplaceCos)
                }
            }

            originCords = jeu.getCoordOrigineDeplacement()!!
            originColumn = originCords.getX()
            originRow = originCords.getY()
            val destiCords = jeu.getCoordDestinationDeplacement()!!
            column = destiCords.getX()
            row = destiCords.getY()

            type = jeu.plateau.getCases()[originColumn][originRow].getPion()!!.getScore()
            typePris = 0
            if (!jeu.plateau.getCases()[column][row].estLibre()){
                typePris = jeu.plateau.getCases()[column][row].getPion()!!.getScore()
                jeu.getJoueurCourant()!!.pionCapture.add(jeu.plateau.getCases()[column][row].getPion()!!)
            }

            //fun contrôle IA

            jeu.deplacer(originColumn,originRow,column,row)

            vue.setAsNull(vue.grille.children[originColumn*(vue.grille.rowCount)+originRow] as Circle,jeu)
            when (type) {
                1 -> {
                    vue.setAsPetitPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle,jeu)
                }
                2 -> {
                    vue.setAsMoyenPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle,jeu)
                }
                3 -> {
                    vue.setAsGrandPion(vue.grille.children[column*(vue.grille.rowCount)+row] as Circle,jeu)
                }
            }


            // Compteur des points
            if (!jeu.plateau.getCases()[column][row].estLibre() && jeu.plateau.getCases()[column][row].getJoueur() != jeu.getJoueurCourant()){
                if (jeu.getJoueurCourant()!!.nom == vue.joueur1.text){
                    vue.point1.text = "${jeu.getJoueurCourant()!!.calculerScore()} Points"
                    if (typePris == 1){
                        vue.nbPetit2.text = (vue.nbPetit2.text.toInt() + 1).toString()

                    }
                    if (typePris == 2){
                        vue.nbMoyen2.text = (vue.nbMoyen2.text.toInt() + 1).toString()
                    }
                    if (typePris == 3){
                        vue.nbGrand2.text = (vue.nbGrand2.text.toInt() + 1).toString()
                    }
                }
                if (jeu.getJoueurCourant()!!.nom == vue.joueur2.text){
                    vue.point2.text = "${jeu.getJoueurCourant()!!.calculerScore()} Points"
                    if (typePris == 1 ){
                        vue.nbPetit.text = (vue.nbPetit.text.toInt() + 1).toString()
                    }
                    if (typePris == 2 ){
                        vue.nbMoyen.text = (vue.nbMoyen.text.toInt() + 1).toString()
                    }
                    if (typePris == 3 ){
                        vue.nbGrand.text = (vue.nbGrand.text.toInt() + 1).toString()
                    }
                }
            }
            jeu.changeJoueurCourant()

            vue.update(jeu)

            vue.nbTour += 1
            vue.compteTour.text = "Tour ${vue.nbTour}"
            vue.tourSansPrises.text = "Tours sans prises : ${jeu.getNombreCoupsSansPrise()}"

            //playerturn
            vue.changeJoueurStyl(jeu)

            if (jeu.arretPartie()){
                val dialog = Alert(Alert.AlertType.INFORMATION)
                dialog.title = "Fin de partie"
                dialog.headerText = null
                dialog.graphic = null
                if (jeu.joueurVainqueur() != null) {
                    if (jeu.joueurVainqueur()!!.nom == jeu.getJoueurCourant()!!.nom) {
                        jeu.changeJoueurCourant()
                    }
                    if (jeu.joueurVainqueur()!!.nom == jeu.getJoueurCourant()!!.nom) {
                        jeu.changeJoueurCourant()
                    }
                    if (jeu.joueurVainqueur() == Joueur("Nachouki")){
                        dialog.contentText = "GET NACHOUKED"
                    }else{
                        dialog.contentText = "FIN DE PARTIE \n" + "Le gagnant est ${jeu.joueurVainqueur()!!.nom} avec ${
                            jeu.joueurVainqueur()!!.calculerScore()
                        } Point(s) \n" + "Le joueur ${jeu.getJoueurCourant()!!.nom} à perdu, il avait ${
                            jeu.getJoueurCourant()!!.calculerScore()
                        } Point(s) "
                    }
                } else {
                    dialog.contentText = "FIN DE PARTIE \n" + "Egalité, chaque joueurs avaient ${jeu.getJoueurCourant()!!.calculerScore()} Points"
                }
                dialog.dialogPane.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/style.css").toExternalForm())
                dialog.dialogPane.styleClass.add("dialog")
                dialog.showAndWait()
                vue.grille.isDisable = true
                vue.endGame.isVisible = true
            }
        }


    }

    private fun triPrendre(liste : MutableList<Coordonnee>, listeCos : MutableList<Coordonnee>) : Boolean{
        var max = 1
        var listeMax = mutableListOf<Coordonnee>()
        val listeMaxCos = mutableListOf<Coordonnee>()
        for (i in 0 until liste.size){
            if (jeu.plateau.getCases()[liste[i].getX()][liste[i].getY()].getPion()!!.getScore() == max){
                listeMax.add(liste[i])
                listeMaxCos.add(listeCos[i])
            }else if(jeu.plateau.getCases()[liste[i].getX()][liste[i].getY()].getPion()!!.getScore() > max){
                listeMax = mutableListOf()
                listeMax.add(liste[i])
                listeMaxCos.add(listeCos[i])
                max = jeu.plateau.getCases()[liste[i].getX()][liste[i].getY()].getPion()!!.getScore()
            }
        }

        return if (listeMax.size > 0){

            val rand = Random.nextInt(0,listeMax.size)
            jeu.setCoordDestinationDeplacement(listeMax[rand])
            jeu.setCoordOrigineDeplacement(listeMaxCos[rand])
            true
        }else{
            false
        }
    }

    private fun triDeplace(liste: MutableList<Coordonnee>, listeCos : MutableList<Coordonnee>) : Boolean{
        val listePris = mutableListOf<Coordonnee>()
        val listePrisCos = mutableListOf<Coordonnee>()
        for (i in 0 until liste.size){
            for (j in 0 until 4){
                for (k in 0 until 4){
                    if (jeu.plateau.getCases()[j][k].getPion() != null){
                        try {
                            if(!jeu.deplacementPossible(j,k,liste[i].getX(),liste[i].getY(),jeu.plateau.getCases()[liste[i].getX()][liste[i].getY()].getJoueur())){
                                throw DeplacementException()
                            }
                            if (liste[i] !in listePris){
                                listePris.add(liste[i])
                                listePrisCos.add(listeCos[i])
                            }
                        }catch (_: DeplacementException){
                        }
                    }
                }
            }
        }

        val listeDispo = mutableListOf<Coordonnee>()
        val listeDispoCos = mutableListOf<Coordonnee>()
        for (i in 0 until liste.size){
            if (liste[i] !in listePris){
                listeDispo.add(liste[i])
                listeDispoCos.add(listeCos[i])
            }
        }

        val listePasAdverse = mutableListOf<Coordonnee>()
        val listePasAdverseCos = mutableListOf<Coordonnee>()

        for (i in 0 until listeDispo.size){
            if (listeDispo[i].getY() >= 4){
                listePasAdverse.add(listeDispo[i])
                listePasAdverseCos.add(listeDispoCos[i])
            }
        }

        if (listePasAdverse.size != 0){
            val rand = Random.nextInt(0,listePasAdverse.size)
            jeu.setCoordDestinationDeplacement(listePasAdverse[rand])
            jeu.setCoordOrigineDeplacement(listePasAdverseCos[rand])
            return true
        }
        if (listeDispo.size != 0){
            val rand = Random.nextInt(0,listeDispo.size)
            jeu.setCoordDestinationDeplacement(listeDispo[rand])
            jeu.setCoordOrigineDeplacement(listeDispoCos[rand])
            return true
        }
        if (liste.size != 0){
            val rand = Random.nextInt(0,liste.size)
            jeu.setCoordDestinationDeplacement(liste[rand])
            jeu.setCoordOrigineDeplacement(listeCos[rand])
        }
        return false

    }



    private fun triDefense(liste : MutableList<Coordonnee>, listeCos : MutableList<Coordonnee>) : Boolean{
        val listeGrandPion = mutableListOf<Coordonnee>()
        val listeGrandPionCos = mutableListOf<Coordonnee>()
        for (i in 0 until liste.size){
            if (jeu.plateau.getCases()[listeCos[i].getX()][listeCos[i].getY()].getPion()!!.getScore() == 3 ){
                listeGrandPion.add(liste[i])
                listeGrandPionCos.add(listeCos[i])
            }
        }
        val grandPionDanger = mutableListOf<Coordonnee>()
        val grandPionDangerCos = mutableListOf<Coordonnee>()
        for (i in 0 until listeGrandPionCos.size){
            for (j in 0 until 4){
                for (k in 0 until 4){
                    if (jeu.plateau.getCases()[j][k].getPion() != null){
                        try {
                            (jeu.plateau.getCases()[j][k].getPion()!!.getDeplacement(Deplacement(Coordonnee(j,k),listeGrandPionCos[i])))
                            if (liste[i] !in grandPionDanger){
                                grandPionDanger.add(listeGrandPion[i])
                                grandPionDangerCos.add(listeGrandPionCos[i])
                            }
                        }catch (_: DeplacementException){
                        }
                    }
                }
            }
        }
        val grandPionDangerPrend = mutableListOf<Coordonnee>()
        val grandPionDangerPrendCos = mutableListOf<Coordonnee>()
        for (i in 0 until grandPionDanger.size){
            if (jeu.plateau.getCases()[grandPionDanger[i].getX()][grandPionDanger[i].getY()].getPion() != null){
                grandPionDangerPrend.add(Coordonnee(grandPionDanger[i].getX(),grandPionDanger[i].getY()))
                grandPionDangerPrendCos.add(Coordonnee(grandPionDangerCos[i].getX(),grandPionDangerCos[i].getY()))
            }
        }
        return if (grandPionDangerPrend.size != 0){
            if (!triPrendre(grandPionDangerPrend,grandPionDangerPrendCos)){
                triDeplace(grandPionDanger,grandPionDangerCos)
            }else{
                true
            }
        }else{
            false
        }


    }
}
