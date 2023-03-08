package com.yagizcgrgn.to_doapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yagizcgrgn.to_doapp.databinding.FragmentNewTaskSheetBinding
import com.yagizcgrgn.to_doapp.model.TaskViewModel


class NewTaskSheet : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity)[TaskViewModel::class.java]
        binding.btSave.setOnClickListener{
            save()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun save(){
        taskViewModel.name.value = binding.name.text.toString()
        taskViewModel.desc.value = binding.desc.text.toString()

        binding.name.setText("")
        binding.desc.setText("")

        dismiss()
    }

}