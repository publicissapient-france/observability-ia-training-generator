package org.publicis.com.domain

enum class OutageEnum(val display : String) {

    NETWORK_FAILURE("network failure"),
    NETWORK_SLOWNESS("network slowness"),
    INSTANCE_DOWN("instance down"),
    PERFORMANCE_ISSUE("performance issue"),
    MISDELIVERY_BAD_REQUEST("misdelivery bade request"),
    STABILITY_ISSUE("stability issue")
}
