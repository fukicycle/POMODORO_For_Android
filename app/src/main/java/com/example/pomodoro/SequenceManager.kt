package com.example.pomodoro

import android.content.Context
import android.widget.TextView

class SequenceManager(
    private val context: Context,
    private val timerTextView: TextView,
    private val displayTextView: TextView
) :
    NotifyChange {
    private var timer = Timer(getMillSecFromEnum(TaskEnum.FIRST), INTERVAL, this, TaskEnum.FIRST)
    private var currentSec = 0L
    private var previousTask = TaskEnum.L_BREAK
    private var currentTask = TaskEnum.FIRST
    private var nextTask = TaskEnum.BREAK
    private var isPause = false
    private var isRun = false
    private val notificationManager = NotificationManager(context)
    override fun finish(task: TaskEnum) {
        notificationManager.set()
        isRun = false
        next()
        start()
    }

    override fun tick(sec: Long) {
        currentSec = sec
        timerTextView.text = getFormattedSec(sec)
    }

    fun start() {
        if (isRun) return
        if (!isPause) {
            timer.start()
            currentSec = 0
        } else {
            timer = Timer(currentSec * 1000, INTERVAL, this, currentTask)
            timer.start()
            isPause = false
        }
        isRun = true
        displayTextView.text = currentTask.name
    }

    fun pause() {
        if (!isRun) return
        isPause = true
        isRun = false
        timer.cancel()
        displayTextView.text = "PAUSE"
    }

    fun reset() {
        if (isRun) return
        isPause = false
        timer.cancel()
        timer = Timer(getMillSecFromEnum(TaskEnum.FIRST), INTERVAL, this, TaskEnum.FIRST)
        timerTextView.text = getFormattedSec(FIRST)
        displayTextView.text = TaskEnum.FIRST.name
        previousTask = TaskEnum.L_BREAK
        currentTask = TaskEnum.FIRST
        nextTask = TaskEnum.BREAK

    }

    fun next() {
        if (isRun) return
        when (currentTask) {
            TaskEnum.FIRST -> {
                timer = Timer(getMillSecFromEnum(TaskEnum.BREAK), INTERVAL, this, TaskEnum.BREAK)
                previousTask = TaskEnum.FIRST
                currentTask = TaskEnum.BREAK
                nextTask = TaskEnum.SECOND
            }
            TaskEnum.SECOND -> {
                timer = Timer(getMillSecFromEnum(TaskEnum.BREAK), INTERVAL, this, TaskEnum.BREAK)
                previousTask = TaskEnum.SECOND
                currentTask = TaskEnum.BREAK
                nextTask = TaskEnum.THIRD
            }
            TaskEnum.THIRD -> {
                timer = Timer(getMillSecFromEnum(TaskEnum.BREAK), INTERVAL, this, TaskEnum.BREAK)
                previousTask = TaskEnum.THIRD
                currentTask = TaskEnum.BREAK
                nextTask = TaskEnum.FOURTH
            }
            TaskEnum.FOURTH -> {
                timer = Timer(getMillSecFromEnum(TaskEnum.BREAK), INTERVAL, this, TaskEnum.BREAK)
                previousTask = TaskEnum.FOURTH
                currentTask = TaskEnum.L_BREAK
                nextTask = TaskEnum.FIRST
            }
            TaskEnum.L_BREAK -> {
                timer = Timer(getMillSecFromEnum(TaskEnum.FIRST), INTERVAL, this, TaskEnum.FIRST)
                previousTask = TaskEnum.L_BREAK
                currentTask = TaskEnum.FIRST
                nextTask = TaskEnum.BREAK
            }
            TaskEnum.BREAK -> {
                timer = Timer(getMillSecFromEnum(nextTask), INTERVAL, this, nextTask)
                previousTask = TaskEnum.BREAK
                currentTask = nextTask
                nextTask = TaskEnum.BREAK
            }
        }
        displayTextView.text = currentTask.name
        timerTextView.text = getFormattedSec(getMillSecFromEnum(currentTask) / 1000)
        currentSec = 0
    }

    fun prev() {
        if (isRun) return
        when (currentTask) {
            TaskEnum.FIRST -> {
                timer =
                    Timer(getMillSecFromEnum(TaskEnum.L_BREAK), INTERVAL, this, TaskEnum.L_BREAK)
                previousTask = TaskEnum.FOURTH
                currentTask = TaskEnum.L_BREAK
                nextTask = TaskEnum.FIRST
            }
            TaskEnum.SECOND -> {
                timer = Timer(getMillSecFromEnum(TaskEnum.BREAK), INTERVAL, this, TaskEnum.BREAK)
                previousTask = TaskEnum.FIRST
                currentTask = TaskEnum.BREAK
                nextTask = TaskEnum.SECOND
            }
            TaskEnum.THIRD -> {
                timer = Timer(getMillSecFromEnum(TaskEnum.BREAK), INTERVAL, this, TaskEnum.BREAK)
                previousTask = TaskEnum.SECOND
                currentTask = TaskEnum.BREAK
                nextTask = TaskEnum.THIRD
            }
            TaskEnum.FOURTH -> {
                timer = Timer(getMillSecFromEnum(TaskEnum.BREAK), INTERVAL, this, TaskEnum.BREAK)
                previousTask = TaskEnum.THIRD
                currentTask = TaskEnum.BREAK
                nextTask = TaskEnum.FOURTH
            }
            TaskEnum.L_BREAK -> {
                timer = Timer(getMillSecFromEnum(TaskEnum.FOURTH), INTERVAL, this, TaskEnum.FOURTH)
                previousTask = TaskEnum.BREAK
                currentTask = TaskEnum.FOURTH
                nextTask = TaskEnum.L_BREAK
            }
            TaskEnum.BREAK -> {
                timer = Timer(getMillSecFromEnum(previousTask), INTERVAL, this, previousTask)
                currentTask = previousTask
                previousTask = TaskEnum.BREAK
                nextTask = TaskEnum.BREAK
            }
        }
        displayTextView.text = currentTask.name
        timerTextView.text = getFormattedSec(getMillSecFromEnum(currentTask) / 1000)
        currentSec = 0
    }
}