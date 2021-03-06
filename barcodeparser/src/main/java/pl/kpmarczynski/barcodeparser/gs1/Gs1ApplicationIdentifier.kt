package pl.kpmarczynski.barcodeparser.gs1

import android.annotation.SuppressLint
import pl.kpmarczynski.barcodeparser.BarcodeLabel
import pl.kpmarczynski.barcodeparser.R
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import java.text.ParseException

enum class Gs1ApplicationIdentifier(
    override val baseIdentifierCode: String,
    override val identifierDescriptionId: Int,
    override val identifierLength: Int,
    override val minDataLength: Int,
    override val maxDataLength: Int,
    override val dataFormatter: ((String, String) -> String)? = null
) : BarcodeLabel {
    GS1_NAME("", R.string.product_name, 0, 0, 0),
    GS1_RAW("", R.string.parsed_code, 0, 0, 0),
    GS1_MALFORMED("", R.string.malformed_barcode, 0, 0, 0),
    GS100("00", R.string.GS1_00, 2, 18, 18),  
    GS101("01", R.string.GS1_01, 2, 14, 14),  
    GS102("02", R.string.GS1_02, 2, 14, 14),  
    GS110("10", R.string.GS1_10, 2, 1, 20),  
    GS111("11", R.string.GS1_11, 2, 6, 6, { izCode, data -> dateFormatter(izCode, data) }),  
    GS112("12", R.string.GS1_12, 2, 6, 6, { izCode, data -> dateFormatter(izCode, data) }),  
    GS113("13", R.string.GS1_13, 2, 6, 6, { izCode, data -> dateFormatter(izCode, data) }),  
    GS115("15", R.string.GS1_15, 2, 6, 6, { izCode, data -> dateFormatter(izCode, data) }),  
    GS116("16", R.string.GS1_16, 2, 6, 6, { izCode, data -> dateFormatter(izCode, data) }),  
    GS117("17", R.string.GS1_17, 2, 6, 6, { izCode, data -> dateFormatter(izCode, data) }),  
    GS120("20", R.string.GS1_20, 2, 2, 2),  
    GS121("21", R.string.GS1_21, 2, 1, 20),  
    GS122("22", R.string.GS1_22, 2, 1, 29),
    GS1_23N("23", R.string.GS1_23N, 3, 1, 19),
    GS1240("240", R.string.GS1_240, 3, 1, 30),  
    GS1241("241", R.string.GS1_241, 3, 1, 30),  
    GS1242("242", R.string.GS1_242, 3, 1, 6),  
    GS1243("243", R.string.GS1_243, 3, 1, 20),  
    GS1250("250", R.string.GS1_250, 3, 1, 30),  
    GS1251("251", R.string.GS1_251, 3, 1, 30),  
    GS1253("253", R.string.GS1_253, 3, 13, 17),  
    GS1254("254", R.string.GS1_254, 3, 1, 20),  
    GS1255("255", R.string.GS1_255, 3, 13, 25),  
    GS130("30", R.string.GS1_30, 2, 1, 8), 
    GS1_310Y("310", R.string.GS1_310Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_311Y("311", R.string.GS1_311Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_312Y("312", R.string.GS1_312Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_313Y("313", R.string.GS1_313Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_314Y("314", R.string.GS1_314Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_315Y("315", R.string.GS1_315Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_316Y("316", R.string.GS1_316Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_320Y("320", R.string.GS1_320Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_321Y("321", R.string.GS1_321Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_322Y("322", R.string.GS1_322Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_323Y("323", R.string.GS1_323Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_324Y("324", R.string.GS1_324Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_325Y("325", R.string.GS1_325Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_326Y("326", R.string.GS1_326Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_327Y("327", R.string.GS1_327Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_328Y("328", R.string.GS1_328Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_329Y("329", R.string.GS1_329Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_330Y("330", R.string.GS1_330Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_331Y("331", R.string.GS1_331Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_332Y("332", R.string.GS1_332Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_333Y("333", R.string.GS1_333Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_334Y("334", R.string.GS1_334Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_335Y("335", R.string.GS1_335Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_336Y("336", R.string.GS1_336Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_337Y("337", R.string.GS1_337Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_340Y("340", R.string.GS1_340Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_341Y("341", R.string.GS1_341Y, 4, 6, 6, { izCode, data -> decimalFormatter(izCode, data) }),
    GS1_342Y("342", R.string.GS1_342Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_343Y("343", R.string.GS1_343Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_344Y("344", R.string.GS1_344Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_345Y("345", R.string.GS1_345Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_346Y("346", R.string.GS1_346Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_347Y("347", R.string.GS1_347Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_348Y("348", R.string.GS1_348Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_349Y("349", R.string.GS1_349Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_350Y("350", R.string.GS1_350Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_351Y("351", R.string.GS1_351Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_352Y("352", R.string.GS1_352Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_353Y("353", R.string.GS1_353Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_354Y("354", R.string.GS1_354Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_355Y("355", R.string.GS1_355Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_356Y("356", R.string.GS1_356Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_357Y("357", R.string.GS1_357Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_360Y("360", R.string.GS1_360Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_361Y("361", R.string.GS1_361Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_362Y("362", R.string.GS1_362Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_363Y("363", R.string.GS1_363Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_364Y("364", R.string.GS1_364Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_365Y("365", R.string.GS1_365Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_366Y("366", R.string.GS1_366Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_367Y("367", R.string.GS1_367Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_368Y("368", R.string.GS1_368Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_369Y("369", R.string.GS1_369Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS137("37", R.string.GS1_37, 2, 1, 8, { izCode, data ->  intFormatter( izCode, data ) }),
    GS1_390Y("390", R.string.GS1_390Y, 4, 1, 15, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_391Y("391", R.string.GS1_391Y, 4, 3, 18, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_392Y("392", R.string.GS1_392Y, 4, 1, 15, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_393Y("393", R.string.GS1_393Y, 4, 3, 18, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1_394Y("394", R.string.GS1_394Y, 4, 6, 6, { izCode, data ->  decimalFormatter( izCode, data ) }),
    GS1400("400", R.string.GS1_400, 3, 1, 30),  
    GS1401("401", R.string.GS1_401, 3, 1, 30),  
    GS1402("402", R.string.GS1_402, 3, 17, 17),  
    GS1403("403", R.string.GS1_403, 3, 1, 30),  
    GS1410("410", R.string.GS1_410, 3, 13, 13),  
    GS1411("411", R.string.GS1_411, 3, 13, 13),  
    GS1412("412", R.string.GS1_412, 3, 13, 13),  
    GS1413("413", R.string.GS1_413, 3, 13, 13),  
    GS1414("414", R.string.GS1_414, 3, 13, 13),  
    GS1415("415", R.string.GS1_415, 3, 13, 13),  
    GS1416("416", R.string.GS1_416, 3, 13, 13),  
    GS1417("417", R.string.GS1_417, 3, 13, 13),  
    GS1420("420", R.string.GS1_420, 3, 1, 20),  
    GS1421("421", R.string.GS1_421, 3, 3, 15),  
    GS1422("422", R.string.GS1_422, 3, 3, 3),  
    GS1423("423", R.string.GS1_423, 3, 3, 15),  
    GS1424("424", R.string.GS1_424, 3, 3, 3),  
    GS1425("425", R.string.GS1_425, 3, 3, 3),  
    GS1426("426", R.string.GS1_426, 3, 3, 3),  
    GS1427("427", R.string.GS1_427, 3, 1, 3),  
    GS17001("7001", R.string.GS1_7001, 4, 13, 13),  
    GS17002("7002", R.string.GS1_7002, 4, 1, 30),  
    GS17003("7003", R.string.GS1_7003, 4, 10, 10),  
    GS17004("7004", R.string.GS1_7004, 4, 1, 4),  
    GS17005("7005", R.string.GS1_7005, 4, 1, 12),  
    GS17006("7006", R.string.GS1_7006, 4, 6, 6),  
    GS17007("7007", R.string.GS1_7007, 4, 6, 12),  
    GS17008("7008", R.string.GS1_7008, 4, 1, 3),  
    GS17009("7009", R.string.GS1_7009, 4, 1, 10),  
    GS17010("7010", R.string.GS1_7010, 4, 1, 4),  
    GS17020("7020", R.string.GS1_7020, 4, 1, 20),  
    GS17021("7021", R.string.GS1_7021, 4, 1, 20),  
    GS17022("7022", R.string.GS1_7022, 4, 1, 20),  
    GS17023("7023", R.string.GS1_7023, 4, 1, 30), 
    GS1_703N("703", R.string.GS1_703N, 4, 3, 30),  
    GS1710("710", R.string.GS1_710, 3, 1, 20),  
    GS1711("711", R.string.GS1_711, 3, 1, 20),  
    GS1712("712", R.string.GS1_712, 3, 1, 20),  
    GS1713("713", R.string.GS1_713, 3, 1, 20),  
    GS1714("714", R.string.GS1_714, 3, 1, 20), 
    GS1_723Y("723", R.string.GS1_723Y, 4, 1, 28),  
    GS17240("7240", R.string.GS1_7240, 3, 1, 20),  
    GS18001("8001", R.string.GS1_8001, 4, 14, 14),  
    GS18002("8002", R.string.GS1_8002, 4, 1, 20),  
    GS18003("8003", R.string.GS1_8003, 4, 14, 30),  
    GS18004("8004", R.string.GS1_8004, 4, 1, 30),  
    GS18005("8005", R.string.GS1_8005, 4, 6, 6),  
    GS18006("8006", R.string.GS1_8006, 4, 18, 18),  
    GS18007("8007", R.string.GS1_8007, 4, 1, 30),  
    GS18008("8008", R.string.GS1_8008, 4, 8, 12),  
    GS18009("8009", R.string.GS1_8009, 4, 1, 50),  
    GS18010("8010", R.string.GS1_8010, 4, 1, 30),  
    GS18011("8011", R.string.GS1_8011, 4, 1, 12),  
    GS18012("8012", R.string.GS1_8012, 4, 1, 20),  
    GS18013("8013", R.string.GS1_8013, 4, 1, 30),  
    GS18017("8017", R.string.GS1_8017, 4, 18, 18),  
    GS18018("8018", R.string.GS1_8018, 4, 18, 18),  
    GS18019("8019", R.string.GS1_8019, 4, 1, 10),  
    GS18020("8020", R.string.GS1_8020, 4, 1, 25),  
    GS18100("8100", R.string.GS1_8100, 4, 6, 6),  
    GS18101("8101", R.string.GS1_8101, 4, 10, 10),  
    GS18102("8102", R.string.GS1_8102, 4, 2, 2),  
    GS18110("8110", R.string.GS1_8110, 4, 1, 30),  
    GS18111("8111", R.string.GS1_8111, 4, 1, 8),  
    GS18112("8112", R.string.GS1_8112, 4, 1, 70),  
    GS18200("8200", R.string.GS1_8200, 4, 1, 70),  
    GS190("90", R.string.GS1_90, 4, 1, 30),  
    GS191("91", R.string.GS1_91, 2, 1, 90),  
    GS192("92", R.string.GS1_92, 2, 1, 90),  
    GS193("93", R.string.GS1_93, 2, 1, 90),  
    GS194("94", R.string.GS1_94, 2, 1, 90),  
    GS195("95", R.string.GS1_95, 2, 1, 90),  
    GS196("96", R.string.GS1_96, 2, 1, 90),  
    GS197("97", R.string.GS1_97, 2, 1, 90),  
    GS198("98", R.string.GS1_98, 2, 1, 90),  
    GS199("99", R.string.GS1_99, 2, 1, 90);

    companion object {
        @SuppressLint("SimpleDateFormat")
        private fun dateFormatter(izCode: String, data: String): String = try {
            SimpleDateFormat("dd MMM yyyy").format(
                SimpleDateFormat("yyMMdd", Locale.ROOT).parse(
                    data
                )!!
            )
        } catch (ex: ParseException) {
            data
        }
        
        private fun decimalFormatter(izCode: String, data: String): String =
            BigDecimal(data).movePointLeft(
                izCode.substring(izCode.length - 1, izCode.length).toInt()
            ).toPlainString() 
        
        private fun intFormatter(izCode: String, data: String): String =
                BigDecimal(data).toPlainString()

        fun getGs1Code(barcode: String): Gs1ApplicationIdentifier? =
            values().firstOrNull { elem -> elem.baseIdentifierCode.isNotBlank() && barcode.startsWith(elem.baseIdentifierCode) }
    }
}