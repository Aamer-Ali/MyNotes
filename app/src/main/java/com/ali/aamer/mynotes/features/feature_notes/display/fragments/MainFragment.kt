package com.ali.aamer.mynotes.features.feature_notes.display.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ali.aamer.mynotes.R
import com.ali.aamer.mynotes.core.utils.Constants.TAG
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.databinding.FragmentMainBinding
import com.ali.aamer.mynotes.features.feature_notes.display.adapters.NotesAdapter
import com.ali.aamer.mynotes.features.feature_notes.display.events.NoteEvents
import com.ali.aamer.mynotes.features.feature_notes.display.viewmodels.NoteViewModel
import com.ali.aamer.notesapp.models.notes.NoteResponseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel by viewModels<NoteViewModel>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        adapter = NotesAdapter(::onNoteClick)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindHandlers()
        getNotesList()
        registerAdapter()
        registerNotesObserve()
    }

    private fun bindHandlers() {
        binding.btnAddNote.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNoteFragment(null)
            findNavController().navigate(action)
        }
    }

    private fun registerAdapter() {
        binding.rcvNotes.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rcvNotes.adapter = adapter
    }

    private fun getNotesList() {
        noteViewModel.notesOperation(NoteEvents.GetNotes)
    }

    private fun registerNotesObserve() {
        noteViewModel.noteList.observe(viewLifecycleOwner) { result ->
            binding.progressIndicator.isVisible = false
            when (result) {
                is Resource.Loading -> {
                    binding.progressIndicator.isVisible = true
                }
                is Resource.Failure -> {
                    Log.d(TAG, "registerNotesObserve: ${result.message.toString()}")
                }
                is Resource.Success -> {
                    adapter.submitList(result.data)
                }
            }
        }
    }

    private fun onNoteClick(noteResponseModel: NoteResponseModel) {
        val action = MainFragmentDirections.actionMainFragmentToNoteFragment(noteResponseModel)
        findNavController().navigate(action)
    }
}