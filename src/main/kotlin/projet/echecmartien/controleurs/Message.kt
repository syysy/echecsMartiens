package projet.echecmartien.controleurs

import com.google.gson.Gson
import java.io.FileReader
import java.io.FileWriter

open class Message (titre : String,
                    joueur1 :String, joueur2 : String,
                    ptsj1 : String, ptsj2 : String,
                    nbtour : String, jCourant : String) {

    var titre : String
    var joueur1: String
    var joueur2: String
    var ptsj1 : String
    var ptsj2 : String
    var nbtour : String
    var jCourant : String

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
        return "\n-------\n $titre " +
                "\n-------\n$joueur1,$ptsj1" +
                "\n-------\n$joueur2,$ptsj2" +
                "\n-------\n$nbtour,$jCourant"
    }

    fun serialiser(nomFichier: String) {
        val writer = FileWriter(nomFichier)
        Gson().toJson(this, writer)
        writer.flush()
        writer.close()
    }

    fun deserialiserMessage(nomFichier: String): Message? {
        val reader = FileReader(nomFichier)
        val message = Gson().fromJson(reader, Message::class.java)
        reader.close()
        return message
    }
}