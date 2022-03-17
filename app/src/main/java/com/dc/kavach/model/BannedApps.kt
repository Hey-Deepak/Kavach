package com.dc.kavach.model

data class BannedApps(
    val apps: List<BannedApp>
) {
    constructor(): this(emptyList())
}
