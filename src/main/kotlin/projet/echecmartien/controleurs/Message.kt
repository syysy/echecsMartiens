package projet.echecmartien.controleurs

import com.google.gson.Gson
import java.io.FileWriter

open class Message (titre : String,
                    joueur1 :String, joueur2 : String,
                    ptsj1 : String, ptsj2 : String,
                    nbtour : String, jCourant : String) {

    private var titre : String
    var joueur1: String
    var joueur2: String
    private var ptsj1 : String
    private var ptsj2 : String
    private var nbtour : String
    private var jCourant : String

    init {
        this.titre = titre
        this.joueur1 = joueur1
        this.joueur2 = joueur2
        this.ptsj1 = ptsj1
        this.ptsj2 = ptsj2
        this.nbtour = nbtour
        this.jCourant = jCourant
    }

    @Override
    override fun toString(): String {
        return "$titre $joueur1 $ptsj1 $joueur2 $ptsj2 $nbtour $jCourant "
    }

    fun serialiser(nomFichier: String) {
        val writer = FileWriter(nomFichier)
        Gson().toJson(this.toString(), writer)
        writer.flush()
        writer.close()
    }

}