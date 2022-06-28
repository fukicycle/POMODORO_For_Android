package com.example.pomodoro

import android.os.CountDownTimer

class Timer(
    millisInFuture: Long,
    countDownInterval: Long,
    private val notifyChange: NotifyChange,
    private val currentTaskEnum: TaskEnum
) : CountDownTimer(
    millisInFuture,
    countDownInterval
) {
    override fun onTick(p0: Long) {
        notifyChange.tick(p0 / 1000)
    }

    override fun onFinish() {
        notifyChange.finish(currentTaskEnum)
    }
}