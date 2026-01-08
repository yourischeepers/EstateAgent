package me.partypronl.estateagent.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.partypronl.estateagent.R
import me.partypronl.estateagent.domain.homes.model.Home
import me.partypronl.estateagent.generic.composable.EASheet
import me.partypronl.estateagent.generic.composable.SheetHeader
import me.partypronl.estateagent.generic.composable.button.EAIconButton
import me.partypronl.estateagent.navigation.NavController
import me.partypronl.estateagent.presentation.detail.ListingDetailsArgs
import me.partypronl.estateagent.presentation.detail.ListingDetailsNavigation
import me.partypronl.estateagent.presentation.detail.ListingDetailsViewModel
import me.partypronl.estateagent.presentation.util.EventFlow
import me.partypronl.estateagent.util.CollectEvents
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

// TODO This needs some kind of parameter to tell the screen if it's in the playing mode or not

@Composable
fun ListingDetailsScreen(
    home: Home,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ListingDetailsViewModel = koinViewModel {
        parametersOf(ListingDetailsArgs(home))
    },
) {
    // TODO UI model
    viewModel.navigation.HandleNavigation(navController)

    Content(
        onClickBack = viewModel::onBackClicked,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) = EASheet(
    modifier = modifier.padding(top = 160.dp),
) { topPadding ->
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = topPadding + 4.dp),
    ) {
        SheetHeader(
            title = "Address 1, 1234AB",
            subtitle = "Area",
            actions = {
                EAIconButton(
                    icon = R.drawable.ic_close,
                    contentDescription = stringResource(R.string.generic_back),
                    onClick = onClickBack,
                )
            }
        )
    }
}

@Composable
private fun EventFlow<ListingDetailsNavigation>.HandleNavigation(navController: NavController) {
    CollectEvents {
        when (it) {
            is ListingDetailsNavigation.GoBack -> navController.popBackStack()
        }
    }
}
