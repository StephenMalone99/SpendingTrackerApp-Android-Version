package org.wit.spendingtracker_androidapp_asgn2.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PurchaseModel(var id: Long = 0,
                         var purchaseName: String = "",
                         var description: String = "",
                         var cost: Int = 0,
                         var image: Uri = Uri.EMPTY) : Parcelable
