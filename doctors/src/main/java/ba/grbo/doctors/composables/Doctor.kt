package ba.grbo.doctors.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.doctors.Doctor
import ba.grbo.doctors.R
import ba.grbo.doctors.Specialization.HEART
import ba.grbo.doctors.ui.theme.albescentWhite
import ba.grbo.doctors.ui.theme.blueRomance
import ba.grbo.doctors.ui.theme.gorse
import ba.grbo.doctors.ui.theme.grenadier
import ba.grbo.doctors.ui.theme.silver
import ba.grbo.doctors.ui.theme.silverChalice
import kotlin.text.Typography.bullet

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Doctor(
    modifier: Modifier = Modifier,
    doctor: Doctor,
    onClicked: (Doctor) -> Unit,
    onAvailabilityButtonClicked: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        onClick = { onClicked(doctor) }
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .height(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                painter = painterResource(doctor.pictureResource),
                contentDescription = stringResource(R.string.doctors_doctor_picture),
            )
            HorizontalSpacer(16.dp)
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "dr. ${doctor.fullName}",
                    style = MaterialTheme.typography.subtitle2
                )
                VerticalSpacer(2.dp)
                Text(
                    text = "${doctor.specialization}   $bullet   ${doctor.hospital} Hospital",
                    style = MaterialTheme.typography.body2,
                    color = silverChalice
                )
                VerticalSpacer(12.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // For simplicity ignoring the rating
                    for (i in 1..5) {
                        Star()
                        HorizontalSpacer(4.dp)
                    }
                    Text(
                        text = "(${doctor.rating.raters})",
                        style = MaterialTheme.typography.caption.copy(fontSize = 10.sp),
                        color = silver
                    )
                    Box(modifier = Modifier.weight(1f))
                    Surface(
                        modifier = Modifier.height(24.dp),
                        onClick = onAvailabilityButtonClicked,
                        color = if (doctor.available) blueRomance else albescentWhite,
                        contentColor = if (doctor.available) MaterialTheme.colors.secondary else grenadier,
                        shape = MaterialTheme.shapes.small,
                        role = Role.Button
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            text = stringResource(
                                if (doctor.available) R.string.doctors_doctor_available
                                else R.string.doctors_doctor_unavailable
                            ),
                            style = MaterialTheme.typography.button,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Star() {
    Icon(
        modifier = Modifier.size(12.dp),
        painter = painterResource(R.drawable.ic_star),
        contentDescription = null,
        tint = gorse
    )
}

@Preview
@Composable
fun PreviewDoctor() {
    Preview {
        Doctor(
            doctor = Doctor(
                fullName = "Gilang Segara Bening",
                pictureResource = R.drawable.gilang_segara_bening,
                specialization = HEART,
                hospital = "Persahabatan",
                available = true,
                rating = Doctor.Rating(5.0, 1221)
            ),
            onClicked = {},
            onAvailabilityButtonClicked = {}
        )
    }
}