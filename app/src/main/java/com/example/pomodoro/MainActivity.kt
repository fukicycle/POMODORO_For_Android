package com.example.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.pomodoro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var sequenceManager:SequenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.apply {
            sequenceManager = SequenceManager(timerTextView,displayTextView)
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
        }.root)
    }
}