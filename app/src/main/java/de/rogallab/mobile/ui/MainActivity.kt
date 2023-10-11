package de.rogallab.mobile.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.rogallab.mobile.ui.appointments.AppointmentsViewModel
import de.rogallab.mobile.ui.navigation.AppNavHost
import de.rogallab.mobile.ui.people.PeopleViewModel
import de.rogallab.mobile.ui.tasks.TasksViewModel
import de.rogallab.mobile.ui.theme.AppTheme

class MainActivity : BaseActivity(tag) {

   private val _peopleViewModel by viewModels<PeopleViewModel>()
   private val _tasksViewModel by viewModels<TasksViewModel>()
   private val _appointmentsViewModel by viewModels<AppointmentsViewModel>()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      setContent {
         AppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
               modifier = Modifier.fillMaxSize(),
               color = MaterialTheme.colorScheme.background
            ) {
               AppNavHost(_peopleViewModel, _tasksViewModel, _appointmentsViewModel)
            }
         }
      }
   }
   companion object {
      const val isInfo = true
      const val isDebug = true
      //12345678901234567890123
      private const val tag = "ok>MainActivity       ."
   }
}

@Preview(showBackground = true)
@Composable
fun Preview() {

   AppTheme {
   }
}