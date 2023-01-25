package iam5akda.mobilebank.view.file

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FileReaderImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : FileReader {

    override fun loadRawData(fileName: String): String {
        val reader = context.assets
            .open(fileName)
            .bufferedReader()
        return reader.use { it.readText() }
    }
}