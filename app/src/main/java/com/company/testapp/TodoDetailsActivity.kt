package com.company.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.testapp.model.Todo

import kotlinx.android.synthetic.main.activity_todo_details.*

class TodoDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_details)

        val todo: Todo = intent.getSerializableExtra("item") as Todo


        completed.text = todo.completed.toString()
        userId.text = todo.userId.toString()
        id.text = todo.id.toString()
        title_item.text = todo.title
    }

}
