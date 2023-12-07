package com.graphql.task.ui

import android.content.Context
import android.widget.Toast
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*


fun Context.showToast(strMessage: String) {
    Toast.makeText(this, strMessage, Toast.LENGTH_SHORT).show()
}
