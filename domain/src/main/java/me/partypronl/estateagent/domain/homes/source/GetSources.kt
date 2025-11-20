package me.partypronl.estateagent.domain.homes.source

import me.partypronl.estateagent.domain.homes.source.model.ListingsSource
import org.koin.core.annotation.Factory
import org.koin.core.component.KoinComponent

@Factory
class GetSources : KoinComponent {

    operator fun invoke(): List<ListingsSource> {
        // TODO consider enabled state
        return getKoin().getAll()
    }
}
