package de.rogallab.mobile

import android.app.Application
import de.rogallab.mobile.domain.utilities.logInfo

class AppStart : Application() {

   override fun onCreate() {
      super.onCreate()
      logInfo(tag,"onStart()")
   }

   companion object {
      //                       12345678901234567890123
      private const val tag = "ok>AppStart           ."
      const val database_name:    String = "B4_02_TasksMeetings.db"
      const val database_version: Int    = 1
      const val URL:              String = "http://10.0.2.2:5000/api/v1.0/"
   }
}