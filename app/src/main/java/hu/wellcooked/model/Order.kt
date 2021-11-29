package hu.wellcooked.model

import android.os.Parcel
import android.os.Parcelable

data class Order(
    var id: String = "",
    var customer: User? = null,
    var site: Site? = null,
    var orderDate: String = "",
    var completionDate: String = "",
    var recipe: Recipe? = null,
    var status: OrderStatus? = null,
    var courier: User? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readParcelable(User::class.java.classLoader),
        parcel.readParcelable(Site::class.java.classLoader),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(Recipe::class.java.classLoader),
        OrderStatus.valueOf(parcel.readString()!!),
        parcel.readParcelable(User::class.java.classLoader),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(customer, flags)
        parcel.writeParcelable(site, flags)
        parcel.writeString(orderDate)
        parcel.writeString(completionDate)
        parcel.writeParcelable(recipe, flags)
        parcel.writeString(status?.name)
        parcel.writeParcelable(courier, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }
}