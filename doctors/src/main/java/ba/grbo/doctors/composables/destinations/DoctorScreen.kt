package ba.grbo.doctors.composables.destinations

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.doctors.Doctor
import ba.grbo.doctors.R
import ba.grbo.doctors.composables.AppBar
import ba.grbo.doctors.composables.HorizontalSpacer
import ba.grbo.doctors.composables.VerticalSpacer
import ba.grbo.doctors.ui.theme.athensGray
import ba.grbo.doctors.ui.theme.curiousBlue
import ba.grbo.doctors.ui.theme.lato
import ba.grbo.doctors.ui.theme.silver
import ba.grbo.doctors.ui.theme.silverChalice
import ba.grbo.doctors.ui.theme.sourceSansPro
import ba.grbo.doctors.ui.theme.white

@Composable
fun DoctorScreen(
    modifier: Modifier = Modifier,
    doctor: Doctor,
    onBackButtonClicked: () -> Unit,
    onBookmarkButtonClicked: () -> Unit,
    onMessageButtonClicked: (Int) -> Unit,
    onMakeAppointmentButtonClicked: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(doctor.pictureResource),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                alignment = BiasAlignment(0f, -0.4f)
            )
            AppBar(
                modifier = Modifier.padding(start = 12.dp, end = 18.dp, top = 24.dp),
                leadingIcon = {
                    IconButton(onClick = onBackButtonClicked) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.doctor_back_arrow)
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = onBookmarkButtonClicked) {
                        Icon(
                            painter = painterResource(R.drawable.ic_artboard),
                            contentDescription = stringResource(R.string.doctor_bookmark)
                        )
                    }
                }
            )
        }
        VerticalSpacer(24.dp)
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = "dr. ${doctor.fullName}",
            style = MaterialTheme.typography.h5
        )
        VerticalSpacer(8.dp)
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = "${doctor.specialization}   ${Typography.bullet}   ${doctor.hospital} Hospital",
            style = MaterialTheme.typography.body2.copy(fontFamily = lato),
            color = silverChalice
        )
        VerticalSpacer(20.dp)
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = doctor.description,
            style = MaterialTheme.typography.body1,
            color = silver
        )
        VerticalSpacer(20.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .padding(horizontal = 32.dp)
        ) {
            Statistics(
                modifier = Modifier.weight(1f),
                statistic = doctor.experience.toString(),
                titleResource = R.string.doctor_experience_label,
                abbreviationResource = R.string.doctor_years_abbreviation_label
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Statistics(
                modifier = Modifier.weight(1f),
                statistic = doctor.patients.toString(),
                titleResource = R.string.doctor_patients_label,
                abbreviationResource = R.string.doctor_patients_abbreviation_label
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Statistics(
                modifier = Modifier.weight(1f),
                statistic = doctor.rating.toString(),
                titleResource = R.string.doctor_rating_label
            )
        }
        VerticalSpacer(20.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Button(
                modifier = Modifier.size(56.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = white
                ),
                onClick = { onMessageButtonClicked(doctor.id) }
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.ic_comment),
                    contentDescription = null
                )
            }
            HorizontalSpacer(16.dp)
            Button(
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = white
                ),
                onClick = { onMakeAppointmentButtonClicked(doctor.id) }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(R.string.doctor_make_appointment_button),
                    style = MaterialTheme.typography.button,
                    color = athensGray
                )
            }
        }
        VerticalSpacer(24.dp)
    }
}

@Composable
private fun Statistics(
    modifier: Modifier = Modifier,
    statistic: String,
    @StringRes titleResource: Int,
    @StringRes abbreviationResource: Int? = null,
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(titleResource),
            style = MaterialTheme.typography.body1
        )

        val text = buildAnnotatedString {
            withStyle(
                MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Normal,
                    fontFamily = sourceSansPro,
                    color = curiousBlue
                ).toSpanStyle()
            ) {
                append(statistic)
            }

            append(" ")

            abbreviationResource?.let {
                withStyle(
                    MaterialTheme.typography.body2.copy(
                        lineHeight = 36.sp,
                        color = silverChalice
                    ).toSpanStyle()
                ) {
                    append(stringResource(abbreviationResource))
                }
            }
        }
        Text(text = text)
    }
}