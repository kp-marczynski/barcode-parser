package pl.kpmarczynski.barcodeparser

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import pl.kpmarczynski.barcodeparser.gs1.Gs1ApplicationIdentifier

class AirportTest : StringSpec() {
    companion object {
        private const val GROUP_SEPARATOR = 29.toChar().toString()
    }

    init {
        "canary test should pass" {
            true shouldBe true
        }

        "should parse different codes" {
            forall(
                row(
                    "021801759601853015200520310200864037024${GROUP_SEPARATOR}10L20018",
                    "(02) 18017596018530 (15) 200520 (3102) 008640 (37) 024 (10) L20018"
                ),
                row(
                    "0018017596018530",
                    "(00) 18017596018530"
                ),
                row(
                    "010800271042257015200520310320000010L10015",
                    "(01) 08002710422570 (15) 200520 (3103) 200000 (10) L10015"
                ),
                row(
                    "00380027107100000043021801759601853015200520310200864037024${GROUP_SEPARATOR}10L20018",
                    "(00) 380027107100000043 (02) 18017596018530 (15) 200520 (3102) 008640 (37) 024 (10) L20018"
                )
            ) { barcodeToParse, expected ->
                val result =
                    BarcodeResultParser().parseBarcode(barcodeToParse, BarcodeFormat.GS1_128)
                result shouldNotBe null
                result!!.fields.first { it.label == Gs1ApplicationIdentifier.GS1_RAW }
                    .getRawData() shouldBe expected
            }
        }

        "should parse codes with different order" {
            forall(
                row(
                    "021801759601853015200520310200864037024${GROUP_SEPARATOR}10L20018",
                    "(02) 18017596018530 (15) 200520 (3102) 008640 (37) 024 (10) L20018"
                ),
                row(
                    "37024${GROUP_SEPARATOR}10L20018${GROUP_SEPARATOR}0218017596018530152005203102008640",
                    "(37) 024 (10) L20018 (02) 18017596018530 (15) 200520 (3102) 008640"
                ),
                row(
                    "152005200218017596018530310200864037024${GROUP_SEPARATOR}10L20018",
                    "(15) 200520 (02) 18017596018530 (3102) 008640 (37) 024 (10) L20018"
                ),
                row(
                    "021801759601853015200520310200864010L20018${GROUP_SEPARATOR}37024",
                    "(02) 18017596018530 (15) 200520 (3102) 008640 (10) L20018 (37) 024"
                )
            ) { barcodeToParse, expected ->
                val result =
                    BarcodeResultParser().parseBarcode(barcodeToParse, BarcodeFormat.GS1_128)
                result shouldNotBe null
                result!!.fields.first { it.label == Gs1ApplicationIdentifier.GS1_RAW }
                    .getRawData() shouldBe expected
            }
        }

        "should detect malformed" {
            forall(
                row(
                    "02180175960185301",
                    "1"
                ),
                row(
                    "07180175960185301",
                    "07180175960185301"
                ),
                row(
                    "0038002710710000004302180175960185301520052031020086403702410L20018",
                    "018"
                )
            ) { barcodeToParse, expected ->
                val result =
                    BarcodeResultParser().parseBarcode(barcodeToParse, BarcodeFormat.GS1_128)
                result shouldNotBe null
                val malformed =
                    result!!.fields.firstOrNull { it.label == Gs1ApplicationIdentifier.GS1_MALFORMED }
                malformed shouldNotBe null
                malformed!!.getRawData() shouldBe expected
            }
        }

        "should merge barcodes" {
            forall(
                row(
                    listOf(
                        "00380027107100000043",
                        "021801759601853015200520310200864037024${GROUP_SEPARATOR}10L20018"
                    ),
                    "(00) 380027107100000043 (02) 18017596018530 (15) 200520 (3102) 008640 (37) 024 (10) L20018"
                )
            ) { barcodeToParse, expected ->
                val result =
                    BarcodeResultParser().parseBarcode(barcodeToParse, BarcodeFormat.GS1_128)
                result shouldNotBe null
                result!!.fields.first { it.label == Gs1ApplicationIdentifier.GS1_RAW }
                    .getRawData() shouldBe expected
            }
        }
    }
}