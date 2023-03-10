package com.yagizcgrgn.to_doapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yagizcgrgn.to_doapp.app.TodoApplication
import com.yagizcgrgn.to_doapp.listener.TaskItemClickListener
import com.yagizcgrgn.to_doapp.adapter.TaskItemAdapter
import com.yagizcgrgn.to_doapp.view.fragments.NewTaskSheet
import com.yagizcgrgn.to_doapp.databinding.ActivityMainBinding
import com.yagizcgrgn.to_doapp.model.TaskItem
import com.yagizcgrgn.to_doapp.model.TaskItemModelFactory
import com.yagizcgrgn.to_doapp.model.TaskViewModel

class MainActivity : AppCompatActivity() , TaskItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel  by viewModels {
      TaskItemModelFactory((application as TodoApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btNewTask.setOnClickListener{
            NewTaskSheet(null).show(supportFragmentManager,"newTaskTag")
        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoRecyclerList.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it, mainActivity)

            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem) {
        NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }
}