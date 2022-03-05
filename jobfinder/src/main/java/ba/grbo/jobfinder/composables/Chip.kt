package ba.grbo.jobfinder.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ba.grbo.jobfinder.ui.theme.inter

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean = false,
    backgroundColor: Color,
    contentColor: Color,
    shape: Shape = MaterialTheme.shapes.small,
    contentPadding: PaddingValues,
    textStyle: TextStyle = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
        lineHeight = 11.sp
    ),
    onClicked: ((String) -> Unit)? = null
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = if (selected) MaterialTheme.colors.primary else backgroundColor,
        contentColor = if (selected) MaterialTheme.colors.onPrimary else contentColor
    ) {
        Text(
            modifier = Modifier
                .clickable(
                    enabled = onClicked != null,
                    onClick = { onClicked?.invoke(text) },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                )
                .padding(contentPadding),
            text = text,
            style = textStyle
        )
    }
}