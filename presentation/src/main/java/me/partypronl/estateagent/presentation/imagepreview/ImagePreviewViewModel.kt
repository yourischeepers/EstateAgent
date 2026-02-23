package me.partypronl.estateagent.presentation.imagepreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.partypronl.estateagent.presentation.imagepreview.model.ImagePreviewUiMapper
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class ImagePreviewViewModel(
    @InjectedParam private val args: ImagePreviewArgs,
    private val mapper: ImagePreviewUiMapper,
) : ViewModel() {

    companion object {

        private const val AutoPlayTime = 5_000L
    }

    private var autoPlaying = true // TODO Change default to false when settings are implemented
    private var currentImageIndex = 0

    private val _uiModel = MutableStateFlow(
        value = mapper.toUiModel(
            imageUrls = args.imageUrls,
            isAutoPlaying = autoPlaying,
            currentImageIndex = currentImageIndex,
        )
    )
    val uiModel by lazy {
        checkAndStartAutoPlay()
        _uiModel.asStateFlow()
    }

    fun onNextClicked() {
        autoPlaying = false
        currentImageIndex++
        if (currentImageIndex >= args.imageUrls.size) currentImageIndex = 0
        updateUiModel(isManualChange = true)
    }

    fun onPreviousClicked() {
        autoPlaying = false
        currentImageIndex--
        if (currentImageIndex < 0) currentImageIndex = args.imageUrls.size - 1
        updateUiModel(isManualChange = true)
    }

    private fun checkAndStartAutoPlay() {
        // TODO Check if auto play is enabled

        viewModelScope.launch {
            delay(AutoPlayTime)

            while (autoPlaying) {
                currentImageIndex++
                if (currentImageIndex >= args.imageUrls.size) currentImageIndex = 0
                updateUiModel()
                delay(AutoPlayTime)
            }
        }
    }

    private fun updateUiModel(
        isManualChange: Boolean = false,
    ) {
        _uiModel.value = mapper.toUiModel(
            imageUrls = args.imageUrls,
            isAutoPlaying = !isManualChange && autoPlaying,
            currentImageIndex = currentImageIndex,
        )
    }
}
