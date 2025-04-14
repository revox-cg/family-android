package com.godsonpeya.myfamily.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun MemberFormDialog(
    onCancel: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    Dialog(onDismissRequest = { }) {
        Card(
            modifier = Modifier.Companion.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {

            Column(
                modifier = Modifier.Companion
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {

                Text(text = "Voulez-vous réellement supprimer ce membre ?")
                Row(
                    modifier = Modifier.Companion.fillMaxWidth(),
                    verticalAlignment = Alignment.Companion.CenterVertically,
                    horizontalArrangement = Arrangement.End

                ) {
                    Button(
                        onClick = {
                            onCancel()
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                        )
                    ) {
                        Text(text = "Non")
                    }
                    Spacer(modifier = Modifier.Companion.width(16.dp))
                    Button(onClick = {
                        onConfirm()
                    }) {
                        Text(text = "Oui")
                    }
                }
            }

        }
    }
}