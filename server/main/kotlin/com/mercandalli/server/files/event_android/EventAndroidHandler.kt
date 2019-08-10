@file:Suppress("PackageName")

/* ktlint-disable package-name */
package com.mercandalli.server.files.event_android

import com.mercandalli.server.files.main.ApplicationGraph
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.util.pipeline.PipelineContext
import org.json.JSONObject

object EventAndroidHandler {

    suspend fun PipelineContext<Unit, ApplicationCall>.androidEventPost(
        appPackageName: String,
        appVersionName: String,
        requestBody: String
    ) {
        val eventHandlerPost = ApplicationGraph.getEventHandlerPost()
        val eventResponse = eventHandlerPost.handleEvent(
            "android",
            appPackageName,
            appVersionName,
            requestBody
        )
        val responseJsonObject = JSONObject()
        responseJsonObject.put("succeeded", eventResponse.isSucceeded())
        responseJsonObject.put("events_added_count", eventResponse.getEventsAddedCount())
        responseJsonObject.put("events_written_count", eventResponse.getEventsWrittenCount())
        call.respondText(
            responseJsonObject.toString()
        )
    }
}
