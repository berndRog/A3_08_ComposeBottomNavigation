package de.rogallab.mobile.ui.people

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import de.rogallab.mobile.R
import de.rogallab.mobile.domain.utilities.logInfo
import de.rogallab.mobile.ui.navigation.NavScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonInputScreen(
   navController: NavController,
   viewModel: PeopleViewModel,
) {
   val tag = "ok>PersonInputScreen  ."

   BackHandler(
      enabled = true,
      onBack = {
         logInfo(tag, "Back Navigation (Abort)")
         // Navigate to 'PeopleList' destination and clear the back stack.
         navController.popBackStack(NavScreen.PeopleList.route,inclusive = false)
      }
   )

   val snackbarHostState = remember { SnackbarHostState() }

   Scaffold(
      topBar = {
         TopAppBar(
            title = { Text(stringResource(R.string.person_input)) },
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
      snackbarHost = {
         SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(
               snackbarData = data,
               actionOnNewLine = true
            )
         }
      },
      content = {  }
   )
}