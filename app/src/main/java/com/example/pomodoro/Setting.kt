package com.example.pomodoro

const val FIRST = 25 * 60L
const val SECOND = 25 * 60L
const val THIRD = 25 * 60L
const val FOURTH = 25 * 60L
const val BREAK = 5 * 60L
const val L_BREAK = 15 * 60L
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