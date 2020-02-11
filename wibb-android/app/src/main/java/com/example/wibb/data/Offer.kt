package com.example.wibb.data

import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Offer {
    var store: Store? = null
    var beer: Beer? = null
    var price: Int = 0
    var startDate: LocalDate? = null
    var endDate: LocalDate? = null

    val isValid: Boolean
        get() = price >= 0 && store != null && beer != null && endDate != null

    companion object {
        fun fromJSON(joffer: JSONObject): Offer {
            val o = Offer()
            o.beer = Beer.fromJSON(joffer.getJSONObject("beer"))
            o.store = Store.fromJSON(joffer.getJSONObject("store"))
            o.endDate = LocalDate.parse(joffer.getString("endDate"), DateTimeFormatter.ISO_DATE_TIME)
            o.startDate = LocalDate.parse(joffer.getString("startDate"), DateTimeFormatter.ISO_DATE_TIME)
            o.price = joffer.getInt("price")
            return o
        }
    }

    fun toJSON(): JSONObject{
        val jo = JSONObject()
        jo.put("beer", this.beer?.toJSON())
        jo.put("store", this.store?.toJSON())
        jo.put("startDate", this.startDate?.atStartOfDay()?.format(DateTimeFormatter.ISO_DATE_TIME))
        jo.put("endDate", this.endDate?.atStartOfDay()?.format(DateTimeFormatter.ISO_DATE_TIME))
        jo.put("price", this.price)
        return jo
    }
}
