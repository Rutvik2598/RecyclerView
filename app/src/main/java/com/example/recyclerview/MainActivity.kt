package com.example.recyclerview

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonTodo: Button
    private lateinit var todoEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rvTodos)
        buttonTodo = findViewById(R.id.btnAddTodo)
        todoEditText = findViewById(R.id.etTodo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var todoList = mutableListOf(
            Todo("Buy groceries", false),
            Todo("Complete homework", true),
            Todo("Call the bank", false),
            Todo("Go for a run", true),
            Todo("Clean the house", false),
            Todo("Finish reading the book", true),
            Todo("Prepare dinner", false)
        )

        val adapter = TodoAdapter(todoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonTodo.setOnClickListener {
            val title = todoEditText.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)
            adapter.notifyItemInserted(todoList.size - 1)
            todoEditText.setText("")
        }
    }
}