package ba.grbo.jobfinder.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ba.grbo.jobfinder.R

@Composable
fun EmployerLogo(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    logoPadding: PaddingValues = PaddingValues(4.dp),
    @DrawableRes logoResource: Int
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = backgroundColor
    ) {
        Image(
            modifier = Modifier.padding(logoPadding),
            painter = painterResource(logoResource),
            contentDescription = stringResource(R.string.home_employer_logo)
        )
    }
}