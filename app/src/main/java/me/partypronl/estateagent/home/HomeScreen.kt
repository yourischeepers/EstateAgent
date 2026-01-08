package me.partypronl.estateagent.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.partypronl.estateagent.R
import me.partypronl.estateagent.generic.composable.EARootSheet
import me.partypronl.estateagent.generic.composable.PrimarySheetHeader
import me.partypronl.estateagent.generic.composable.button.EAIconButton
import me.partypronl.estateagent.generic.composable.rememberDefaultSheetState
import me.partypronl.estateagent.presentation.home.HomeEvent
import me.partypronl.estateagent.presentation.home.HomeViewModel
import me.partypronl.estateagent.presentation.util.EventFlow
import me.partypronl.estateagent.util.CollectEvents
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val sheetState = rememberDefaultSheetState()
    viewModel.events.HandleEvents(sheetState)

    LaunchedEffect(sheetState.currentValue) {
        if (sheetState.currentValue == SheetValue.PartiallyExpanded) {
            viewModel.onMapOpened()
        }
    }

    Content(
        sheetState = sheetState,
        onClickOpenMap = viewModel::onOpenMapClicked,
        onClickCloseMap = viewModel::onCloseMapClicked,
        onClickSync = viewModel::onSyncClicked,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    sheetState: SheetState,
    onClickOpenMap: () -> Unit,
    onClickCloseMap: () -> Unit,
    onClickSync: () -> Unit,
    modifier: Modifier = Modifier,
) = EARootSheet(
    sheetState = sheetState,
    modifier = modifier,
) {
    Header(
        sheetState = sheetState,
        onClickOpenMap = onClickOpenMap,
        onClickCloseMap = onClickCloseMap,
    )

    AnimatedVisibilityNewListingsSection(
        visible = sheetState.targetValue == SheetValue.Expanded,
    )

    HomeShortcuts(
        modifier = Modifier.padding(top = 8.dp),
        onClickOpenSearches = {}, // TODO
        onClickOpenTimeToReact = {}, // TODO
        onClickOpenWaitingForReaction = {}, // TODO
        onClickOpenHistory = {} // TODO
    )

    // TODO Remove
    Button(
        onClick = onClickSync,
    ) {
        Text(text = "Sync")
    }
}

@Composable
private fun ColumnScope.AnimatedVisibilityNewListingsSection(
    visible: Boolean,
    modifier: Modifier = Modifier,
) = AnimatedVisibility(
    visible = visible,
    modifier = modifier,
) {
    HomeNewListingsSection(
        onClick = {}, // TODO
        modifier = Modifier.padding(top = 20.dp),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Header(
    sheetState: SheetState,
    onClickOpenMap: () -> Unit,
    onClickCloseMap: () -> Unit,
    modifier: Modifier = Modifier,
) = PrimarySheetHeader(
    title = stringResource(R.string.home_title),
    subtitle = stringResource(
        R.string.home_subtitle,
        4.toString(), // TODO
    ),
    leadingIcon = R.drawable.ic_robot,
    actions = {
        Crossfade(
            targetState = sheetState.targetValue == SheetValue.Expanded,
        ) { expanded ->
            if (expanded) {
                EAIconButton(
                    icon = R.drawable.ic_search_map,
                    contentDescription = stringResource(R.string.home_open_map_alt),
                    onClick = onClickOpenMap,
                )
            } else {
                EAIconButton(
                    icon = R.drawable.ic_arrow_up,
                    contentDescription = stringResource(R.string.home_close_map_alt),
                    onClick = onClickCloseMap,
                )
            }
        }

        EAIconButton(
            icon = R.drawable.ic_settings,
            contentDescription = stringResource(R.string.home_open_settings_alt),
            onClick = {}, // TODO Open settings
        )
    },
    modifier = modifier,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EventFlow<HomeEvent>.HandleEvents(
    sheetState: SheetState,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    CollectEvents {
        when (it) {
            is HomeEvent.RevealMap -> scope.launch {
                sheetState.partialExpand()
            }

            is HomeEvent.HideMap -> scope.launch {
                sheetState.expand()
            }
        }
    }
}
