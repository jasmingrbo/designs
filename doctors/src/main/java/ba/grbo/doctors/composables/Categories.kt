package ba.grbo.doctors.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.doctors.Category
import ba.grbo.doctors.Category.CONSULTATION
import ba.grbo.doctors.Category.DENTAL
import ba.grbo.doctors.Category.HEART
import ba.grbo.doctors.Category.HOSPITALS
import ba.grbo.doctors.Category.MEDICINES
import ba.grbo.doctors.Category.PHYSICIAN
import ba.grbo.doctors.Category.SKIN
import ba.grbo.doctors.Category.SURGEON
import ba.grbo.doctors.R
import ba.grbo.doctors.ui.theme.bitterSweet
import ba.grbo.doctors.ui.theme.brightTurquoise
import ba.grbo.doctors.ui.theme.coral
import ba.grbo.doctors.ui.theme.dodgerBlue
import ba.grbo.doctors.ui.theme.heliotrope
import ba.grbo.doctors.ui.theme.hotPink
import ba.grbo.doctors.ui.theme.malibu
import ba.grbo.doctors.ui.theme.melrose
import ba.grbo.doctors.ui.theme.mountainMeadow
import ba.grbo.doctors.ui.theme.razzleDazzleRose
import ba.grbo.doctors.ui.theme.robinsEggBlue
import ba.grbo.doctors.ui.theme.seaBuckthorn
import ba.grbo.doctors.ui.theme.sunsetOrange
import ba.grbo.doctors.ui.theme.vividTangerine
import ba.grbo.doctors.ui.theme.white
import ba.grbo.doctors.ui.theme.yellowOrange

@Composable
fun Categories(
    modifier: Modifier = Modifier,
    onCategoryButtonClicked: (Category) -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(start = 19.dp, end = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Category(
                boxColor = MaterialTheme.colors.primary,
                cornerShineColor = malibu,
                iconResource = R.drawable.ic_stethoscope,
                textResource = R.string.doctors_consultation,
                onClicked = { onCategoryButtonClicked(CONSULTATION) }
            )
            HorizontalSpacer(27.dp)
            Category(
                boxColor = heliotrope,
                cornerShineColor = melrose,
                iconResource = R.drawable.ic_tooth,
                textResource = R.string.doctors_dental,
                onClicked = { onCategoryButtonClicked(DENTAL) }
            )
            HorizontalSpacer(32.dp)
            Category(
                boxColor = coral,
                cornerShineColor = vividTangerine,
                iconResource = R.drawable.ic_heart,
                textResource = R.string.doctors_heart,
                onClicked = { onCategoryButtonClicked(HEART) }
            )
            HorizontalSpacer(32.dp)
            Category(
                boxColor = seaBuckthorn,
                cornerShineColor = yellowOrange,
                iconResource = R.drawable.ic_clinic,
                textResource = R.string.doctors_hospitals,
                onClicked = { onCategoryButtonClicked(HOSPITALS) }
            )
        }
        VerticalSpacer(12.dp)
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Category(
                boxColor = MaterialTheme.colors.secondary,
                cornerShineColor = mountainMeadow,
                iconResource = R.drawable.ic_medicine,
                textResource = R.string.doctors_medicines,
                onClicked = { onCategoryButtonClicked(MEDICINES) }
            )
            Category(
                boxColor = robinsEggBlue,
                cornerShineColor = brightTurquoise,
                iconResource = R.drawable.ic_care,
                textResource = R.string.doctors_physician,
                onClicked = { onCategoryButtonClicked(PHYSICIAN) }
            )
            Category(
                boxColor = razzleDazzleRose,
                cornerShineColor = hotPink,
                iconResource = R.drawable.ic_bandage,
                textResource = R.string.doctors_skin,
                onClicked = { onCategoryButtonClicked(SKIN) }
            )
            Category(
                boxColor = sunsetOrange,
                cornerShineColor = bitterSweet,
                iconResource = R.drawable.ic_syringe,
                textResource = R.string.doctors_surgeon,
                onClicked = { onCategoryButtonClicked(SURGEON) }
            )
        }
    }
}

@Composable
private fun Category(
    modifier: Modifier = Modifier,
    boxColor: Color,
    cornerShineColor: Color,
    @DrawableRes iconResource: Int,
    @StringRes textResource: Int,
    onClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.size(56.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(backgroundColor = boxColor, contentColor = white),
            contentPadding = PaddingValues(0.dp),
            onClick = onClicked
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Canvas(modifier = Modifier.requiredSize(20.dp)) {
                    drawCircle(
                        color = cornerShineColor,
                        center = Offset(x = 0f, y = 0f),
                        radius = size.maxDimension
                    )
                }

                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(iconResource),
                    contentDescription = null
                )
            }
        }
        VerticalSpacer(8.dp)
        Text(
            text = stringResource(textResource),
            style = MaterialTheme.typography.caption
        )
    }
}

@Preview
@Composable
fun PreviewCategory() {
    Preview {
        Category(
            boxColor = dodgerBlue,
            cornerShineColor = malibu,
            iconResource = R.drawable.ic_stethoscope,
            textResource = R.string.doctors_consultation,
            onClicked = {}
        )
    }
}

@Preview
@Composable
fun PreviewCategories() {
    Preview {
        Categories(onCategoryButtonClicked = {})
    }
}