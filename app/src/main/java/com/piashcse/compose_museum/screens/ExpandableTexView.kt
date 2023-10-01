package com.piashcse.compose_museum.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.piashcse.compose_museum.R
import com.piashcse.compose_museum.components.ExpandableText

@Composable
fun ExpandableTexView(navController: NavController) {
    ExpandableText(text = stringResource(id = R.string.text_android_os))
}