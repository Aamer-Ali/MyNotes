package com.ali.aamer.mynotes.features.feature_notes.display.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ali.aamer.mynotes.databinding.FragmentAddEditNoteBinding


class AddEditNoteFragment : Fragment() {

    private var _binding: FragmentAddEditNoteBinding? = null
    private val binding get() = _binding!!
    private val args: AddEditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
    }

    private fun setInitialData() {
        binding.tvTitle.text = "Add New Note"
        args.note?.let { note ->
            binding.etTitle.setText(note.title)
            binding.etDescription.setText(note.description)
            binding.tvTitle.text = "Edit Note"
        }
    }
}