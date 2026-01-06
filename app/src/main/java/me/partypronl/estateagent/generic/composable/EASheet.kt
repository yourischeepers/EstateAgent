package me.partypronl.estateagent.generic.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EASheet(
    modifier: Modifier = Modifier,
    sheetPeekHeight: Dp = 160.dp,
    sheetState: SheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Expanded,
        skipHiddenState = true,
    ),
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState),
    sheetContainerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    content: @Composable () -> Unit,
) = BottomSheetScaffold(
    scaffoldState = scaffoldState,
    sheetContent = {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 4.dp)
                .padding(horizontal = 24.dp)
        ) {
            content()
        }
    },
    sheetPeekHeight = sheetPeekHeight,
    containerColor = Color.Transparent,
    sheetContainerColor = sheetContainerColor,
    sheetShape = RoundedCornerShape(36.dp),
    sheetDragHandle = { DragHandle() },
    modifier = modifier.statusBarsPadding(),
) {
    // Empty on purpose, since we want to see the map underneath
    // Map isn't placed here because we want it to be persistent
}

@Composable
private fun DragHandle(
    modifier: Modifier = Modifier
) = Surface(
    modifier = modifier.padding(vertical = 8.dp),
    color = MaterialTheme.colorScheme.surfaceContainerHigh,
    shape = CircleShape,
) {
    Box(
        modifier = Modifier.size(
            width = 76.dp,
            height = 4.dp,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDefaultSheetState(): SheetState {
    return rememberStandardBottomSheetState(
        initialValue = SheetValue.Expanded,
        skipHiddenState = true,
    )
}
