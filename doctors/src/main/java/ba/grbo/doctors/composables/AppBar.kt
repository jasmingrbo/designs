package ba.grbo.doctors.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.doctors.R

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    onMenuButtonClicked: () -> Unit,
    onUserButtonClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuButtonClicked) {
            Icon(
                painter = painterResource(R.drawable.ic_menu_burger),
                contentDescription = stringResource(R.string.menu)
            )
        }
        Box(modifier = Modifier.weight(1f))
        IconButton(onClick = onUserButtonClicked) {
            Image(
                modifier = Modifier.size(36.dp),
                painter = painterResource(R.drawable.user),
                contentDescription = stringResource(R.string.user)
            )
        }
    }
}

@Preview
@Composable
fun PreviewAppBar() {
    Preview {
        AppBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            onMenuButtonClicked = {},
            onUserButtonClicked = {}
        )
    }
}