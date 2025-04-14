package com.godsonpeya.myfamily.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun NoDataFound(padding: PaddingValues) {
    Box(modifier = Modifier.Companion.padding(padding)) {
        Text(
            text = "No members found",
            modifier = Modifier.Companion.align(Alignment.Companion.Center)
        )
    }
}