package com.devalvv.notefile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var noteTitle: EditText
    private lateinit var noteContent: EditText
    private lateinit var noteTimestamp: TextView
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private var currentNoteFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        noteTitle = findViewById(R.id.noteTitle)
        noteContent = findViewById(R.id.noteContent)
        noteTimestamp = findViewById(R.id.noteTimestamp)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteButton)

        val filePath = intent.getStringExtra("noteFilePath")
        if (filePath != null) {
            currentNoteFile = File(filePath)
            loadNote()
        }

        saveButton.setOnClickListener { saveNote() }
        deleteButton.setOnClickListener { deleteNote() }
    }

    private fun loadNote() {
        currentNoteFile?.let {
            val lines = it.readLines()
            if (lines.isNotEmpty()) {
                noteTitle.setText(lines[0].removePrefix("Title: "))
                noteContent.setText(lines.subList(1, lines.size - 1).joinToString("\n").removePrefix("Content: "))
                noteTimestamp.text = lines.last()
            }
        }
    }

    private fun saveNote() {
        val title = noteTitle.text.toString()
        val content = noteContent.text.toString()
        if (currentNoteFile != null && title.isNotBlank() && content.isNotBlank()) {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            currentNoteFile?.writeText("Title: $title\nContent: $content\nCreated: $timestamp\n")
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Title and Content cannot be empty!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteNote() {
        if (currentNoteFile != null) {
            currentNoteFile?.delete()
            Toast.makeText(this, "Note deleted!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "No note to delete!", Toast.LENGTH_SHORT).show()
        }
    }
}
