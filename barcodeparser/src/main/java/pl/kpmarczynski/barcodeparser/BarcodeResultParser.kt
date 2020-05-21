package pl.kpmarczynski.barcodeparser

import pl.kpmarczynski.barcodeparser.gs1.Gs1Parser


class BarcodeResultParser {

    fun parseBarcode(barcode: String, format: BarcodeFormat): Barcode? = when(format){
        BarcodeFormat.GS1_128 -> Gs1Parser().parseBarcode(barcode)
    }

    fun parseBarcodes(barcodes: List<String>, format: BarcodeFormat): Barcode? = when(format){
        BarcodeFormat.GS1_128 -> barcodes.map {Gs1Parser().parseBarcode(it)}.reduce{acc, elem -> acc.merge(elem) }
    }

}