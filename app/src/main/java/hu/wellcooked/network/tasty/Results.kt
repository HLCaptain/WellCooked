import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Results(

	@SerializedName("brand") val brand: Brand?,
	@SerializedName("show_id") val show_id: Int,
	@SerializedName("is_shoppable") val is_shoppable: Boolean,
	@SerializedName("topics") val topics: ArrayList<Topics>?,
	@SerializedName("canonical_id") val canonical_id: String?,
	@SerializedName("country") val country: String?,
	@SerializedName("user_ratings") val user_ratings: User_ratings?,
	@SerializedName("servings_noun_singular") val servings_noun_singular: String?,
	@SerializedName("tips_and_ratings_enabled") val tips_and_ratings_enabled: Boolean,
	@SerializedName("aspect_ratio") val aspect_ratio: String?,
	@SerializedName("servings_noun_plural") val servings_noun_plural: String?,
	@SerializedName("seo_title") val seo_title: String?,
	@SerializedName("inspired_by_url") val inspired_by_url: String?,
	@SerializedName("updated_at") val updated_at: Int,
	@SerializedName("cook_time_minutes") val cook_time_minutes: String?,
	@SerializedName("promotion") val promotion: String?,
	@SerializedName("id") val id: Int,
	@SerializedName("sections") val sections: ArrayList<Sections>?,
	@SerializedName("show") val show: Show?,
	@SerializedName("total_time_minutes") val total_time_minutes: String?,
	@SerializedName("yields") val yields: String?,
	@SerializedName("facebook_posts") val facebook_posts: ArrayList<String>?,
	@SerializedName("brand_id") val brand_id: Int,
	@SerializedName("num_servings") val num_servings: Int,
	@SerializedName("buzz_id") val buzz_id: String?,
	@SerializedName("approved_at") val approved_at: Int,
	@SerializedName("beauty_url") val beauty_url: String?,
	@SerializedName("original_video_url") val original_video_url: String?,
	@SerializedName("video_id") val video_id: Int,
	@SerializedName("nutrition_visibility") val nutrition_visibility: String?,
	@SerializedName("is_one_top") val is_one_top: Boolean,
	@SerializedName("renditions") val renditions: ArrayList<Renditions>?,
	@SerializedName("total_time_tier") val total_time_tier: Total_time_tier?,
	@SerializedName("instructions") val instructions: ArrayList<Instructions>?,
	@SerializedName("keywords") val keywordswords: String?,
	@SerializedName("language") val language: String?,
	@SerializedName("slug") val slug: String?,
	@SerializedName("prep_time_minutes") val prep_time_minutes: String?,
	@SerializedName("compilations") val compilations: ArrayList<String>?,
	@SerializedName("thumbnail_url") val thumbnail_url: String?,
	@SerializedName("thumbnail_alt_text") val thumbnail_alt_text: String?,
	@SerializedName("video_ad_content") val video_ad_content: String?,
	@SerializedName("tags") val tags: ArrayList<Tags>?,
	@SerializedName("nutrition") val nutrition: Nutrition?,
	@SerializedName("name") val name: String?,
	@SerializedName("created_at") val created_at: Int,
	@SerializedName("description") val description: String?,
	@SerializedName("draft_status") val draft_status: String?,
	@SerializedName("video_url") val video_url: String?,
	@SerializedName("credits") val credits: ArrayList<Credits>?
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readParcelable(Brand::class.java.classLoader),
		parcel.readInt(),
		parcel.readByte() != 0.toByte(),
		parcel.createTypedArrayList(Topics),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(User_ratings::class.java.classLoader),
		parcel.readString(),
		parcel.readByte() != 0.toByte(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.createTypedArrayList(Sections),
		parcel.readParcelable(Show::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.createStringArrayList(),
		parcel.readInt(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readByte() != 0.toByte(),
		parcel.createTypedArrayList(Renditions),
		parcel.readParcelable(Total_time_tier::class.java.classLoader),
		parcel.createTypedArrayList(Instructions),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.createStringArrayList(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.createTypedArrayList(Tags),
		parcel.readParcelable(Nutrition::class.java.classLoader),
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.createTypedArrayList(Credits)
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeParcelable(brand, flags)
		parcel.writeInt(show_id)
		parcel.writeByte(if (is_shoppable) 1 else 0)
		parcel.writeTypedList(topics)
		parcel.writeString(canonical_id)
		parcel.writeString(country)
		parcel.writeParcelable(user_ratings, flags)
		parcel.writeString(servings_noun_singular)
		parcel.writeByte(if (tips_and_ratings_enabled) 1 else 0)
		parcel.writeString(aspect_ratio)
		parcel.writeString(servings_noun_plural)
		parcel.writeString(seo_title)
		parcel.writeString(inspired_by_url)
		parcel.writeInt(updated_at)
		parcel.writeString(cook_time_minutes)
		parcel.writeString(promotion)
		parcel.writeInt(id)
		parcel.writeTypedList(sections)
		parcel.writeParcelable(show, flags)
		parcel.writeString(total_time_minutes)
		parcel.writeString(yields)
		parcel.writeStringList(facebook_posts)
		parcel.writeInt(brand_id)
		parcel.writeInt(num_servings)
		parcel.writeString(buzz_id)
		parcel.writeInt(approved_at)
		parcel.writeString(beauty_url)
		parcel.writeString(original_video_url)
		parcel.writeInt(video_id)
		parcel.writeString(nutrition_visibility)
		parcel.writeByte(if (is_one_top) 1 else 0)
		parcel.writeTypedList(renditions)
		parcel.writeParcelable(total_time_tier, flags)
		parcel.writeTypedList(instructions)
		parcel.writeString(keywordswords)
		parcel.writeString(language)
		parcel.writeString(slug)
		parcel.writeString(prep_time_minutes)
		parcel.writeStringList(compilations)
		parcel.writeString(thumbnail_url)
		parcel.writeString(thumbnail_alt_text)
		parcel.writeString(video_ad_content)
		parcel.writeTypedList(tags)
		parcel.writeParcelable(nutrition, flags)
		parcel.writeString(name)
		parcel.writeInt(created_at)
		parcel.writeString(description)
		parcel.writeString(draft_status)
		parcel.writeString(video_url)
		parcel.writeTypedList(credits)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Results> {
		override fun createFromParcel(parcel: Parcel): Results {
			return Results(parcel)
		}

		override fun newArray(size: Int): Array<Results?> {
			return arrayOfNulls(size)
		}
	}
}