
package pl.kpmarczynski.barcodeparser

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class BarcodeField(val label: @RawValue BarcodeLabel, private val value: String): Parcelable {
    fun getDescriptionId() = label.identifierDescriptionId
    fun getCode() = value.substring(0, label.identifierLength)
    fun getRawData() = value.substring(label.identifierLength)
    fun getFormattedData(): String {
        val rawData = getRawData()
        return label.dataFormatter?.invoke(getCode(), rawData) ?: rawData
    }
}
