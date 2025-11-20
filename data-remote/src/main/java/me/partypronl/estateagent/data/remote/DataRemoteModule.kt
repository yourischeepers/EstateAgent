package me.partypronl.estateagent.data.remote

import me.partypronl.estateagent.data.remote.client.HttpClientCreator
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("me.partypronl.estateagent.data.remote")
class DataRemoteModule {

    // TODO potentially later split into multiple clients

    @Single
    fun provideHttpClient(creator: HttpClientCreator) = creator.createHttpClient()
}
