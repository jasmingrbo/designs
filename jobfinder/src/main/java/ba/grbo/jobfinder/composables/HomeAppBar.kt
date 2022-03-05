package ba.grbo.jobfinder.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.ui.theme.eastBay
import ba.grbo.jobfinder.ui.theme.mineShaft

@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier,
    userName: String,
    onUserButtonClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            val greeting = stringResource(R.string.home_user_greeting)
            val userGreeting = buildAnnotatedString {
                val style = MaterialTheme.typography.subtitle2.toSpanStyle()
                withStyle(style) {
                    append(greeting)
                    append(", ")
                }
                withStyle(style.copy(fontWeight = FontWeight.SemiBold)) {
                    append(userName)
                }
            }
            Text(
                text = userGreeting,
                color = mineShaft
            )
            VerticalSpacer(4.dp)
            Text(
                text = stringResource(R.string.home_find_job_label),
                style = MaterialTheme.typography.h5,
                color = eastBay
            )
        }

        Image(
            modifier = Modifier
                .align(Alignment.Top)
                .size(44.dp)
                .clip(CircleShape)
                .clickable(
                onClick = onUserButtonClicked,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            ),
            painter = painterResource(R.drawable.user),
            contentDescription = stringResource(R.string.common_user_photo)
        )
    }
}