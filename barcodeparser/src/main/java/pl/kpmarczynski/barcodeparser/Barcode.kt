package pl.kpmarczynski.barcodeparser

data class Barcode(val fields: List<BarcodeField>) {
    val rawBarcode: String
    init{
        rawBarcode = fields.joinToString(" ") {
            "(${it.getCode()}) ${it.getRawData()}"
        }
    }
}