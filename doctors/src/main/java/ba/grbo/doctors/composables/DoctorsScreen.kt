package ba.grbo.doctors.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ba.grbo.doctors.Category
import ba.grbo.doctors.Doctor
import ba.grbo.doctors.R
import ba.grbo.doctors.ui.theme.grayChateau

@Composable
fun DoctorsScreen(
    doctors: List<Doctor>,
    onDoctorClicked: (Doctor) -> Unit,
    onDoctorAvailabilityButtonClicked: () -> Unit,
    onMenuButtonClicked: () -> Unit,
    onUserButtonClicked: () -> Unit,
    onCategoryButtonClicked: (Category) -> Unit,
    onViewAllButtonClicked: () -> Unit
) {
    Column {
        AppBar(
            modifier = Modifier.padding(start = 12.dp, end = 18.dp, top = 24.dp),
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
            onCategoryButtonClicked = onCategoryButtonClicked
        )
        VerticalSpacer(12.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.doctors_subtitle),
                style = MaterialTheme.typography.subtitle1
            )
            TextButton(onClick = onViewAllButtonClicked) {
                Text(text = stringResource(R.string.doctors_view_all_button))
            }
        }
        VerticalSpacer(12.dp)
        LazyColumn(modifier = Modifier.padding(horizontal = 24.dp)) {
            items(items = doctors, key = { doctor -> doctor.fullName }) { doctor ->
                Doctor(
                    doctor = doctor,
                    onClicked = onDoctorClicked,
                    onAvailabilityButtonClicked = onDoctorAvailabilityButtonClicked
                )
                VerticalSpacer(16.dp)
            }
        }
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