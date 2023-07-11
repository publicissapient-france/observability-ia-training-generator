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

                val context = "System logs: \\n " + configuration.outageToObservabilityEvent[impactedFeaturedByOutage.key]?.map {
                    it + " on " + impactedFeaturedByOutageAndService.key.display
                }?.stream()?.collect(Collectors.joining(".\\n"))

                val generalQuestion = "Question: How is my system running ?"



                configuration.serviceToImpactedFeature.keys.forEach {
                    val questionService = "Question: How is " + it.display + " running ?\\n"
                    if(it == impactedFeaturedByOutageAndService.key){
                        val impactedFeatures = impactedFeaturedByOutage.value.map { impactedFeature ->
                            impactedFeature.impactLevel.display + " " +
                                    "degraded experience on "+
                                    impactedFeature.featureName
                        }.stream().collect(Collectors.joining("\\n"))


                        val completion = impactedFeatures + " due to " +
                                impactedFeaturedByOutage.key.display + " on " +
                                impactedFeaturedByOutageAndService.key.display
                        println("$context ### $generalQuestion,$completion END")
                        println("$context ### $questionService,$completion END")
                    }else {
                        println("$context ### $questionService,no issue detected on service ${it.display} END")
                    }
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