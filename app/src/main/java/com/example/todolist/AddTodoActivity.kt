package com.example.todolist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.todolist.database.Task
import com.example.todolist.databinding.ActivityAddTodoBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTodoBinding
    private lateinit var task: Task
    private lateinit var oldTodo: Task
    var isUpdate = false


    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldTodo = intent.getSerializableExtra("current_todo") as Task
            binding.etNote.setText(oldTodo.note)
            isUpdate = true
        }catch (e: Exception){
            e.printStackTrace()
        }
        if(isUpdate){
            binding.imgDelete.visibility = View.VISIBLE
        }else{
            binding.imgDelete.visibility = View.INVISIBLE
        }

        binding.imgCheck.setOnClickListener {
            val todoDescription = binding.etNote.text.toString()
            var priority = 1
            if(binding.radioButton2.isChecked){
                priority=2
            }
            if(binding.radioButton3.isChecked){
                priority=3
            }

            if(todoDescription.isNotEmpty()){
                if(isUpdate){
                    task = Task(oldTodo.id, todoDescription, priority)
                }else{
                    task = Task(null, todoDescription, priority)
                }
                //для передачи данных из add в main
                var intent = Intent()
                intent.putExtra("todo", task)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this@AddTodoActivity, "Введите описание задачи", Toast.LENGTH_LONG).show()
                //return@setOnClickListener
            }
        }

        binding.imgDelete.setOnClickListener {
            var intent = Intent()
            intent.putExtra("todo", oldTodo)
            intent.putExtra("delete_todo", true)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }


    }
}