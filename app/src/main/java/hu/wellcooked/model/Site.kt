package hu.wellcooked.model

import android.os.Parcel
import android.os.Parcelable

data class Site(
    var id: String = "",
    var longitude: Double = 69.0,
    var latitude: Double = 69.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeDouble(longitude)
        parcel.writeDouble(latitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Site> {
        override fun createFromParcel(parcel: Parcel): Site {
            return Site(parcel)
        }

        override fun newArray(size: Int): Array<Site?> {
            return arrayOfNulls(size)
        }
    }
}
