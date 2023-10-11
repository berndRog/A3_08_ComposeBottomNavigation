package de.rogallab.mobile.domain.model

import de.rogallab.mobile.domain.utilities.zonedDateTimeNow
import java.time.ZonedDateTime
import java.util.UUID

data class Appointment (
   val title:String = "",
   val dateTime: ZonedDateTime = zonedDateTimeNow(),  // in machine time zone
   val description: String = "",
   // Appointment [1] <--> [0..*] Attendance [0..*] <--> [1] Person
   // val attendances: MutableList<Attendance> = mutableListOf<Attendance>()
   val id: UUID = UUID.randomUUID(),
)