package com.example.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.pomodoro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NotifyChange {
    private lateinit var b: ActivityMainBinding
    private var timer = Timer(FIRST, INTERVAL, this, TaskEnum.FIRST)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.apply {
            startButton.setOnClickListener {
                timer.start()
            }
            pauseButton.setOnClickListener {
                timer.cancel()
            }
        }.root)
    }

    override fun finish(task: TaskEnum) {
    }

    override fun tick(sec: Long) {
        b.timerTextView.text = sec.toString()
    }
}