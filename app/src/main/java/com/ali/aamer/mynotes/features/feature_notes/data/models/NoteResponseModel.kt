package com.ali.aamer.notesapp.models.notes

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_notes")
data class NoteResponseModel(
    @PrimaryKey
    val _id: String,
    val createdAt: String,
    val description: String,
    val title: String,
    val updatedAt: String,
    val userId: String,
    var hasUpdateSync: Int,
    var hasCreateSync: Int,
    var hasDeleteSync: Int,
    val __v: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(createdAt)
        parcel.writeString(description)
        parcel.writeString(title)
        parcel.writeString(updatedAt)
        parcel.writeString(userId)
        parcel.writeInt(hasUpdateSync)
        parcel.writeInt(hasCreateSync)
        parcel.writeInt(hasDeleteSync)
        parcel.writeInt(__v)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteResponseModel> {
        override fun createFromParcel(parcel: Parcel): NoteResponseModel {
            return NoteResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<NoteResponseModel?> {
            return arrayOfNulls(size)
        }
    }
}