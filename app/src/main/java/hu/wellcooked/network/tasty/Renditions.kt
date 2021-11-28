import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Renditions(

	@SerializedName("aspect") val aspect: String?,
	@SerializedName("name") val name: String?,
	@SerializedName("bit_rate") val bit_rate: Int,
	@SerializedName("poster_url") val poster_url: String?,
	@SerializedName("file_size") val file_size: Int,
	@SerializedName("url") val url: String?,
	@SerializedName("duration") val duration: Int,
	@SerializedName("content_type") val content_type: String?,
	@SerializedName("width") val width: Int,
	@SerializedName("minimum_bit_rate") val minimum_bit_rate: String?,
	@SerializedName("container") val container: String?,
	@SerializedName("height") val height: Int,
	@SerializedName("maximum_bit_rate") val maximum_bit_rate: String?
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(aspect)
		parcel.writeString(name)
		parcel.writeInt(bit_rate)
		parcel.writeString(poster_url)
		parcel.writeInt(file_size)
		parcel.writeString(url)
		parcel.writeInt(duration)
		parcel.writeString(content_type)
		parcel.writeInt(width)
		parcel.writeString(minimum_bit_rate)
		parcel.writeString(container)
		parcel.writeInt(height)
		parcel.writeString(maximum_bit_rate)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Renditions> {
		override fun createFromParcel(parcel: Parcel): Renditions {
			return Renditions(parcel)
		}

		override fun newArray(size: Int): Array<Renditions?> {
			return arrayOfNulls(size)
		}
	}
}