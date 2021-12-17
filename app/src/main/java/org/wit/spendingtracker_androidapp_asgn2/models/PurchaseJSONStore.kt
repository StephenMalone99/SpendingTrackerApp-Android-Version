package org.wit.spendingtracker_androidapp_asgn2.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.spendingtracker_androidapp_asgn2.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "itemspurchased.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<PurchaseModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PurchaseJSONStore(private val context: Context) : PurchaseStore {

    var purchases = mutableListOf<PurchaseModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PurchaseModel> {
        logAll()
        return purchases
    }

    override fun create(purchase: PurchaseModel) {
        purchase.id = generateRandomId()
        purchases.add(purchase)
        serialize()
    }


    override fun update(purchase: PurchaseModel) {
        var foundPurchase: PurchaseModel? = purchases.find { p -> p.id == purchase.id }
        if (foundPurchase != null) {
            foundPurchase.purchaseName = purchase.purchaseName
            foundPurchase.description = purchase.description
            foundPurchase.cost = purchase.cost.toString().toInt()
            foundPurchase.image = purchase.image
            foundPurchase.lat = purchase.lat
            foundPurchase.lng = purchase.lng
            foundPurchase.zoom = purchase.zoom
        }
        serialize()
    }

    override fun delete(purchase: PurchaseModel) {
        purchases.remove(purchase)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(purchases, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        purchases = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        purchases.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}