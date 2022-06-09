package projet.echecmartien.controleurs

import com.google.gson.Gson
import java.io.FileReader
import java.io.FileWriter

class Message (titre :String, contenu : String) {

    private var titre: String
    private var contenu: String

    init {
        this.titre = titre
        this.contenu = contenu
    }

    @Override
    override fun toString(): String {
        return "\n-------\n$titre\n-------\n$contenu"
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