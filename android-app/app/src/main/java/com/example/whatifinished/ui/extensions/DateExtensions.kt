package com.example.whatifinished.ui.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toFormattedDate(): String {
    return try {
        val inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        LocalDate.parse(this, inputFormatter).format(outputFormatter)
    } catch (e: Exception) {
        this
    }
}