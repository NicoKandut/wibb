package com.example.wibb.data

import org.json.JSONObject

class Beer(var name: String, var icon: String): GridDisplayable {

    override val iconurl: String
        get() = icon

    override val text: String
        get() = name

    companion object {
        fun fromJSON(jbeer: JSONObject): Beer {
            return Beer(
                jbeer.getString("name"),
                jbeer.getString("icon")
            )
        }
    }
}
