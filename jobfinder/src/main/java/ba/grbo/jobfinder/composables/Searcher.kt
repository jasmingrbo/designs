package ba.grbo.jobfinder.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.ui.theme.inter
import ba.grbo.jobfinder.ui.theme.mineShaft
import ba.grbo.jobfinder.ui.theme.silver
import ba.grbo.jobfinder.ui.theme.white

@Composable
fun Searcher(
    modifier: Modifier = Modifier,
    searchTerm: String,
    onSearchTermChanged: (String) -> Unit
) {
    val textStyle = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 16.sp
    )
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = searchTerm,
        onValueChange = onSearchTermChanged,
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        textStyle = textStyle,
        colors = TextFieldDefaults.textFieldColors(
            textColor = mineShaft,
            backgroundColor = white,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            leadingIconColor = silver,
            trailingIconColor = mineShaft
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.home_searcher_placeholder),
                style = textStyle,
                color = silver
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null
            )
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = searchTerm.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(onClick = { onSearchTermChanged("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.home_reset_button),
                        tint = silver
                    )
                }
            }
        }
    )
}