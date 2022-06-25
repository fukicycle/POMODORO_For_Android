package com.example.pomodoro

data class Task(
    var id: Int = 0,
    var name: String = "",
    var isDone: Boolean = false,
    var date: String = ""
) {
    override fun toString(): String {
        return name
    }
}
