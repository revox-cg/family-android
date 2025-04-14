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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.godsonpeya.myfamily.R
import com.godsonpeya.myfamily.data.remote.model.MembersItemRq
import com.godsonpeya.myfamily.ui.components.MemberAlertDialog
import com.godsonpeya.myfamily.ui.components.MemberFormDialog
import com.godsonpeya.myfamily.ui.components.NoDataFound
import com.godsonpeya.myfamily.ui.viewmodel.MemberViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    memberId: String,
    viewModel: MemberViewModel = hiltViewModel()
) {

    var openEditDialog by remember { mutableStateOf(false) }
    var openAlertDialog by remember { mutableStateOf(false) }

    val member by viewModel.member.collectAsState()

    LaunchedEffect(key1 = memberId) {
        viewModel.getMember(memberId)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {},

                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
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
        if (member == null) {
            NoDataFound(padding)
        } else {
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

                Text(text = "${member?.firstName} ${member?.lastName}")
            }
        }
    }

    if (openEditDialog) {
        MemberFormDialog(
            member = member,
            action = "Modifier",
            onCancel = {
                openEditDialog = !openEditDialog
            },
            onConfirm = { firstname, lastname ->
                viewModel.updateMember(
                    id = memberId,
                    member = MembersItemRq(
                        id = memberId,
                        firstName = firstname,
                        lastName = lastname
                    )
                )
                viewModel.getMember(id = memberId)
                openEditDialog = !openEditDialog
            })
    }

    if (openAlertDialog) {
        MemberAlertDialog(
            onCancel = {
                openAlertDialog = !openAlertDialog
            },
            onConfirm = {
                viewModel.deleteMember(id = memberId)
                navHostController.popBackStack()
            })
    }


}


@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(navHostController = rememberNavController(), memberId = "ddd")
}