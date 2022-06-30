package com.example.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import com.example.pomodoro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private val items = mutableListOf<Task>()
    private val adapter = ListViewAdapter(this,R.layout.list_task_list,items)
    private val taskManager = TaskManager(adapter,items)
    private lateinit var sequenceManager: SequenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.apply {
            sequenceManager = SequenceManager(this@MainActivity,timerTextView, displayTextView)
            taskListView.adapter = adapter
            startButton.setOnClickListener {
                sequenceManager.start()
            }

            pauseButton.setOnClickListener {
                sequenceManager.pause()
            }

            resetButton.setOnClickListener {
                sequenceManager.reset()
            }

            prevButton.setOnClickListener {
                sequenceManager.prev()
            }

            nextButton.setOnClickListener {
                sequenceManager.next()
            }

            addTaskButton.setOnClickListener {
                taskManager.add(Task(1, "Test1", false, "2022-01-02"))
            }
        }.root)
    }
}