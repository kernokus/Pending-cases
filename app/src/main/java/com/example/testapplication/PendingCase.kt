package com.example.testapplication

import android.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.*

class PendingCase(_description:String?, _data:Date?) {
    var description=_description
    var data=getCurrentDateTime().toString("yyyy/MM/dd HH:mm:ss")

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}
