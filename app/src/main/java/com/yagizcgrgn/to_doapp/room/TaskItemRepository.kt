package com.yagizcgrgn.to_doapp.room

import androidx.annotation.WorkerThread
import com.yagizcgrgn.to_doapp.model.TaskItem
import kotlinx.coroutines.flow.Flow

class TaskItemRepository(private val taskItemDao: TaskItemDao) {
    val allTaskItems: Flow<List<TaskItem>> = taskItemDao.allTaskItems()

    @WorkerThread
    suspend fun insertTaskItem(taskItem: TaskItem)
    {
        taskItemDao.insertTaskItem(taskItem)
    }
    @WorkerThread
    suspend fun updateTaskItem(taskItem: TaskItem)
    {
        taskItemDao.updateTaskItem(taskItem)
    }
}