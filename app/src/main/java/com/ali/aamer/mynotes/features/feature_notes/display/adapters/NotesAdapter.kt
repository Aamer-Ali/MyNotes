package com.ali.aamer.mynotes.features.feature_notes.display.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ali.aamer.mynotes.databinding.NoteItemBinding
import com.ali.aamer.notesapp.models.notes.NoteResponseModel

class NotesAdapter(private val onNoteClick: (NoteResponseModel) -> Unit) :
    ListAdapter<NoteResponseModel, NotesAdapter.NotesViewHolder>(NoteDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        note?.let {
            holder.bind(note)
        }
    }

    inner class NotesViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteResponseModel) {
            binding.tvNoteTitle.text = note.title
            binding.tvNoteDescription.text = note.description
            binding.root.setOnClickListener {
                onNoteClick(note)
            }
        }
    }

    class NoteDiffUtils : DiffUtil.ItemCallback<NoteResponseModel>() {
        override fun areItemsTheSame(
            oldItem: NoteResponseModel,
            newItem: NoteResponseModel
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: NoteResponseModel,
            newItem: NoteResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}