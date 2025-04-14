package com.godsonpeya.myfamily.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.godsonpeya.myfamily.R
import com.godsonpeya.myfamily.ui.components.MemberFormDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navHostController: NavHostController, memberId: String) {

    var openEditDialog by remember { mutableStateOf(false) }
    var openAlertDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},

                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        openEditDialog = !openEditDialog
                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = {
                        openAlertDialog = !openAlertDialog
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "John Alikoko $memberId")
        }
    }

    if (openEditDialog) {
        MemberFormDialog(
            "firstname", "lastname",
            action = "Modifier",
            onCancel = {
                openAlertDialog = !openAlertDialog
            },
            onConfirm = {
                openAlertDialog = !openAlertDialog
            })
    }

    if (openAlertDialog) {
        MemberFormDialog(
            onCancel = {
                openAlertDialog = !openAlertDialog
            },
            onConfirm = {
                openAlertDialog = !openAlertDialog
            })
    }


}


@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(navHostController = rememberNavController(), memberId = "ddd")
}