package com.devalvv.notefile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class NewNoteActivity : AppCompatActivity() {

    private lateinit var noteTitle: EditText
    private lateinit var noteContent: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        noteTitle = findViewById(R.id.noteTitle)
        noteContent = findViewById(R.id.noteContent)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener { saveNote() }
    }

    private fun saveNote() {
        val title = noteTitle.text.toString()
        val content = noteContent.text.toString()
        if (title.isNotBlank() && content.isNotBlank()) {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val filename = "Note_${timestamp}.txt"
            val file = File(filesDir, filename)
            file.writeText("Title: $title\nContent: $content\nCreated: $timestamp\n")
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Title and Content cannot be empty!", Toast.LENGTH_SHORT).show()
        }
    }
}
