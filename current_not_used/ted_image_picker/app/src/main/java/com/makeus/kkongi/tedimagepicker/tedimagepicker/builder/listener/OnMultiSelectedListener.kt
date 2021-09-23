package com.makeus.kkongi.tedimagepicker.tedimagepicker.builder.listener

import android.net.Uri

interface OnMultiSelectedListener {
    fun onSelected(uriList: List<Uri>)
}