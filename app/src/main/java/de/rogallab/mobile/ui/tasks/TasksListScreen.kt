package de.rogallab.mobile.ui.tasks

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import de.rogallab.mobile.R
import de.rogallab.mobile.domain.utilities.logDebug
import de.rogallab.mobile.domain.utilities.logInfo
import de.rogallab.mobile.ui.navigation.AppNavigationBar
import de.rogallab.mobile.ui.navigation.NavScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun TasksListScreen(
   navController: NavController,
   viewModel: TasksViewModel,
) {
   val tag: String = "ok>TasksListScreen    ."

   val snackbarHostState = remember { SnackbarHostState() }

   BackHandler(
      enabled = true,
      onBack = {
         logInfo(tag, "Back Navigation (Abort)")
         navController.popBackStack(NavScreen.PeopleList.route,inclusive = false)
      }
   )

   Scaffold(
      topBar = {
         TopAppBar(
            title = { Text(stringResource(R.string.tasks_list)) },
            navigationIcon = { IconButton( onClick = {
               // save data
               logInfo(tag, "Reverse Navigation")
               // Navigate to 'PeopleList' destination and clear the back stack
               navController.navigate(route = NavScreen.PeopleList.route) {
                  popUpTo(route = NavScreen.PeopleList.route) { inclusive = true }
               }
            }) {
               Icon(
                  imageVector = Icons.Default.ArrowBack,
                  contentDescription = stringResource(R.string.back)
               )}
            }
         )
      },
      floatingActionButton = {
         FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.tertiary,
            onClick = {
               logDebug(tag, "Forward Navigation: FAB clicked")
               navController.navigate(route = NavScreen.TaskInput.route)
            }
         ) {
            Icon(Icons.Default.Add, stringResource(id = R.string.task_input))
         }
      },
      bottomBar = {
         AppNavigationBar(navController = navController)
      },
      snackbarHost = {
         SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(
               snackbarData = data,
               actionOnNewLine = true
            )
         }
      },
      content = { val text = "E M P T Y" }
   )
}