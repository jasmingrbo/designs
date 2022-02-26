package ba.grbo.doctors.composables

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import ba.grbo.doctors.ui.theme.DoctorsTheme

@Composable
fun Preview(content: @Composable () -> Unit) {
    DoctorsTheme {
        Surface {
            content()
        }
    }
}