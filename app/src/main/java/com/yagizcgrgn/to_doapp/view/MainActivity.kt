package com.yagizcgrgn.to_doapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.yagizcgrgn.to_doapp.view.fragments.NewTaskSheet
import com.yagizcgrgn.to_doapp.databinding.ActivityMainBinding
import com.yagizcgrgn.to_doapp.model.TaskViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        binding.btNewTask.setOnClickListener{
            NewTaskSheet().show(supportFragmentManager,"newTaskTag")
        }

        taskViewModel.name.observe(this){
            binding.taskName.text = String.format("Task Name: %s",it)
        }
        taskViewModel.desc.observe(this){
            binding.taskDescription.text = String.format("Task Name: %s",it)
        }
    }
}