package com.example.wibb.tools.err

import android.content.Context
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.wibb.SplashActivity

/*
 * Usage:
 *
 * try {
 *      // application code
 * }
 * catch (exception: Exception) {
 *      ErrorHandler.of(this).handle(exception)
 * }
 */
class ErrorHandler constructor(context: Context) {
    private val context: Context = context.applicationContext

    companion object {
        fun of(context: Context): ErrorHandler {
            return ErrorHandler(context)
        }
    }

    fun handle(throwable: Throwable){
        handle(WibbError.fromThrowable(throwable))
    }

    fun handle(wibbError: WibbError){
        if(wibbError.occurrenceDescription.isEmpty())
            wibbError.occurrenceDescription = "[${context.javaClass.name}]: ${wibbError.occurrenceDescription}"
        inform(wibbError.message)
        wibbError.report()
    }

    fun handle(message: String){
        handle(message, "NO_OCCURRENCE_DESC_HANDLER")
    }

    fun handle(message: String, occDesc: String){
        val e = WibbError()
        e.message = message
        e.occurrenceDescription = occDesc
        handle(e)
    }

    private fun inform(message: String){
        Log.e("[ErrorHandler.${context.javaClass.name}]", "message")
        Toast.makeText(context, "ERROR: $message", Toast.LENGTH_SHORT).show()
    }
}