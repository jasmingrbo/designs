package ba.grbo.healthcare.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import ba.grbo.healthcare.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    count: Int,
    backgroundColor: Color,
    contentColor: Color,
    onClickedInFinalState: () -> Unit
) {
    val scrollPosition = (pagerState.currentPage + pagerState.currentPageOffset)
        .coerceIn(0f, (count - 1f).coerceAtLeast(0f))
    val sweepAngle = (1 + if (scrollPosition > count - 2 || scrollPosition < -count + 2) {
        ((scrollPosition - (count - 2)) * 2 + (count - 2)).coerceAtMost(count - 1f)
    } else {
        scrollPosition
    }) * (360 / count)

    val size = 56.dp + if (scrollPosition >= count - 1.5 || scrollPosition <= -count + 1.5) {
        (((scrollPosition - count + 1.5) * 2).coerceAtMost(1.0) * 8).dp
    } else {
        0.dp
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 52.dp)
            .background(
                Color.White.copy(alpha = ((scrollPosition - count + 1.5f) * 2).coerceIn(0f, 1f)),
                CircleShape
            )
            .clip(CircleShape)
            .then(
                if (pagerState.currentPage == count - 1 && pagerState.currentPageOffset == 0f) {
                    Modifier.clickable(
                        enabled = true,
                        role = Role.Button,
                        onClick = onClickedInFinalState
                    )
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 32.dp),
            text = stringResource(R.string.onboarding_continue_button),
            color = Color.Black.copy(alpha = ((scrollPosition - count + 1.5f) * 2).coerceIn(0f, 1f))
        )

        Box(
            modifier = modifier
                .offset {
                    IntOffset(
                        x = if (scrollPosition > count - 2) {
                            ((scrollPosition - count + 2) * 2 * ((maxWidth / 2) - 39.dp).roundToPx())
                                .roundToInt()
                                .coerceAtMost(maxWidth.roundToPx() / 2 - 39.dp.roundToPx())
                        } else {
                            0
                        },
                        y = 0
                    )
                }
                .size(78.dp)
                .drawBehind {
                    circleGround(color = Color.White.copy(alpha = 0.1f))
                    circleGround(color = Color.White, sweepAngle = sweepAngle)
                },
            contentAlignment = Alignment.Center
        ) {
            val scope = rememberCoroutineScope()
            val width = LocalDensity.current.run {
                (this@BoxWithConstraints.maxWidth + 104.dp).toPx()
            }
            Button(
                modifier = Modifier.size(size),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backgroundColor,
                    contentColor = contentColor,
                    disabledBackgroundColor = backgroundColor,
                    disabledContentColor = contentColor
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                ),
                enabled = pagerState.currentPageOffset == 0f,
                shape = CircleShape,
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == count - 1) {
                            onClickedInFinalState()
                        } else {
                            pagerState.animateScrollBy(
                                value = width,
                                animationSpec = tween(easing = LinearEasing)
                            )
                        }
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
            }
        }
    }
}

private fun DrawScope.circleGround(
    color: Color,
    startAngle: Float = -90f,
    sweepAngle: Float = 360f
) {
    val strokeWidth = 2.dp.toPx()
    drawArc(
        size = Size(size.width - strokeWidth, size.height - strokeWidth),
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = 2.dp.toPx(),
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = strokeWidth / 2,
            y = strokeWidth / 2
        )
    )
}