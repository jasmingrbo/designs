package ba.grbo.doctors.composables.destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ba.grbo.doctors.Category
import ba.grbo.doctors.Doctor
import ba.grbo.doctors.R
import ba.grbo.doctors.composables.AppBar
import ba.grbo.doctors.composables.Categories
import ba.grbo.doctors.composables.Doctor
import ba.grbo.doctors.composables.Searcher
import ba.grbo.doctors.composables.VerticalSpacer
import ba.grbo.doctors.ui.theme.grayChateau
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun DoctorsScreen(
    modifier: Modifier = Modifier,
    doctors: List<Doctor>,
    onDoctorClicked: (Int) -> Unit,
    onDoctorAvailabilityButtonClicked: () -> Unit,
    onMenuButtonClicked: () -> Unit,
    onUserButtonClicked: () -> Unit,
    onCategoryButtonClicked: (Category) -> Unit,
    onViewAllButtonClicked: () -> Unit,
    onDoctorsScrolled: () -> Unit
) {
    Column(modifier = modifier) {
        AppBar(
            modifier = Modifier.padding(start = 12.dp, end = 18.dp, top = 24.dp),
            leadingIcon = {
                IconButton(onClick = onMenuButtonClicked) {
                    Icon(
                        painter = painterResource(R.drawable.ic_menu_burger),
                        contentDescription = stringResource(R.string.doctors_menu)
                    )
                }
            },
            trailingIcon = {
                Image(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .clickable(
                            onClick = onUserButtonClicked,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ),
                    painter = painterResource(R.drawable.user),
                    contentDescription = stringResource(R.string.doctors_user)
                )
            }
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
        Categories(onCategoryButtonClicked = onCategoryButtonClicked)
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
        
        val state = rememberLazyListState()
        LazyColumn(
            state = state,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            items(items = doctors, key = { doctor -> doctor.id }) { doctor ->
                Doctor(
                    doctor = doctor,
                    onClicked = onDoctorClicked,
                    onAvailabilityButtonClicked = onDoctorAvailabilityButtonClicked
                )
            }
        }
        LaunchedEffect(state) {
            snapshotFlow { state.isScrollInProgress }
                .distinctUntilChanged()
                .filter { it }
                .collect { onDoctorsScrolled() }
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