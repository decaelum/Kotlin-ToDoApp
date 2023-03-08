package com.yagizcgrgn.to_doapp.listener

import com.yagizcgrgn.to_doapp.model.TaskItem

interface TaskItemClickListener {
    fun editTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)
}