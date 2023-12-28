package com.example.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.Task
import com.example.todolist.database.TaskDao
import com.example.todolist.database.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//
class TasksViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TaskRepository
    val allTodo : LiveData<List<Task>>

    init {
        val dao = TaskDatabase.getDatabase(application).getTodoDao()
        repository = TaskRepository(dao)
        allTodo = repository.allTodos
    }

    fun insertTodo(task: Task) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(task)
    }

    fun updateTodo(task: Task) = viewModelScope.launch(Dispatchers.IO){
        repository.update(task)
    }

    fun deleteTodo(task: Task) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(task)
    }
}