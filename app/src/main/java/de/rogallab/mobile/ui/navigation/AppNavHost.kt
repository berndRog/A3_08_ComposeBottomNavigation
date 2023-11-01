package de.rogallab.mobile.ui.navigation
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.rogallab.mobile.ui.appointments.AppointmentInputScreen
import de.rogallab.mobile.ui.appointments.AppointmentsListScreen
import de.rogallab.mobile.ui.appointments.AppointmentsViewModel
import de.rogallab.mobile.ui.people.PeopleListScreen
import de.rogallab.mobile.ui.people.PeopleViewModel
import de.rogallab.mobile.ui.people.PersonInputScreen
import de.rogallab.mobile.ui.tasks.TaskInputScreen
import de.rogallab.mobile.ui.tasks.TasksListScreen
import de.rogallab.mobile.ui.tasks.TasksViewModel

@Composable
fun AppNavHost(
   peopleViewModel: PeopleViewModel = viewModel(),
   tasksViewModel: TasksViewModel  = viewModel(),
   appointmentsViewModel: AppointmentsViewModel  = viewModel()
) {
   val tag ="ok>AppNavHost         ."

   val navHostController: NavHostController = rememberNavController()

   // https://developer.android.com/jetpack/compose/animation/quick-guide
   val duration  = 1000

   NavHost(
      navController = navHostController,
      startDestination = NavScreen.PeopleList.route,
      enterTransition = { EnterTransition.None },
      exitTransition = { ExitTransition.None }
   ) {

      // PeopleList, PersonInput, PersonDetail
      // ----------------------------------------------------------
      composable(
         route = NavScreen.PeopleList.route,
         enterTransition = { enterTransition(duration) },
         exitTransition  = { exitTransition(duration)  }
      ) {
         PeopleListScreen(
            navController = navHostController,
            viewModel = peopleViewModel
         )
      }
      composable(
         route = NavScreen.PersonInput.route,
         enterTransition = { enterTransition(duration) },
         exitTransition  = { exitTransition(duration)  }
      ) {
         PersonInputScreen(
            navController = navHostController,
            viewModel = peopleViewModel
         )
      }

      // TasksList, PersonInput, PersonDetail
      // ----------------------------------------------------------
      composable(
         route = NavScreen.TasksList.route,
         enterTransition = { enterTransition(duration) },
         exitTransition  = { exitTransition(duration)  }
      ) {
         TasksListScreen(
            navController = navHostController,
            viewModel = tasksViewModel
         )
      }
      composable(
         route = NavScreen.TaskInput.route,
         enterTransition = { enterTransition(duration) },
         exitTransition  = { exitTransition(duration)  }
      ) {
         TaskInputScreen(
            navController = navHostController,
            viewModel = tasksViewModel
         )
      }


      // AppointsmentsList, AppointmentInput, AppointmentDetail
      // ----------------------------------------------------------
      composable(
         route = NavScreen.AppointmentsList.route,
         enterTransition = { enterTransition(duration) },
         exitTransition  = { exitTransition(duration)  }
      ) {
         AppointmentsListScreen(
            navController = navHostController,
            viewModel = appointmentsViewModel
         )
      }
      composable(
         route = NavScreen.AppointmentInput.route,
         enterTransition = { enterTransition(duration) },
         exitTransition  = { exitTransition(duration)  }
      ) {
         AppointmentInputScreen(
            navController = navHostController,
            viewModel = appointmentsViewModel
         )
      }
   }
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(
   duration: Int
) =
   fadeIn(animationSpec = tween(duration)) +
   slideIntoContainer(
      animationSpec = tween(duration),
      towards = AnimatedContentTransitionScope.SlideDirection.Left
   )

private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(
   duration: Int
) =
   fadeOut(animationSpec = tween(duration)) +
   slideOutOfContainer(
      animationSpec = tween(duration),
      towards = AnimatedContentTransitionScope.SlideDirection.Left
   )
