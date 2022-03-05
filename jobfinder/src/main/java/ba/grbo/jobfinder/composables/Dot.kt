package ba.grbo.jobfinder.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun Dot(size: Dp, color: Color) {
    Canvas(modifier = Modifier.size(size),) {
        drawCircle(color = color)
    }
}