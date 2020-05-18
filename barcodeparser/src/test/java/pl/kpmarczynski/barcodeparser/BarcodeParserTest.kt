package pl.kpmarczynski.barcodeparser

import org.junit.Test

import org.junit.Assert.*

class BarcodeParserTest {
    @Test
    fun parsingIsCorrect() {
        //given
        val barcodeToParse = "021801759601853015200520310200864037024${GROUP_SEPARATOR}10L20018"
        val expected = "(02) 18017596018530 (15) 200520 (3102) 008640 (37) 024 (10) L20018"
        //when
        val result = BarcodeResultParser().parseBarcode(barcodeToParse, BarcodeFormat.GS1_128)
        //then
        assertEquals(expected, result!!.fields[0].getRawData() )
    }
    companion object {
        private const val GROUP_SEPARATOR = 29.toChar().toString()
    }
}
