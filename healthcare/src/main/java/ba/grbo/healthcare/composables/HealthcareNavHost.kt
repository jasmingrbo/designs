package ba.grbo.healthcare.composables

import androidx.compose.animation.VectorConverter
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.healthcare.OnboardingScreenData
import ba.grbo.healthcare.R
import ba.grbo.healthcare.composables.destinations.Destination.ONBOARDING
import ba.grbo.healthcare.composables.destinations.OnboardingScreen
import ba.grbo.healthcare.ui.theme.hintOfRed
import ba.grbo.healthcare.ui.theme.inter
import ba.grbo.healthcare.ui.theme.lavenderMagenta
import ba.grbo.healthcare.ui.theme.riptide
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.math.truncate

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HealthcareNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    showNotImplementedToast: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ONBOARDING.route
    ) {
        composable(ONBOARDING.route) {
            val continueButtonBgColors = remember { listOf(riptide, lavenderMagenta, hintOfRed) }
            val continueButtonContentColors = remember { listOf(Color.White, Color.Black) }
            val data = remember { OnboardingScreenData.dummyData }
            val systemUiController = rememberSystemUiController()
            val pagerState = rememberPagerState()
            var backgroundColor by remember { mutableStateOf(data.first().backgroundColor) }
            var continueButtonBgColor by remember { mutableStateOf(continueButtonBgColors.first()) }
            var continueButtonContentColor by remember { mutableStateOf(continueButtonContentColors.first()) }
            var skipButton by remember { mutableStateOf(Triple(0, 0, 0f)) }

            fun Int.coerce() = coerceIn(0, (pagerState.pageCount - 1).coerceAtLeast(0))

            LaunchedEffect(pagerState) {
                snapshotFlow {
                    Triple(
                        pagerState.currentPage,
                        pagerState.targetPage,
                        pagerState.currentPageOffset
                    )
                }
                    .distinctUntilChanged()
                    .collect { (currentPage, targetPage, currentPageOffset) ->
                        var (current, target, offset) = if (abs(currentPageOffset) in 0.0..1.0) {
                            Triple(currentPage, targetPage, abs(currentPageOffset))
                        } else {
                            val over = truncate(currentPageOffset).toInt()
                            Triple(
                                (currentPage + over).coerce(),
                                (targetPage + over).coerce(),
                                abs(currentPageOffset - over)
                            )
                        }

                        // Going back, take the last value
                        if (current == target) target = skipButton.second

                        backgroundColor = getAnimatedColorAt(
                            initialColor = data[current].backgroundColor,
                            targetColor = data[target].backgroundColor,
                            animationPercentage = offset
                        )

                        if ((current == 1 && target == 2) || (current == 2 && target == 1)) {
                            if (target > current && offset <= 0.5) {
                                val animationPercentage = offset * 2
                                continueButtonContentColor = getAnimatedColorAt(
                                    initialColor = continueButtonContentColors[current - 1],
                                    targetColor = continueButtonContentColors[target - 1],
                                    animationPercentage = animationPercentage
                                )
                                continueButtonBgColor = getAnimatedColorAt(
                                    initialColor = continueButtonBgColors[current],
                                    targetColor = continueButtonBgColors[target],
                                    animationPercentage = animationPercentage
                                )
                            } else if (current > target && offset >= 0.5) {
                                val animationPercentage = (offset - 0.5f) * 2
                                continueButtonContentColor = getAnimatedColorAt(
                                    initialColor = continueButtonContentColors[current - 1],
                                    targetColor = continueButtonContentColors[target - 1],
                                    animationPercentage = animationPercentage
                                )
                                continueButtonBgColor = getAnimatedColorAt(
                                    initialColor = continueButtonBgColors[current],
                                    targetColor = continueButtonBgColors[target],
                                    animationPercentage = animationPercentage
                                )
                            }
                        } else {
                            continueButtonBgColor = getAnimatedColorAt(
                                initialColor = continueButtonBgColors[current],
                                targetColor = continueButtonBgColors[target],
                                animationPercentage = offset
                            )
                        }
                        skipButton = Triple(current, target, offset)
                    }
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawRect(backgroundColor)
                        systemUiController.setSystemBarsColor(backgroundColor)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(modifier = Modifier.weight(1f)) {
                    HorizontalPager(
                        modifier = Modifier.fillMaxSize(),
                        count = data.size,
                        state = pagerState
                    ) { index ->
                        OnboardingScreen(
                            data = data[index],
                            spacer = index != data.lastIndex
                        )
                    }

                    TextButton(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 20.dp),
                        enabled = skipButton.third == 0f && skipButton.first != data.lastIndex && skipButton.second != data.lastIndex,
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.White,
                            disabledContentColor = Color.White.copy(
                                alpha = if (skipButton.third != 0f && skipButton.first != data.lastIndex && skipButton.second != data.lastIndex) {
                                    1f
                                } else if (skipButton.third != 0f && skipButton.second == data.lastIndex) {
                                    1 - skipButton.third
                                } else if (skipButton.third == 0f && skipButton.first == data.lastIndex) {
                                    0f
                                } else if (skipButton.third != 0f && skipButton.second == data.lastIndex - 1) {
                                    skipButton.third
                                } else {
                                    1f
                                }
                            )
                        ),
                        onClick = showNotImplementedToast
                    ) {
                        Text(
                            text = stringResource(R.string.onboarding_skip_button),
                            fontFamily = inter
                        )
                    }
                }
                VerticalSpacer(24.dp)

                HorizontalPagerIndicator(pagerState = pagerState)

                VerticalSpacer(40.dp)

                ContinueButton(
                    pagerState = pagerState,
                    backgroundColor = continueButtonBgColor,
                    contentColor = continueButtonContentColor,
                    onClickedInFinalState = showNotImplementedToast,
                    count = data.size
                )

                VerticalSpacer(20.dp)
            }
        }
    }
}

private fun getAnimatedColorAt(
    initialColor: Color,
    targetColor: Color,
    animationPercentage: Float,
): Color = TargetBasedAnimation(
    animationSpec = tween(easing = LinearEasing),
    typeConverter = (Color.VectorConverter)(Color.Unspecified.colorSpace),
    initialValue = initialColor,
    targetValue = targetColor,
).getValueFromNanos((DefaultDurationMillis * animationPercentage * 1_000_000L).roundToLong())
