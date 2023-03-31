//package com.example.myandroidproject.core.utils
//
//import android.content.Context
//import com.example.myandroidproject.core.R
//import com.example.myandroidproject.core.data.source.remote.response.DataResponse
//import org.json.JSONObject
//import java.io.IOException
//
//class JsonHelper(private val context: Context) {
//
//    private fun parsingFileToString(): String? {
//        val jsonString: String
//        try {
//            jsonString =
//                context.resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
//        } catch (ioException: IOException) {
//            ioException.printStackTrace()
//            return null
//        }
//        return jsonString
//    }
//
//    fun loadData(): List<DataResponse> {
//        val list = ArrayList<DataResponse>()
//        val responseObject = JSONObject(parsingFileToString().toString())
//        val listArray = responseObject.getJSONArray("items")
//        for (i in 0 until listArray.length()) {
//            val course = listArray.getJSONObject(i)
//
//            val id = course.getInt("id")
//            val login = course.getString("login")
//            val avatarUrl = course.getString("avatar_url")
//
//            val courseResponse = DataResponse(
//                id = id,
//                login = login,
//                avatarUrl = avatarUrl
//            )
//            list.add(courseResponse)
//
//        }
//        return list
//    }
//}