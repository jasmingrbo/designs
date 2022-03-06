package ba.grbo.jobfinder.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Suppress("SpellCheckingInspection")
internal inline fun <T> LazyListScope.lazyGrid(
    items: List<T>,
    rowSize: Int = 2,
    inBetweenElementsPadding: Dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    crossinline content: @Composable (item: T, modifier: Modifier) -> Unit,
) {
    val gridifiedItems = items.gridify(rowSize = rowSize)
    itemsIndexed(gridifiedItems) { index, items ->
        PaddingValues()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
                    top = if (index == 0) contentPadding.calculateTopPadding() else 0.dp,
                    bottom = if (index == gridifiedItems.lastIndex) contentPadding.calculateBottomPadding() else 0.dp
                )
        ) {
            items.forEachIndexed { innerIndex, gridifiedItems ->
                content(gridifiedItems, Modifier.weight(1f))
                if (innerIndex != rowSize - 1) HorizontalSpacer(inBetweenElementsPadding)
            }
            if (items.size != rowSize) Box(modifier = Modifier.weight(1f))
        }
        if (index != gridifiedItems.lastIndex) VerticalSpacer(inBetweenElementsPadding)
    }
}

@Suppress("SpellCheckingInspection")
private fun <T> List<T>.gridify(rowSize: Int = 2): List<List<T>> {
    if (rowSize < 2) throw IllegalArgumentException("rowSize has to be greater than 1")
    val result: MutableList<List<T>> = mutableListOf()
    var temp = mutableListOf<T>()

    return if (size <= rowSize) listOf(this)
    else {
        forEach { item ->
            if (temp.size < 2) temp.add(item)
            else {
                result.add(temp)
                temp = mutableListOf(item)
            }
        }
        result.also { result.add(temp) }
    }
}