package org.publicis.com.domain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Configuration


@Configuration
class Configuration {
    val serviceToImpactedFeature: Map<ServiceEnum, Map<OutageEnum,List<String>>> =
        ObjectMapper()
            .registerKotlinModule()
            .readValue(
                javaClass.getResource("/impactedFeatureByOutageByServices.json"),
                object : TypeReference<Map<ServiceEnum, Map<OutageEnum,List<String>>>>() {})



    val outageToObservabilityEvent: Map<OutageEnum, List<String>> =
        ObjectMapper()
            .registerKotlinModule()
            .readValue(
                javaClass.getResource("/observabilityEvenByOutage.json"),
                object : TypeReference<Map<OutageEnum, List<String>>>() {})



}

