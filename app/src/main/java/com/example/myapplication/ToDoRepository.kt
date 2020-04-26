package com.example.myapplication

import java.util.*

object ToDoRepository {
    private val items = listOf(


        ToDoModel(
            description = "make me go out",
            checked = "false",
            id = UUID.randomUUID().toString()
        ),
        ToDoModel(
            description = "give me green tea",
            checked = "false",
            id = UUID.randomUUID().toString()
        ),
        ToDoModel(
            description = "help me someone",
            checked = "true",
            id = UUID.randomUUID().toString()
        )


    ).associateBy { it.id }


    fun getItems() = items.values.toList()

    fun getItemById(id:String) = items[id]
}


