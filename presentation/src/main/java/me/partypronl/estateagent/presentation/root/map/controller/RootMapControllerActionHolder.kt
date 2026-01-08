package me.partypronl.estateagent.presentation.root.map.controller

import me.partypronl.estateagent.presentation.util.MutableEventFlow
import me.partypronl.estateagent.presentation.util.asEventFlow
import me.partypronl.estateagent.presentation.util.emitEvent
import org.koin.core.annotation.Single

@Single
class RootMapControllerActionHolder {

    private val _actions = MutableEventFlow<RootMapAction>()
    val actions = _actions.asEventFlow()

    fun sendAction(action: RootMapAction) {
        _actions.emitEvent(action)
    }
}
