package com.dc.kavach.domain.models

data class BannedApps(
    val apps: List<BannedApp>
) {
    constructor(): this(emptyList())
}
