package com.example.todolist

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.Task

// отображает данные в списке RecycleView
class TaskAdapter(private val context: Context, val listener: TaskClickListener):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    private val todoList = ArrayList<Task>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateList(newList: List<Task>){
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val item = todoList[position]
        //holder.title.isSelected = true
        holder.note.text = item.note
        when(item.priority){
            1 ->  {
                holder.text_priority.setBackgroundResource(R.drawable.priority1)
                holder.text_priority.text = 1.toString()
            }
            2 -> {
                holder.text_priority.setBackgroundResource(R.drawable.priority2)
                holder.text_priority.text = 2.toString()
            }
            else -> {
                holder.text_priority.setBackgroundResource(R.drawable.priority3)
                holder.text_priority.text = 3.toString()
            }
        }

        holder.todo_layout.setOnClickListener {
            listener.onItemClicked(todoList[holder.adapterPosition])
        }
    }

    inner class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val todo_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val text_priority = itemView.findViewById<TextView>(R.id.textView_priority)
    }
    interface TaskClickListener {
        fun onItemClicked(task: Task)
    }
}