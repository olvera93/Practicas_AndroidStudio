package com.olvera.mapsbasics.common.dataAccess

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.entities.Artist
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

object FakeDatabase {

    private var result: List<Artist>? = null

    fun getArtist(context: Context): Artist? {
        getArtists(context)?.let {
            it[getIndexRandom()]
        }

        return null
    }

    private fun getIndexRandom():Int {
        val time = System.currentTimeMillis().toString()
        val lastNumbers = time.substring(time.length - 2, time.length).toInt()

        return if (lastNumbers > 26) lastNumbers/4 else lastNumbers
    }

    fun getArtists(context: Context): List<Artist>? {
        result?.let { return it }

        result = loadObjectsFromJson(context, R.raw.new_style, object : TypeToken<ArrayList<Artist?>?>(){}.type)

        return result
    }


    private fun <T> loadObjectsFromJson(appContext: Context, resource: Int, jsonType: Type?): List<T>? {
        val inputStream = appContext.resources.openRawResource(resource)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)

        try {
            val reader: Reader =
                BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))

            var pointer: Int
            while (reader.read(buffer).also { pointer = it } != -1) {
                writer.write(buffer, 0, pointer)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        val jsonString = writer.toString()
        writer.close()
        val gson = Gson()
        return gson.fromJson(jsonString, jsonType)
    }


}