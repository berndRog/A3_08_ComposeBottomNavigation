package de.rogallab.mobile.ui.appointments

import android.annotation.SuppressLint
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import de.rogallab.mobile.R
import de.rogallab.mobile.domain.utilities.logDebug
import de.rogallab.mobile.domain.utilities.logInfo
import de.rogallab.mobile.ui.navigation.NavScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentsListScreen(
   navController: NavController,
   viewModel: AppointmentsViewModel
) {
   val tag = "ok>AppointmentsListScreen  ."
   logDebug(tag, "Composition")

   // testing the snackbar
   // viewModel.onErrorMessage("Test SnackBar: Fehlermeldung ...")
   val snackbarHostState = remember { SnackbarHostState() }
   val coroutineScope = rememberCoroutineScope()

   Scaffold(
      topBar = {
         TopAppBar(
            title = { Text(stringResource(R.string.appointments_list)) },
            navigationIcon = { IconButton( onClick = {
               // save data
               logInfo(tag, "Reverse Navigation")
               // Navigate to 'AppointmentsList' destination and clear the back stack
               navController.navigate(route = NavScreen.AppointmentsList.route) {
                  popUpTo(route = NavScreen.AppointmentsList.route) { inclusive = true }
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
               // Navigate to 'PersonDetail' destination and put 'PeopleList' on the back stack
               navController.navigate(
                  route = NavScreen.AppointmentInput.route
               )
            }
         ) {
            Icon(Icons.Default.Add, stringResource(id = R.string.appointment_input))
         }
      },
      snackbarHost = {
         SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(
               snackbarData = data,
               actionOnNewLine = true
            )
         }
      },
      content = { }
   ) // end Scaffold
}