package com.deas.movieapps

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class TestUtils {

    companion object {

        @JvmStatic
        fun <T> getObjectFromJson(clazz: Class<T>, fileName: String): T {
            val streamReader = InputStreamReader(TestUtils::class.java.classLoader?.getResourceAsStream(fileName))
            return Gson().fromJson(streamReader, clazz)
        }

        @JvmStatic
        fun <T> getListFromJson(fileName: String): List<T> {
            val type = object : TypeToken<List<T>>() {}.type
            return Gson().fromJson<List<T>>(fileName, type)
        }

        private fun getStringFromInputString(streamReader: InputStreamReader): String? {
            return try {
                val stringBuilder = StringBuilder()
                val bufferedReader = BufferedReader(streamReader)
                while (bufferedReader.readLine() != null) stringBuilder.append(bufferedReader.readLine())
                bufferedReader.close()
                stringBuilder.toString()
            } catch (e: IOException) {
                null
            }
        }
    }
}