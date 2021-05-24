package id.trials.notestodo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_notes.view.*

class AdapterNotes(
    private val notes: MutableList<DataNotes>
) : RecyclerView.Adapter<AdapterNotes.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notes, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.itemView.apply {
            tvTitle.text = currentNote.Title
            cbChecked.isChecked = currentNote.isChecked
            toggleStrikeThrough(tvTitle, currentNote.isChecked)
            cbChecked.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTitle, isChecked)
                currentNote.isChecked = !currentNote.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun addNotes(note: DataNotes) {
        notes.add(note)
        notifyItemInserted(notes.size - 1)
    }

    fun deleteNotes() {
        notes.removeAll { note ->
            note.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTitle.paintFlags = tvTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTitle.paintFlags = tvTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}