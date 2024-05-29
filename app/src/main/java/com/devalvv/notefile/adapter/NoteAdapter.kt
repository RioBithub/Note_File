package com.devalvv.notefile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devalvv.notefile.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteAdapter(private val notes: List<File>, private val clickListener: (File) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.findViewById(R.id.noteTitle)
        val noteTimestamp: TextView = itemView.findViewById(R.id.noteTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.noteTitle.text = note.name
        holder.noteTimestamp.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
            Date(note.lastModified())
        )

        holder.itemView.setOnClickListener { clickListener(note) }
    }

    override fun getItemCount() = notes.size
}