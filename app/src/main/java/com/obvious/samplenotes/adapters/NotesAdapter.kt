package com.obvious.samplenotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.obvious.samplenotes.R
import com.obvious.samplenotes.models.Note
import com.obvious.samplenotes.utils.DateUtils

/**
 * Created by Prasoon on 2020-01-28.
 */
class NotesAdapter(
    private val notesList: ArrayList<Note>,
    val onNoteClicked: (note: Note) -> Unit
) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_note, parent, false)

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTitle).text =
            notesList[position].title
        holder.itemView.findViewById<TextView>(R.id.tvTime).text =
            DateUtils.getDate(notesList[position].time.toLong())
        holder.itemView.findViewById<CardView>(R.id.cvNote)
            .setOnClickListener {
                onNoteClicked(notesList[position])
            }
    }

    override fun getItemCount() = notesList.size
}