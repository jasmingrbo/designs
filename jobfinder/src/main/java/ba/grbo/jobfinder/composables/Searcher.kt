package ba.grbo.jobfinder.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.ui.theme.inter
import ba.grbo.jobfinder.ui.theme.silver

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
    BasicTextField(
        modifier = modifier,
        value = searchTerm,
        onValueChange = onSearchTermChanged,
        singleLine = true,
        textStyle = textStyle,
        cursorBrush = SolidColor(MaterialTheme.colors.primary)
    ) { innerTextField ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colors.onPrimary,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(start = 16.dp, end = 12.dp),
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint = silver
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                innerTextField()
                this@Row.AnimatedVisibility(
                    visible = searchTerm.isEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = stringResource(R.string.home_searcher_placeholder),
                        style = textStyle,
                        color = silver
                    )
                }
            }

            AnimatedVisibility(
                visible = searchTerm.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(
                    modifier = Modifier.padding(start = 4.dp),
                    onClick = { onSearchTermChanged("") }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.home_reset_button),
                        tint = silver
                    )
                }
            }
        }
    }
}