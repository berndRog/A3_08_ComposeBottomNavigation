package de.rogallab.mobile.ui.tasks

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.rogallab.mobile.R
import de.rogallab.mobile.domain.utilities.logInfo
import de.rogallab.mobile.ui.navigation.NavScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskInputScreen(
   navController: NavController,
   viewModel: TasksViewModel,
) {
   val tag = "ok>TaskInputScreen    ."

   BackHandler(
      enabled = true,
      onBack = {
         logInfo(tag, "Back Navigation (Abort)")
         // Navigate to 'PeopleList' destination and clear the back stack.
         navController.popBackStack(NavScreen.PeopleList.route,inclusive = false)
      }
   )

   val snackbarHostState = remember { SnackbarHostState() }
   val coroutineScope = rememberCoroutineScope()
   // testing the snackbar
   // viewModel.onErrorMessage("Test SnackBar: Fehlermeldung ...")

   Scaffold(
      topBar = {
         TopAppBar(
            title = { Text(stringResource(R.string.task_input)) },
            navigationIcon = { IconButton( onClick = {
               // save data
               logInfo(tag, "Reverse Navigation")
               // Navigate to 'TasksList' destination and clear the back stack
               navController.navigate(route = NavScreen.TasksList.route) {
                  popUpTo(route = NavScreen.TasksList.route) { inclusive = true }
               }
            }) {
               Icon(
                  imageVector = Icons.Default.ArrowBack,
                  contentDescription = stringResource(R.string.back)
               )}
            }
         )
      },
      snackbarHost = {
         SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(
               snackbarData = data,
               actionOnNewLine = true
            )
         }
      },
      content = { innerPadding ->
         Column(
            modifier = Modifier
               .padding(top = innerPadding.calculateTopPadding())
               .padding(bottom = innerPadding.calculateBottomPadding())
               .padding(horizontal = 8.dp)
               .fillMaxWidth()
               .verticalScroll(state = rememberScrollState())
         ) {
         }
      }
   )
}