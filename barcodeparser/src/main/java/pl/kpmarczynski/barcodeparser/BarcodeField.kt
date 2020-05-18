
package pl.kpmarczynski.barcodeparser

data class BarcodeField(private val label: BarcodeLabel, private val value: String) {
    fun getDescriptionId() = label.identifierDescriptionId
    fun getCode() = value.substring(0, label.identifierLength)
    fun getRawData() = value.substring(label.identifierLength)
    fun getFormattedData(): String {
        val rawData = getRawData()
        return label.dataFormatter?.invoke(getCode(), rawData) ?: rawData
    }
}
