package iam5akda.mobilebank.view.file

interface FileReader {
    fun loadRawData(fileName: String): String
}