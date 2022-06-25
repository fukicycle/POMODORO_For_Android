package com.example.pomodoro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.pomodoro.databinding.ListTaskListBinding

class ListViewAdapter(
    private val context: Context,
    private val resourceID: Int,
    private val items: List<*>
) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return items[p0] as Any
    }

    override fun getItemId(p0: Int): Long {
        return -1
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        return ListTaskListBinding.inflate(LayoutInflater.from(context)).apply {
            when (resourceID) {
                R.layout.list_task_list -> {
                    val item = items[p0] as Task
                    taskCheckBox.text = item.name
                    if(item.isDone) root.background = context.getDrawable(R.drawable.style_task_list_done)

                }
            }
        }.root
    }
}