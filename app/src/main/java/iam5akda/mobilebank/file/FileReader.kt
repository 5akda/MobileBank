package iam5akda.mobilebank.file

interface FileReader {
    fun loadRawData(fileName: String): String
}