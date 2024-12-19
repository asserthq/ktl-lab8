package com.example.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todolist_table")
class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "note")
    val note: String?,
    @ColumnInfo(name = "descr")
    val descr: String?,
    @ColumnInfo(name = "priority")
    val priority: Int,
): java.io.Serializable