package com.example.memoryusagetest

import android.net.Uri
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore

data class Album(
    val name: String,
    val thumbnailUri: Uri,
    val mediaUris: List<Media>
) {
    val mediaCount: Int = mediaUris.size
}

data class Media(
    val albumName: String,
    val uri: Uri,
    val dateAddedSecond: Long
)

enum class MediaType(
    val savedDirectoryName: String,
    val fileSuffix: String,
    val mimeType: String,
    val externalContentUri: Uri
) {
    IMAGE(
        Environment.DIRECTORY_PICTURES,
        ".jpg",
        "image/*",
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    ),
    VIDEO(
        Environment.DIRECTORY_MOVIES,
        ".mp4",
        "video/*",
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    )
}
