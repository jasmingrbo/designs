package ba.grbo.jobfinder.composables.destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.jobfinder.R
import ba.grbo.jobfinder.composables.Dot
import ba.grbo.jobfinder.composables.HorizontalSpacer
import ba.grbo.jobfinder.composables.VerticalSpacer
import ba.grbo.jobfinder.ui.theme.eastBay
import ba.grbo.jobfinder.ui.theme.inter
import ba.grbo.jobfinder.ui.theme.silver
import ba.grbo.jobfinder.ui.theme.white

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onGetStartedButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier.padding(horizontal = 20.dp),
            painter = painterResource(R.drawable.job_finder),
            contentDescription = null
        )
        VerticalSpacer(100.dp)
        val messageFirst = stringResource(R.string.start_message_first)
        val messageSecond = stringResource(R.string.start_message_second)
        val message = buildAnnotatedString {
            val style = MaterialTheme.typography.h6.toSpanStyle().copy(color = eastBay)

            withStyle(style) {
                append(messageFirst)
            }

            append(" ")

            withStyle(style.copy(fontWeight = FontWeight.Bold)) {
                append(messageSecond)
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 40.dp),
            text = message,
            textAlign = TextAlign.Center,
            lineHeight = 31.sp
        )
        VerticalSpacer(64.dp)
        Button(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            onClick = onGetStartedButtonClicked,
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = eastBay,
                contentColor = white
            )
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = stringResource(R.string.start_get_started_button_text),
                style = MaterialTheme.typography.button
            )
        }
        VerticalSpacer(64.dp)
        Row {
            Dot(size = 8.dp, color = silver)
            HorizontalSpacer(4.dp)
            Dot(size = 8.dp, color = silver)
            HorizontalSpacer(4.dp)
            Dot(size = 8.dp, color = eastBay)
        }
        VerticalSpacer(56.dp)
    }
}