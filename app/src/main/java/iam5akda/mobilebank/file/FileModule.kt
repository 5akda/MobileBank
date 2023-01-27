package iam5akda.mobilebank.file

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FileModule {

    @Reusable
    @Provides
    fun provideFileReader(
        fileReaderImpl: FileReaderImpl
    ): FileReader = fileReaderImpl
}