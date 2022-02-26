package ba.grbo.doctors.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ba.grbo.doctors.R
import ba.grbo.doctors.ui.theme.grayChateau

@Composable
fun DoctorsScreen(
    onMenuButtonClicked: () -> Unit,
    onUserButtonClicked: () -> Unit
) {
    Column {
        AppBar(
            modifier = Modifier.padding(start = 12.dp, end = 18.dp, top = 60.dp),
            onMenuButtonClicked = onMenuButtonClicked,
            onUserButtonClicked = onUserButtonClicked
        )
        VerticalSpacer(24.dp)
        Headline(modifier = Modifier.padding(horizontal = 24.dp))
        VerticalSpacer(24.dp)

        val (searchTerm, onSearchTermChanged) = remember { mutableStateOf("") }
        Searcher(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 24.dp),
            searchTerm = searchTerm,
            onSearchTermChanged = onSearchTermChanged
        )
        VerticalSpacer(24.dp)
        Categories(
            onConsultationButtonClicked = {},
            onDentalButtonClicked = {},
            onHeartButtonClicked = {},
            onHospitalsButtonClicked = {},
            onMedicinesButtonClicked = {},
            onPhysicianButtonClicked = {},
            onSkinButtonClicked = {},
            onSurgeonButtonClicked = {}
        )
    }
}

@Composable
private fun Headline(modifier: Modifier = Modifier) {
    val headlineTextWords = stringResource(R.string.doctors_headline).split(' ')
    val text = buildAnnotatedString {
        withStyle(MaterialTheme.typography.h4.toSpanStyle()) {
            append(headlineTextWords.first() + " ")
        }
        withStyle(MaterialTheme.typography.h4.toSpanStyle().copy(color = grayChateau)) {
            for (i in (1..headlineTextWords.lastIndex)) {
                append(headlineTextWords[i])
                if (i != headlineTextWords.lastIndex) append(" ")
            }
        }
    }

    Text(modifier = modifier, text = text)
}