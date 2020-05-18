package pl.kpmarczynski.barcodeparser.gs1

import pl.kpmarczynski.barcodeparser.Barcode
import pl.kpmarczynski.barcodeparser.BarcodeField


class Gs1Parser {
    fun parseBarcode(barcode: String) = Barcode(parseBarcode(barcode, emptyList()))

    private tailrec fun parseBarcode(
        barcodeToParse: String,
        result: List<BarcodeField>
    ): List<BarcodeField> =
        when {
            barcodeToParse.isEmpty() -> listOf(getFormattedBarcode(result)) + result

            barcodeToParse.startsWith("]") -> parseBarcode(barcodeToParse.substring(3), result)

            barcodeToParse.startsWith(GROUP_SEPARATOR) -> parseBarcode(
                barcodeToParse.substring(1),
                result
            )

            else -> when (val gs1Code = Gs1ApplicationIdentifier.getGs1Code(barcodeToParse)) {
                is Gs1ApplicationIdentifier -> {
                    val (newBarcodeToParse, barcodeField) = extractGs1Code(barcodeToParse, gs1Code)
                    parseBarcode(newBarcodeToParse, result + barcodeField)
                }
                else -> parseBarcode(
                    "",
                    result + BarcodeField(Gs1ApplicationIdentifier.GS1_MALFORMED, barcodeToParse)
                )
            }
        }

    private fun getFormattedBarcode(fields: List<BarcodeField>) =
        BarcodeField(Gs1ApplicationIdentifier.GS1_RAW, fields.joinToString(" ") {
            "(${it.getCode()}) ${it.getRawData()}"
        })

    private fun extractGs1Code(
        barcodeToParse: String,
        gs1Code: Gs1ApplicationIdentifier
    ): Pair<String, BarcodeField> {
        val dataEndIndex = getDataEndIndex(barcodeToParse, gs1Code)
        val data = barcodeToParse.substring(0, dataEndIndex)
            .replace(GROUP_SEPARATOR, "")
        return Pair(
            barcodeToParse.substring(dataEndIndex),
            BarcodeField(gs1Code, data)
        )
    }

    private fun getDataEndIndex(
        barcodeToParse: String,
        gs1Code: Gs1ApplicationIdentifier
    ): Int {
        val dataEndIndex = when {
            gs1Code.maxDataLength + gs1Code.identifierLength > barcodeToParse.length -> barcodeToParse.length
            else -> gs1Code.maxDataLength + gs1Code.identifierLength
        }

        val groupSeparatorIndex = barcodeToParse.indexOf(GROUP_SEPARATOR)

        return if (groupSeparatorIndex in 0..dataEndIndex) {
            groupSeparatorIndex + 1
        } else dataEndIndex
    }

    companion object {
        private const val GROUP_SEPARATOR = 29.toChar().toString()
    }
}