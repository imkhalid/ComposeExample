package com.example.myapplication.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("name") val name: String?,
    @SerializedName("affiliation") val affiliation: String?,
    @SerializedName("species") val species: String?,
    @SerializedName("sex") val sex: String?,
    @SerializedName("fictional") val fictional: Boolean = false,
    @SerializedName("dob") val dob: String?,
    @SerializedName("dod") val dod: String?,
    @SerializedName("wikilink") val wikilink: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("note") val note: String?
) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        (source.readInt() != 0),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(affiliation)
        dest.writeString(species)
        dest.writeString(sex)
        dest.writeInt(if (fictional) 1 else 0);
        dest.writeString(dob)
        dest.writeString(dod)
        dest.writeString(wikilink)
        dest.writeString(image)
        dest.writeString(note)
    }

    companion object {
        @JvmField
        final val CREATOR: Parcelable.Creator<Feed> = object : Parcelable.Creator<Feed> {
            override fun createFromParcel(source: Parcel): Feed {
                return Feed(source)
            }

            override fun newArray(size: Int): Array<Feed?> {
                return arrayOfNulls(size)
            }
        }
    }
}
