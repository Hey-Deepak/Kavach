package com.dc.kavach.model

data class BannedApp(
    val packageName: String,
    val name: String = ""
) {
    constructor(): this("")
}
