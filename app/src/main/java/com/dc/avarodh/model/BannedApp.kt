package com.dc.avarodh.model

data class BannedApp(
    val packageName: String,
    val name: String = ""
) {
    constructor(): this("")
}
