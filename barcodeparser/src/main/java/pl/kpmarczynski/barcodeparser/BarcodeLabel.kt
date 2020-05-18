package pl.kpmarczynski.barcodeparser

interface BarcodeLabel {
    val baseIdentifierCode: String
    val identifierDescriptionId: Int
    val identifierLength: Int
    val minDataLength: Int
    val maxDataLength: Int
    val dataFormatter: ((String, String) -> String)?
}