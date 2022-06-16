package projet.echecmartien.controleurs

import com.google.gson.Gson
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.AppliJeuEchecMartien
import projet.echecmartien.modele.*
import projet.echecmartien.vue.JeuVue
import projet.echecmartien.vue.MainVue
import java.io.FileReader

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
            /**
             * On créé toutes les variables nécessaires pour les informations à récupérer
             * */
            val reader = FileReader(result)
            val readJson = Gson().fromJson(reader, String::class.java)
            var extent = ""
            var joueur1points : Int? = null
            var joueur2points : Int? = null
            var joueur1name = ""
            var joueur2name = ""
            var joueurCourantName = ""
            val listPion1 = mutableSetOf<Pion>()
            val listPion2 = mutableSetOf<Pion>()
            val matricePlateau = Plateau()
            var nbTour : Int? = null
            var nbTourSansPrise : Int? = null
            var iActive = false
            var iActiveStr = ""
            var pionArriveDeString = ""
            var pionArriveDeZone : Pion? = null
            var pionArriveDeBoolean = false

            /**
             * Une boucle pour parcourir l'ensemble du String contenu dans le Json
             * */
            for (i in readJson.indices) {
                /**
                 * On rajoute le caractère courant dans la variable extent qu'on utilisera pour analyser
                 * */
                extent += readJson[i]
                /**
                 * On récupère les noms des joueurs ainsi que celui du joueur courant contenus entre deux |
                 * */
                if (countPipe(extent) == 1 && readJson[i] != '|'){
                    joueur1name += readJson[i]
                }
                if (countPipe(extent) == 3 && readJson[i] != '|'){
                    joueur2name += readJson[i]
                }
                if (countPipe(extent) == 5 && readJson[i] != '|'){
                    joueurCourantName += readJson[i]
                }
                /**
                 * On récupère les points qui sont placés avant un / (Il est écrit "XX points" ou "X points" raison pour le -9 ou -8)
                 * */
                if (countSlash(extent) == 1 && joueur1points !is Int){
                    joueur1points = if (extent[i-9] != ' '){
                        Integer.parseInt("${extent[i-9]}${extent[i-8]}")
                    }else{
                        Integer.parseInt(extent[i-8].toString())
                    }
                }else if (countSlash(extent) == 2 && joueur2points !is Int){
                    joueur2points = if (extent[i-9] != ' '){
                        Integer.parseInt("${extent[i-9]}${extent[i-8]}")
                    }else{
                        Integer.parseInt(extent[i-8].toString())
                    }
                }
                /**
                 * Les pions pris sont compris entre 2 #
                 * */
                if (countHashtag(extent) == 1){
                    if (extent[i] == '1'){
                        listPion1.add(PetitPion())
                    }else if(extent[i] == '2'){
                        listPion1.add(MoyenPion())
                    }else if(extent[i] == '3'){
                        listPion1.add(GrandPion())
                    }
                }
                if (countHashtag(extent) == 3){
                    if (extent[i] == '1'){
                        listPion2.add(PetitPion())
                    }else if(extent[i] == '2'){
                        listPion2.add(MoyenPion())
                    }else if(extent[i] == '3'){
                        listPion2.add(GrandPion())
                    }
                }
                /**
                 * On récupère l'ensemble du plateau grâce à un système de tirets du huit
                 * */
                if (countUnderscore(extent) in 1..5){
                    if (countUnderscore(extent) == 1 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[0][0].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[0][0].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[0][0].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 2 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[1][0].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[1][0].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[1][0].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 3 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[2][0].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[2][0].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[2][0].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 4 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[3][0].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[3][0].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[3][0].setPion(GrandPion())
                        }
                    }
                }
                if (countUnderscore(extent) in 6..10){
                    if (countUnderscore(extent) == 6 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[0][1].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[0][1].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[0][1].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 7 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[1][1].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[1][1].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[1][1].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 8 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[2][1].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[2][1].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[2][1].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 9 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[3][1].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[3][1].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[3][1].setPion(GrandPion())
                        }
                    }
                }
                if (countUnderscore(extent) in 11..15){
                    if (countUnderscore(extent) == 11 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[0][2].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[0][2].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[0][2].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 12 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[1][2].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[1][2].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[1][2].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 13 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[2][2].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[2][2].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[2][2].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 14 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[3][2].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[3][2].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[3][2].setPion(GrandPion())
                        }
                    }
                }
                if (countUnderscore(extent) in 16..20){
                    if (countUnderscore(extent) == 16 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[0][3].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[0][3].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[0][3].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 17 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[1][3].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[1][3].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[1][3].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 18 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[2][3].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[2][3].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[2][3].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 19 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[3][3].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[3][3].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[3][3].setPion(GrandPion())
                        }
                    }
                }
                if (countUnderscore(extent) in 21..25){
                    if (countUnderscore(extent) == 21 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[0][4].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[0][4].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[0][4].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 22 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[1][4].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[1][4].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[1][4].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 23 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[2][4].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[2][4].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[2][4].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 24 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[3][4].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[3][4].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[3][4].setPion(GrandPion())
                        }
                    }
                }
                if (countUnderscore(extent) in 26..30){
                    if (countUnderscore(extent) == 26 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[0][5].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[0][5].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[0][5].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 27 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[1][5].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[1][5].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[1][5].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 28 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[2][5].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[2][5].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[2][5].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 29 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[3][5].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[3][5].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[3][5].setPion(GrandPion())
                        }
                    }
                }
                if (countUnderscore(extent) in 31..35){
                    if (countUnderscore(extent) == 31 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[0][6].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[0][6].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[0][6].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 32 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[1][6].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[1][6].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[1][6].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 33 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[2][6].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[2][6].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[2][6].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 34 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[3][6].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[3][6].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[3][6].setPion(GrandPion())
                        }
                    }
                }
                if (countUnderscore(extent) in 36..40){
                    if (countUnderscore(extent) == 36 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[0][7].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[0][7].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[0][7].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 37 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[1][7].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[1][7].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[1][7].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 38 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[2][7].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[2][7].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[2][7].setPion(GrandPion())
                        }
                    }
                    if (countUnderscore(extent) == 39 && extent[i] != '_'){
                        if (Integer.parseInt(extent[i].toString()) == 1) {
                            matricePlateau.getCases()[3][7].setPion(PetitPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 2) {
                            matricePlateau.getCases()[3][7].setPion(MoyenPion())
                        }
                        if (Integer.parseInt(extent[i].toString()) == 3) {
                            matricePlateau.getCases()[3][7].setPion(GrandPion())
                        }
                    }
                }
                /**
                 * On récupère le nombre de tours et le nombre de tours sans prises qui se situent eux aussi juste avant un /
                 * */
                if (countSlash(extent) == 3 && nbTour !is Int){
                    nbTour = if (extent[i-2] != ' '){
                        Integer.parseInt("${extent[i-2]}${extent[i-1]}")
                    }else {
                        Integer.parseInt(extent[i - 1].toString())
                    }
                }
                if (countSlash(extent) == 4 && nbTourSansPrise !is Int){
                    nbTourSansPrise = Integer.parseInt(extent[i-1].toString())
                }
                /**
                 * On regarde si l'IA est active en récupérant un Boolean mis entre deux |
                 * */
                if (countPipe(extent) == 7 && extent[i] != '|'){
                    iActiveStr += extent[i]
                    if (iActiveStr == "true"){
                        iActive = true
                    }else if (iActiveStr == "false"){
                        iActive = false
                    }
                }
                /**
                 * Même système avec le pion arrive de zone
                 * */
                if (countPipe(extent) == 9 && extent[i] != '|'){
                    pionArriveDeString += extent[i]
                    if (pionArriveDeString == "null"){
                        pionArriveDeZone = null
                    }else if (pionArriveDeString[pionArriveDeString.length-1] == ')'){
                        pionArriveDeBoolean = true
                    }
                }
            }

            var nbPetit = 0
            var nbMoyen = 0
            var nbGrand = 0

            var nbPetit2 = 0
            var nbMoyen2 = 0
            var nbGrand2 = 0

            /**
             * On récupère les nombres de petits, moyens et grands pions affichés sur les côtés
             * */

            for (i in listPion1){
                when (i) {
                    is PetitPion -> {
                        nbPetit += 1
                    }
                    is MoyenPion -> {
                        nbMoyen += 1
                    }
                    is GrandPion -> {
                        nbGrand += 1
                    }
                }
            }
            for (i in listPion2){
                when (i) {
                    is PetitPion -> {
                        nbPetit2 += 1
                    }
                    is MoyenPion -> {
                        nbMoyen2 += 1
                    }
                    is GrandPion -> {
                        nbGrand2 += 1
                    }
                }
            }

            /**
             * On recréé l'ensemble de la partie avec toutes les informations récupérée plus haut
             * */

            jeu.initialiserPartie(Joueur(joueur1name), Joueur(joueur2name), jeu.getNombreCoupsMax())
            jeu.getJoueurCourant()!!.nom = joueurCourantName
            jeu.getJoueur()[0].pionCapture = listPion1
            jeu.getJoueur()[1].pionCapture = listPion2
            jeu.plateau = matricePlateau
            if (pionArriveDeBoolean){
                println(matricePlateau)
                println(pionArriveDeString[1])
                println(pionArriveDeString[3])
                pionArriveDeZone = jeu.plateau.getCases()[Integer.parseInt(pionArriveDeString[1].toString())][Integer.parseInt(pionArriveDeString[3].toString())].getPion()
            }
            jeu.pionArriveDeZone = pionArriveDeZone
            jeu.setNombreCoupSansPrise(nbTourSansPrise!!)

            val newVue = JeuVue(Label(joueur1name),Label(joueur2name),Label(" $joueur2points points"),Label(" $joueur1points points"),
            Label(nbPetit.toString()),Label(nbMoyen.toString()),Label(nbGrand.toString()),
                Label(nbPetit2.toString()), Label(nbMoyen2.toString()),Label(nbGrand2.toString()),
            Label("Tour "+nbTour.toString()),nbTour!!,
            Label("Tours sans prises : $nbTourSansPrise"),iActive)
            newVue.update(jeu)
            println(iActive)
            val scene = Scene(newVue,600.0,900.0)
            primaryStage.scene = scene
            primaryStage.centerOnScreen()

            //initialisation du jeu

            newVue.joueur1.text = joueur1name
            newVue.joueur2.text = joueur2name
            println(jeu.plateau)
            newVue.chargement(jeu,joueurCourantName,jeu.plateau,listPion1,listPion2,iActive)
            scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/style.css").toExternalForm())
            newVue.addStyle()
            println(jeu.plateau)
            //playerturn
            if (Joueur(newVue.joueur1.text) == jeu.getJoueurCourant()){
                newVue.joueur1.style = "-fx-font-weight : bold;"
                newVue.joueur2.style = ""
            }else{
                newVue.joueur2.style = "-fx-font-weight : bold;"
                newVue.joueur1.style = ""
            }
            newVue.changeJoueurStyl(jeu)
            newVue.fixeListenerBouton(newVue.boutonReset,ControleurReset(MainVue(),jeu,primaryStage))
            newVue.fixeListenerBouton(newVue.boutonRegles,ControleurRulesJeu(newVue,jeu,primaryStage))
            newVue.fixeListenerBouton(newVue.boutonCharge,ControleurChargerSave(MainVue(),jeu,primaryStage))
            newVue.fixeListenerBouton(newVue.boutonSave,ControleurSave(newVue,jeu,primaryStage))

        }else{
            return
        }
    }

    /**
     * Les fonctions permettant de compter les caractères spéciaux récupérés
     * */

    private fun countSlash(extent : String) : Int{
        var count = 0
        for (i in extent){
            if (i == '/'){
                count += 1
            }
        }
        return count
    }

    private fun countPipe(extent : String) : Int{
        var count = 0
        for (i in extent){
            if (i == '|'){
                count += 1
            }
        }
        return count
    }

    private fun countHashtag(extent : String) : Int{
        var count = 0
        for (i in extent){
            if (i == '#'){
                count += 1
            }
        }
        return count
    }

    private fun countUnderscore(extent : String) : Int{
        var count = 0
        for (i in extent){
            if (i == '_'){
                count += 1
            }
        }
        return count
    }
}