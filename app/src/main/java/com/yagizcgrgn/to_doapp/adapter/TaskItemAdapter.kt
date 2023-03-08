package com.yagizcgrgn.to_doapp.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yagizcgrgn.to_doapp.listener.TaskItemClickListener

import com.yagizcgrgn.to_doapp.databinding.RowItemCellBinding
import com.yagizcgrgn.to_doapp.model.TaskItem
import java.time.format.DateTimeFormatter


class TaskItemAdapter(
    private val taskItems : List<TaskItem>,
    private val clickListener: TaskItemClickListener
    ): RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {
        class TaskItemViewHolder(
        private val context :Context,
        private val binding: RowItemCellBinding,
        private val clickListener: TaskItemClickListener
        ): RecyclerView.ViewHolder(binding.root) {

                val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
                fun bindTaskItem(taskItem: TaskItem){
                    binding.name.text = taskItem.name

                    if (taskItem.isCompleted()){
                    binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    }

                    binding.btComplete.setImageResource(taskItem.imageResource())
                    binding.btComplete.setColorFilter(taskItem.imageColor(context))

                    binding.btComplete.setOnClickListener{
                        clickListener.completeTaskItem(taskItem)
                    }
                    binding.taskCellContainer.setOnClickListener{
                        clickListener.editTaskItem(taskItem)
                    }

                    if(taskItem.dueTime != null){
                        binding.dueTime.text = timeFormat.format(taskItem.dueTime)
                    }else {
                        binding.dueTime.text = ""
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = RowItemCellBinding.inflate(from,parent,false)
        return TaskItemViewHolder(parent.context,binding,clickListener)
    }

    override fun getItemCount(): Int {
        return taskItems.size
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bindTaskItem(taskItems[position])
    }
}