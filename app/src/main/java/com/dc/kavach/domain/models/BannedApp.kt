package com.dc.kavach.domain.models

data class BannedApp(
    val packageName: String,
    val name: String = ""
) {
    constructor(): this("")
}
