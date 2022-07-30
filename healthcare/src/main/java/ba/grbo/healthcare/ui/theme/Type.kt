package ba.grbo.healthcare.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ba.grbo.healthcare.R

val inter = FontFamily(Font(R.font.inter))
val averia = FontFamily(
    Font(R.font.averia_serif_libre_light),
    Font(R.font.averia_serif_libre_bold, FontWeight.Bold)
)

val Typography = Typography(
    h3 = TextStyle(
        fontFamily = averia,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)