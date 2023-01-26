package iam5akda.mobilebank.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import iam5akda.mobilebank.file.FileReader
import iam5akda.mobilebank.file.FileReaderImpl

@Module
@InstallIn(SingletonComponent::class)
object FileModule {

    @Reusable
    @Provides
    fun provideFileReader(
        fileReaderImpl: FileReaderImpl
    ): FileReader = fileReaderImpl
}