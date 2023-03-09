package com.yagizcgrgn.to_doapp.app

import android.app.Application
import com.yagizcgrgn.to_doapp.room.TaskItemDatabase
import com.yagizcgrgn.to_doapp.room.TaskItemRepository

class TodoApplication : Application() {
    private val database by lazy { TaskItemDatabase.getDatabase(this) }
    val repository by lazy { TaskItemRepository(database.taskItemDao()) }

}