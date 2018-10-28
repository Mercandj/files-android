package com.mercandalli.android.apps.files.network

import org.json.JSONObject

interface NetworkDownloader {

    fun getDownloadSync(
            url: String,
            headers: Map<String, String>,
            jsonObject: JSONObject,
            javaFile: java.io.File,
            listener: Network.DownloadProgressListener
    ): String?
}