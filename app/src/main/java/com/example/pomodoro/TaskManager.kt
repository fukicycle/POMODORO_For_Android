package com.example.pomodoro

import android.widget.BaseAdapter

class TaskManager(private val adapter: BaseAdapter, private val items: MutableList<Task>) {
    fun add(task: Task) {
        items.add(task)
        refresh()
    }

    fun delete(id: Int) {
        items.remove(items.firstOrNull { it.id == id })
        refresh()
    }

    fun edit(task: Task) {
        val tmp = items.firstOrNull { it.id == task.id }
        if (tmp != null) {
            tmp.name = task.name
            tmp.date = task.date
            tmp.isDone = task.isDone
        }
        refresh()
    }

    fun finished(task: Task) {
        val tmp = items.firstOrNull { it.id == task.id }
        if (tmp != null) {
            tmp.isDone = !task.isDone
        }
        refresh()
    }

    private fun refresh() {
        adapter.notifyDataSetChanged()
    }
}