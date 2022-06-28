package com.example.pomodoro

interface NotifyChange {
    fun finish(task: TaskEnum)
    fun tick(sec: Long)
}