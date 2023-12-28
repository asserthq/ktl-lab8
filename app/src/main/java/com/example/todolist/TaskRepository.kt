package com.example.todolist

import androidx.lifecycle.LiveData
import com.example.todolist.database.Task
import com.example.todolist.database.TaskDao
////репозиторий, который будет обращаться к dao-интерфейсу и манипулировать данными базы данных
class TaskRepository(private val taskDao: TaskDao) {
    val allTodos: LiveData<List<Task>> = taskDao.getAll()

    suspend fun insert(task: Task){
        taskDao.insert(task)
    }

    suspend fun delete(task: Task){
        taskDao.delete(task)
    }

    suspend fun update(task: Task){
        taskDao.update(task)
    }
}