package me.partypronl.estateagent.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.partypronl.estateagent.R
import me.partypronl.estateagent.domain.homes.model.Home
import me.partypronl.estateagent.generic.composable.EASheet
import me.partypronl.estateagent.generic.composable.SheetHeader
import me.partypronl.estateagent.generic.composable.button.EAIconButton
import me.partypronl.estateagent.generic.composable.images.EAImagesPreview
import me.partypronl.estateagent.navigation.NavController
import me.partypronl.estateagent.presentation.detail.ListingDetailsArgs
import me.partypronl.estateagent.presentation.detail.ListingDetailsNavigation
import me.partypronl.estateagent.presentation.detail.ListingDetailsViewModel
import me.partypronl.estateagent.presentation.detail.model.ListingDetailsUiModel
import me.partypronl.estateagent.presentation.util.EventFlow
import me.partypronl.estateagent.util.CollectEvents
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

// TODO This needs some kind of parameter to tell the screen if it's in the playing mode or not
// (Because in that case we should do special navigation, which the view model should know of)

@Composable
fun ListingDetailsScreen(
    home: Home,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ListingDetailsViewModel = koinViewModel {
        parametersOf(ListingDetailsArgs(home))
    },
) {
    val uiModel by viewModel.uiModel.collectAsState()
    viewModel.navigation.HandleNavigation(navController)

    Content(
        uiModel = uiModel,
        onClickBack = viewModel::onBackClicked,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    uiModel: ListingDetailsUiModel,
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
            title = uiModel.address,
            subtitle = uiModel.area,
            actions = {
                EAIconButton(
                    icon = R.drawable.ic_close,
                    contentDescription = stringResource(R.string.generic_back),
                    onClick = onClickBack,
                )
            }
        )

        EAImagesPreview(
            imageUrls = uiModel.imageUrls,
            rent = uiModel.rent,
            actions = {
                EAIconButton(
                    icon = R.drawable.ic_expand,
                    contentDescription = stringResource(R.string.listing_detail_images_expand_alt),
                    onClick = {}, // TODO
                )
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
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
