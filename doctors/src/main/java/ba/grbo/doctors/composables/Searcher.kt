package ba.grbo.doctors.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.doctors.R
import ba.grbo.doctors.ui.theme.iron
import ba.grbo.doctors.ui.theme.shark
import ba.grbo.doctors.ui.theme.wildSand

@Composable
fun Searcher(
    modifier: Modifier = Modifier,
    searchTerm: String,
    onSearchTermChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = searchTerm,
        onValueChange = onSearchTermChanged,
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.button,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = wildSand,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            trailingIconColor = shark
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.doctors_searcher_placeholder),
                style = MaterialTheme.typography.button,
                color = iron
            )
        },
        trailingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AnimatedVisibility(
                    visible = searchTerm.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = { onSearchTermChanged("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.doctors_reset_button)
                        )
                    }
                }
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null
                )
                HorizontalSpacer(24.dp)
            }
        }
    )
}

@Preview
@Composable
fun SearcherPreview() {
    Preview {
        Searcher(searchTerm = "", onSearchTermChanged = {})
    }
}