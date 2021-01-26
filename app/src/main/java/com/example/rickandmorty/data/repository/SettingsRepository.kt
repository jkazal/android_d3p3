package com.example.rickandmorty.data.repository

import android.R.attr.path
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.rickandmorty.data.entities.SettingsEntity
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File
import javax.inject.Inject


class SettingsRepository @Inject constructor(
) {
    fun updateSettingsJson(roomId: String, date: String) {

    }

    fun getJsonBodyFromConfig(context: Context): JsonObject {
        val file = File(context.filesDir, "config.json")
        val content: String = String(file.readBytes())
        return JsonParser().parse(content).asJsonObject
    }

    fun getCurrentRoomId(context: Context): String {
        val jso = getJsonBodyFromConfig(context)
        return jso.get("roomId").asString
    }

    fun getCurrentSelectedDate(context: Context): String {
        val jso = getJsonBodyFromConfig(context)
        return jso.get("date").asString
    }

    public class SettingsFormat {
        public var roomId: String = ""
        public var date: String = ""
    }
}