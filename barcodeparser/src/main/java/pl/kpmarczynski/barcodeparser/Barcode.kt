package pl.kpmarczynski.barcodeparser

import pl.kpmarczynski.barcodeparser.gs1.Gs1ApplicationIdentifier

data class Barcode(val fields: List<BarcodeField>) {
    operator fun plus(other: Barcode): Barcode {
        val raw1 = fields.firstOrNull { it.label == Gs1ApplicationIdentifier.GS1_RAW }?.getRawData()
        val raw2 = other.fields.firstOrNull { it.label == Gs1ApplicationIdentifier.GS1_RAW }?.getRawData()

        val malformed1 = fields.firstOrNull { it.label == Gs1ApplicationIdentifier.GS1_MALFORMED }?.getRawData()
        return Barcode(listOf(
            BarcodeField(
                Gs1ApplicationIdentifier.GS1_RAW,
                "$raw1 $raw2"
            )
        ) + if (malformed1 != null) {
            fields.filter { it.label != Gs1ApplicationIdentifier.GS1_RAW && it.label != Gs1ApplicationIdentifier.GS1_MALFORMED
            } + BarcodeField(Gs1ApplicationIdentifier.GS1_MALFORMED, "$malformed1 $raw2")
        } else {
            fields.filter {
                it.label != Gs1ApplicationIdentifier.GS1_RAW && it.label != Gs1ApplicationIdentifier.GS1_MALFORMED
            } + other.fields.filter { it.label != Gs1ApplicationIdentifier.GS1_RAW }

        })
    }
}