package projet.echecmartien.vue

import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.BorderPane

class RulesVue: BorderPane() {
    private val textRegles : TextArea

    init {
        val labelTop = Label("Règles de l'échec martien")
        textRegles = TextArea("PREPARATION\n" +
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
                "Une fois la partie finie (plus de pions à capturer car ils sont tous capturés ou plus aucunes prises n'est possibles), on compte 3 points par grand pion capturés, 2 par moyen et 1 par petit.\n" +
                "\n" +
                "Le gagnant est évidement le joueur qui à le plus de points")
        this.top = labelTop
        this.center = textRegles
    }
}