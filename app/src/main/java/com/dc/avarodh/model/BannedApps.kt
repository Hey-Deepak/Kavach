package com.dc.avarodh.model

data class BannedApps(
    val apps: List<BannedApp>
) {
    constructor(): this(emptyList())
}
