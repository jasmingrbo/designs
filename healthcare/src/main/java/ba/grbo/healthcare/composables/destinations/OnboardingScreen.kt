package ba.grbo.healthcare.composables.destinations

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.healthcare.OnboardingScreenData
import ba.grbo.healthcare.R
import ba.grbo.healthcare.composables.VerticalSpacer
import ba.grbo.healthcare.ui.theme.royalBlue

@Composable
fun OnboardingScreen(
    data: OnboardingScreenData,
    spacer: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val height = ButtonDefaults.MinHeight +
                ButtonDefaults.TextButtonContentPadding.calculateTopPadding() +
                ButtonDefaults.TextButtonContentPadding.calculateBottomPadding()
        VerticalSpacer(
            height = if (spacer) height else height / 2
        )

        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(data.imageResource),
            contentDescription = null
        )

        VerticalSpacer(40.dp)

        Title(txtResource = data.titleTextResource)

        VerticalSpacer(12.dp)

        Text(
            modifier = Modifier.padding(horizontal = 36.dp),
            text = stringResource(data.descriptionTextResource),
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
    @StringRes txtResource: Int
) {
    val words = stringResource(txtResource).split('\n')
    val text = buildAnnotatedString {
        withStyle(
            MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold).toSpanStyle()
        ) { append("${words.first()}\n") }
        withStyle(MaterialTheme.typography.h3.toSpanStyle()) {
            for (i in 1..words.lastIndex) append(words[i])
        }
    }

    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        color = Color.White
    )
}

@Preview
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(
        data = OnboardingScreenData(
            backgroundColor = royalBlue,
            imageResource = R.drawable.prescription,
            titleTextResource = R.string.onboarding_consultation_title,
            descriptionTextResource = R.string.onboarding_consultation_description
        ),
        spacer = true
    )
}