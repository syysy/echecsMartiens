package projet.echecmartien

import javafx.application.Application

import javafx.stage.Stage
import projet.echecmartien.modele.Deplacement
import projet.echecmartien.modele.Plateau

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {
      

    }

}

fun main(){
    //Application.launch(AppliJeuEchecMartien::class.java)
    val p = Plateau()
    println(p)
}



