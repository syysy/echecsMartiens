package projet.echecmartien.vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.FlowPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight


class RulesVue: BorderPane() {

    var buttonBottomRules  = Button("Retour")
    val text = Label("PREPARATION\n" +
            "Disposez les 18 pions comme sur la figure ci-contre.\n" +
            "Un joueur identifie ses pièces par leur position à un instant donné.\n" +
            "Le damier est divisé en 2 zones, une pour chaque joueur. Toute pièce dans la zone d'un joueur est la sienne.\n" +
            "\n" +
            "DEROULEMENT DU JEU\n" +
            "Chaque joueur, à son tour de jeu, déplace une de ses pièces.\n" +
            "Les grands pions se déplacent verticalement, horizontalement et diagonalement de n cases (comme la dame aux échecs traditionnel).\n" +
            "Les pions moyens se déplacent verticalement, horizontalement et diagonalement de 1 ou 2 cases.\n" +
            "Les petits pions se déplacent diagonalement de 1 case.\n" +
            "A son tour de jeu un joueur peut déplacer n'importe quel pion de son camp, soit à l'intérieur de sa zone soit vers la zone adverse. \t\n" +
            "Exception: Il est interdit de renvoyer dans la zone adverse un pion qui vient d'arriver dans sa zone. Mais on peut déplacer ce même pion à l'intérieur de sa zone\n" +
            "\n" +
            "On capture un pion adverse en prenant sa place (donc fatalement en prenant un pion de sa zone et en allant dans la zone adverse). Le pion capturé est retiré du damier..\n" +
            "Le saut par dessus un ou n pions adverses ou non n'est pas autorisé.\n" +
            "\n" +
            "FIN DE LA PARTIE\n" +
            "Une fois la partie finie (plus de pions à capturer car ils sont tous capturés ou plus aucunes prises n'est possibles),\n" +
            "on compte 3 points par grand pion capturés, 2 par moyen et 1 par petit.\n" +
            "\n" +
            "Le gagnant est évidement le joueur qui à le plus de points")

    private var labelTop = Label("Echecs Martiens")

    init {
        val flowPaneTop = FlowPane()
        labelTop.font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelTop.textFill = Color.WHITE
        flowPaneTop.alignment = Pos.CENTER
        labelTop.padding = Insets(30.0,0.0,30.0,0.0)
        flowPaneTop.children.add(labelTop)
        this.top = flowPaneTop
        text.textFill = Color.WHITE
        val borderCenter = FlowPane()
        borderCenter.padding = Insets(50.0,0.0,0.0,75.0)
        borderCenter.children.add(text)
        this.center = borderCenter

        this.buttonBottomRules.isDisable = false
        this.buttonBottomRules.isVisible = true

        val neoFlow = FlowPane()
        this.bottom = neoFlow
        neoFlow.children.add(buttonBottomRules)
        neoFlow.padding = Insets(50.0,0.0,50.0,0.0)
        neoFlow.alignment = Pos.CENTER
        //-fx-background-image: url('https://png.pngtree.com/thumb_back/fw800/background/20190222/ourmid/pngtree-azure-universe-planet-cartoon-background-image_58913.jpg')
        //this.style = ("-fx-background-color: #383344;; -fx-background-repeat: no-repeat; -fx-background-size: 1080 600; -fx-background-position: center center;")

    }
    fun fixeListenerBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }
    fun addStyle(){
        this.styleClass.add("regles")
        buttonBottomRules.styleClass.add("bouton")
    }

}