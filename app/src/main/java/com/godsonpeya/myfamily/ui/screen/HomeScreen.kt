package com.godsonpeya.myfamily.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.godsonpeya.myfamily.data.remote.model.MembersItemRq
import com.godsonpeya.myfamily.navigation.AppScreen
import com.godsonpeya.myfamily.ui.components.MemberFormDialog
import com.godsonpeya.myfamily.ui.components.NoDataFound
import com.godsonpeya.myfamily.ui.viewmodel.MemberViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: MemberViewModel = hiltViewModel()) {


    val members by viewModel.members.collectAsState()

    var openAlertDialog by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = Unit) {
        viewModel.getMembers()
    }
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
        if (members.isEmpty()) {
            NoDataFound(padding)
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(members) { member ->
                    HorizontalDivider(thickness = .3.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(AppScreen.Detail.createRoute(member.id))
                            }
                            .padding(8.dp),
                    ) {
                        Text("${member.firstName} ${member.lastName}")
                    }
                    HorizontalDivider(thickness = .3.dp)
                }

            }
        }

    }

    if (openAlertDialog) {
        MemberFormDialog(
            onCancel = {
                openAlertDialog = !openAlertDialog
            },
            onConfirm = { firstname, lastname ->
                viewModel.addMember(MembersItemRq(firstName = firstname, lastName = lastname))
                viewModel.getMembers()
                openAlertDialog = !openAlertDialog
            })
    }
}

