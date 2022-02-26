package ba.grbo.doctors.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.doctors.R
import ba.grbo.doctors.ui.theme.coral
import ba.grbo.doctors.ui.theme.dodgerBlue
import ba.grbo.doctors.ui.theme.heliotrope
import ba.grbo.doctors.ui.theme.razzleDazzleRose
import ba.grbo.doctors.ui.theme.robinsEggBlue
import ba.grbo.doctors.ui.theme.seaBuckthorn
import ba.grbo.doctors.ui.theme.sunsetOrange
import ba.grbo.doctors.ui.theme.white

@Composable
fun Categories(
    modifier: Modifier = Modifier,
    onConsultationButtonClicked: () -> Unit,
    onDentalButtonClicked: () -> Unit,
    onHeartButtonClicked: () -> Unit,
    onHospitalsButtonClicked: () -> Unit,
    onMedicinesButtonClicked: () -> Unit,
    onPhysicianButtonClicked: () -> Unit,
    onSkinButtonClicked: () -> Unit,
    onSurgeonButtonClicked: () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(start = 19.dp, end = 24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Category(
                boxColor = MaterialTheme.colors.primary,
                iconResource = R.drawable.ic_stethoscope,
                textResource = R.string.doctors_consultation,
                onClicked = onConsultationButtonClicked
            )
            HorizontalSpacer(27.dp)
            Category(
                boxColor = heliotrope,
                iconResource = R.drawable.ic_tooth,
                textResource = R.string.doctors_dental,
                onClicked = onDentalButtonClicked
            )
            HorizontalSpacer(32.dp)
            Category(
                boxColor = coral,
                iconResource = R.drawable.ic_heart,
                textResource = R.string.doctors_heart,
                onClicked = onHeartButtonClicked
            )
            HorizontalSpacer(32.dp)
            Category(
                boxColor = seaBuckthorn,
                iconResource = R.drawable.ic_clinic,
                textResource = R.string.doctors_hospitals,
                onClicked = onHospitalsButtonClicked
            )
        }
        VerticalSpacer(12.dp)
        Row(
            modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Category(
                boxColor = MaterialTheme.colors.secondary,
                iconResource = R.drawable.ic_medicine,
                textResource = R.string.doctors_medicines,
                onClicked = onMedicinesButtonClicked
            )
            Category(
                boxColor = robinsEggBlue,
                iconResource = R.drawable.ic_care,
                textResource = R.string.doctors_physician,
                onClicked = onPhysicianButtonClicked
            )
            Category(
                boxColor = razzleDazzleRose,
                iconResource = R.drawable.ic_bandage,
                textResource = R.string.doctors_skin,
                onClicked = onSkinButtonClicked
            )
            Category(
                boxColor = sunsetOrange,
                iconResource = R.drawable.ic_syringe,
                textResource = R.string.doctors_surgeon,
                onClicked = onSurgeonButtonClicked
            )
        }
    }
}

@Composable
private fun Category(
    modifier: Modifier = Modifier,
    boxColor: Color,
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
            onClick = onClicked
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(iconResource),
                contentDescription = null
            )
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
            iconResource = R.drawable.ic_stethoscope,
            textResource = R.string.doctors_consultation,
            onClicked = {}
        )
    }
}