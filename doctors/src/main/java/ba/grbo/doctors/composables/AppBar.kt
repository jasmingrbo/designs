package ba.grbo.doctors.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable RowScope.() -> Unit,
    trailingIcon: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon()
        Box(modifier = Modifier.weight(1f))
        trailingIcon()
    }
}