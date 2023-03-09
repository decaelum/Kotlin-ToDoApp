package com.yagizcgrgn.to_doapp.model

import androidx.lifecycle.*
import com.yagizcgrgn.to_doapp.room.TaskItemRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskViewModel(private val repository: TaskItemRepository) : ViewModel() {

    var taskItems : LiveData<List<TaskItem>> = repository.allTaskItems.asLiveData()


    fun addTaskItem(newTask : TaskItem) = viewModelScope.launch {
        repository.insertTaskItem(newTask)
    }


    fun updateTaskItem(taskItem: TaskItem) = viewModelScope.launch{
        repository.updateTaskItem(taskItem)
    }

    fun setCompleted(taskItem: TaskItem) = viewModelScope.launch {
        if (!taskItem.isCompleted())
            taskItem.completedDateString = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)
    }

}
class TaskItemModelFactory(private val  repository: TaskItemRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repository) as T

        throw java.lang.IllegalArgumentException("Unknown Class for View Model")
    }
}