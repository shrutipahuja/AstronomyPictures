package com.example.astronomypictures.utility

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.nio.charset.Charset

/**
 * Utility class to read data from JSON
 */
@Suppress("JAVA_CLASS_ON_COMPANION")
class DataUtils {

    companion object {
        /**
         * Method used to read data from JSON object and convert into string
         */
        fun readJson(fileName: String): String {
            val inputStream = javaClass.classLoader!!
                .getResourceAsStream("imagedata/$fileName")
            return inputStream.readBytes().toString(Charset.defaultCharset())

        }

        /**
         * Returns the data in a usable form
         */
        inline fun <reified T> fromJson(json: String): T {
            return Gson().fromJson(json, object : TypeToken<T>() {}.type)
        }
    }
}