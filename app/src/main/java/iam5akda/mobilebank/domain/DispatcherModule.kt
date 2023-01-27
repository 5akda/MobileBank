package iam5akda.mobilebank.domain

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Reusable
    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}