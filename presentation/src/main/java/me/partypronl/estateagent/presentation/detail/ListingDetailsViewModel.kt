package me.partypronl.estateagent.presentation.detail

import androidx.lifecycle.ViewModel
import me.partypronl.estateagent.presentation.util.MutableEventFlow
import me.partypronl.estateagent.presentation.util.asEventFlow
import me.partypronl.estateagent.presentation.util.emitEvent
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class ListingDetailsViewModel(
    @InjectedParam private val args: ListingDetailsArgs,
) : ViewModel() {

    private val _navigation = MutableEventFlow<ListingDetailsNavigation>()
    val navigation = _navigation.asEventFlow()

    fun onBackClicked() {
        _navigation.emitEvent(ListingDetailsNavigation.GoBack)
    }
}
