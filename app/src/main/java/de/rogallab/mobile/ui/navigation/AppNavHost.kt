package de.rogallab.mobile.ui.navigation
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
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
   peopleViewModel: PeopleViewModel,
   tasksViewModel: TasksViewModel,
   appointmentsViewModel: AppointmentsViewModel
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
         enterTransition = { enterAnimation(duration) },
         exitTransition  = { exitAnimation(duration)  }
      ) {
         PeopleListScreen(
            navController = navHostController,
            viewModel = peopleViewModel
         )
      }
      composable(
         route = NavScreen.PersonInput.route,
         enterTransition = { enterAnimation(duration) },
         exitTransition  = { exitAnimation(duration)  }
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
         enterTransition = { enterAnimation(duration) },
         exitTransition  = { exitAnimation(duration)  }
      ) {
         TasksListScreen(
            navController = navHostController,
            viewModel = tasksViewModel
         )
      }
      composable(
         route = NavScreen.TaskInput.route,
         enterTransition = { enterAnimation(duration) },
         exitTransition  = { exitAnimation(duration)  }
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
         enterTransition = { enterAnimation(duration) },
         exitTransition  = { exitAnimation(duration)  }
      ) {
         AppointmentsListScreen(
            navController = navHostController,
            viewModel = appointmentsViewModel
         )
      }
      composable(
         route = NavScreen.AppointmentInput.route,
         enterTransition = { enterAnimation(duration) },
         exitTransition  = { exitAnimation(duration)  }
      ) {
         AppointmentInputScreen(
            navController = navHostController,
            viewModel = appointmentsViewModel
         )
      }
   }
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterAnimation(
   duration: Int
) =
   fadeIn(animationSpec = tween(duration)) +
   slideIntoContainer(
      animationSpec = tween(duration),
      towards = AnimatedContentTransitionScope.SlideDirection.Left
   )

private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitAnimation(
   duration: Int
) =
   fadeOut(animationSpec = tween(duration)) +
   slideOutOfContainer(
      animationSpec = tween(duration),
      towards = AnimatedContentTransitionScope.SlideDirection.Left
   )
