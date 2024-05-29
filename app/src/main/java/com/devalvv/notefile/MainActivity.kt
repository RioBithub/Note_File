package com.devalvv.notefile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devalvv.notefile.adapter.NoteAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fab = findViewById(R.id.fab)

        fab.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        loadNotes()
    }

    private fun loadNotes() {
        val notesDir = filesDir
        val noteFiles = notesDir.listFiles { file -> file.extension == "txt" }?.toList() ?: listOf()
        adapter = NoteAdapter(noteFiles) { note ->
            val intent = Intent(this, NoteDetailActivity::class.java)
            intent.putExtra("noteFilePath", note.absolutePath)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }
}
