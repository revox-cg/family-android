package com.godsonpeya.myfamily.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.godsonpeya.myfamily.navigation.AppScreen
import com.godsonpeya.myfamily.ui.components.MemberFormDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {


    var openAlertDialog by remember { mutableStateOf(false) }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Family members") },

                actions = {
                    IconButton(onClick = {
                        openAlertDialog = !openAlertDialog
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {

            items(200) { memberId ->
                HorizontalDivider(thickness = .3.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(AppScreen.Detail.createRoute(memberId.toString()))
                        }
                        .padding(8.dp),
                ) {
                    Text("John Alikoko $memberId")
                }
                HorizontalDivider(thickness = .3.dp)
            }

        }

    }

    if (openAlertDialog) {
        MemberFormDialog(
            firstname, lastname,
            onCancel = {
                openAlertDialog = !openAlertDialog
            },
            onConfirm = {
                openAlertDialog = !openAlertDialog
            })
    }
}

