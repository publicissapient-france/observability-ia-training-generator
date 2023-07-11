package org.publicis.com.domain

enum class ServiceEnum(val display: String) {
    ACCOUNT_API("account-api"),
    FIREWALL("firewall service"),
    API_GATEWAY("api-gateway"),
    AUTHENTICATION("authentication"),
    PAYMENT_API("payment-api"),
    PAYMENT_SERVICE_PROVIDER("payment service provider"),
    ACCOUNT_DATABASE("account database"),
    PRODUCT_API("product-api"),
    PRODUCT_DATABASE("product database"),
    ORDER_API("order-api"),
    ORDER_DATABASE("order database")
}
