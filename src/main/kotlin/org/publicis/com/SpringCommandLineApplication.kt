package org.publicis.com

import org.publicis.com.domain.Configuration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.stream.Collectors


@SpringBootApplication
class SpringCommandLineApplication(private val configuration: Configuration) : CommandLineRunner {
    override fun run(vararg args: String) {
        LOG.info("EXECUTING : command line runner")
        for (i in args.indices) {
            LOG.info("args[{}]: {}", i, args[i])
        }

        configuration.serviceToImpactedFeature.forEach { impactedFeaturedByOutageAndService ->
            impactedFeaturedByOutageAndService.value.entries.forEach { impactedFeaturedByOutage ->
                val context = configuration.outageToObservabilityEvent[impactedFeaturedByOutage.key]?.map {
                    it + " on " + impactedFeaturedByOutageAndService.key
                }
                val question = "How is my system running ?"
                val prompt = context?.stream()?.collect(Collectors.joining(",")) + "\n" + question

                impactedFeaturedByOutage.value.forEach { impactedFeature ->
                    val completion =
                        "degradation experience on " +
                        impactedFeature +
                        " due to " +
                        impactedFeaturedByOutage.key +
                        " on " +
                        impactedFeaturedByOutageAndService.key
                    println("$prompt;$completion")
                }
            }
        }
    }

    companion object {
        private val LOG: Logger = LoggerFactory
            .getLogger(SpringCommandLineApplication::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            LOG.info("STARTING THE APPLICATION")
            SpringApplication.run(SpringCommandLineApplication::class.java, *args)
            LOG.info("APPLICATION FINISHED")
        }
    }
}