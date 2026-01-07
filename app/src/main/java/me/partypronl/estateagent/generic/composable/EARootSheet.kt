package me.partypronl.estateagent.generic.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.partypronl.estateagent.root.AgentStatusBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EARootSheet(
    modifier: Modifier = Modifier,
    sheetPeekHeight: Dp = 160.dp,
    sheetState: SheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Expanded,
        skipHiddenState = true,
    ),
    content: @Composable ColumnScope.() -> Unit,
) = EASheet(
    modifier = modifier,
    sheetPeekHeight = sheetPeekHeight,
    sheetState = sheetState,
    sheetContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
) { dragHandleTopPadding ->
    val sheetOffsetPx by remember {
        derivedStateOf { sheetState.requireOffset() }
    }

    Column(
        modifier = Modifier
            .graphicsLayer {
                translationY = -sheetOffsetPx
            }
            .clip(
                shape = RoundedCornerShape(
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp,
                )
            )
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(top = dragHandleTopPadding + 4.dp)
            .padding(horizontal = 24.dp)
            .weight(1F),
    ) {
        Column(
            modifier = Modifier
                .graphicsLayer {
                    translationY = sheetOffsetPx
                },
        ) {
            content()
        }
    }

    AgentStatusBar(
        modifier = Modifier.graphicsLayer {
            translationY = -sheetOffsetPx
        },
    )
}
