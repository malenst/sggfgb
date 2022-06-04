package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class JSONParse (val array : ListView, val context : Context){

//var listItems = arrayOfNulls<String>(17)


    fun jsonParse(){
        val requestQueue = Volley.newRequestQueue(context)
    val url = "https://rickandmortyapi.com/api/character"
        Log.d("teg", "listItems[i].toString()")
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                response ->try {
            val jsonArray = response.getJSONArray("results")
            val listItems = arrayOfNulls<String>(jsonArray.length())

            for (i in 0 until jsonArray.length()) {
                val character = jsonArray.getJSONObject(i)
                val name = character.getString("name")
                val gender = character.getString("gender")
                listItems[i] = "$name\n\nGender: $gender"
                Log.d("teg", listItems[i].toString())
            }

            val adapter = ArrayAdapter(
                context,
                android.R.layout.simple_list_item_1, listItems
            )
            array.adapter = adapter
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, { error -> error.printStackTrace()})
        requestQueue.add(request)
}


}