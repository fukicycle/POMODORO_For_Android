package com.example.pomodoro

const val FIRST = 1 * 60L
const val SECOND = 1 * 60L
const val THIRD = 1 * 60L
const val FOURTH = 1 * 60L
const val BREAK = 2 * 60L
const val L_BREAK = 3 * 60L
const val INTERVAL = 1L

fun getMillSecFromEnum(taskEnum: TaskEnum): Long {
    return when (taskEnum) {
        TaskEnum.FIRST -> FIRST * 1000
        TaskEnum.SECOND -> SECOND * 1000
        TaskEnum.THIRD -> THIRD * 1000
        TaskEnum.FOURTH -> FOURTH * 1000
        TaskEnum.BREAK -> BREAK * 1000
        TaskEnum.L_BREAK -> L_BREAK * 1000
    }
}

fun getFormattedSec(sec:Long):String{
    return "00:${(sec / 60).toString().padStart(2, '0')}:${(sec % 60).toString().padStart(2, '0')}"
}