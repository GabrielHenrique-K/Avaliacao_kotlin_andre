import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabase(context: Context) : SQLiteOpenHelper(context, "notes.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE notes (_id INTEGER PRIMARY KEY, title TEXT, content TEXT)")
    }

    fun saveNote(title: String, content: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("title", title)
        values.put("content", content)
        db.insert("notes", null, values)
        db.close()
    }

    fun getNotes(): List<Note> {
        val db = readableDatabase
        val cursor = db.query("notes", arrayOf("_id", "title", "content"), null, null, null, null, null)
        val notes = ArrayList<Note>()
        while (cursor.moveToNext()) {
            val note = Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
            notes.add(note)
        }
        cursor.close()
        db.close()
        return notes
    }
}

data class Note(val id: Int, val title: String, val content: String)

class MainActivity : AppCompatActivity() {
    private lateinit var noteDatabase: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteDatabase = NoteDatabase(this)

        // Adicione a funcionalidade de salvar notas aqui
        // Por exemplo, ao clicar em um botão, chame a função saveNote
        // button.setOnClickListener { noteDatabase.saveNote("Título da nota", "Conteúdo da nota") }
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteDatabase = NoteDatabase(this)
        noteAdapter = NoteAdapter(noteDatabase.getNotes())

        val recyclerView = findViewById<RecyclerView>(R.id.notes_recycler_view)
        recyclerView.adapter = noteAdapter
        val
    }
}