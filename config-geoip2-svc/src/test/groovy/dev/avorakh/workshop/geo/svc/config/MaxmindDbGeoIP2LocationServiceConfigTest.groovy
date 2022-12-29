package dev.avorakh.workshop.geo.svc.config

import com.maxmind.geoip2.DatabaseReader
import org.springframework.core.env.Environment
import spock.lang.Shared
import spock.lang.Specification

import java.security.InvalidParameterException

class MaxmindDbGeoIP2LocationServiceConfigTest extends Specification {

    @Shared
    def maxmindDbFile = "src/test/resources/GeoIP2-City-Test.mmdb"

    MaxmindDbGeoIP2LocationServiceConfig config = new MaxmindDbGeoIP2LocationServiceConfig()
    def env = Mock(Environment)

    def "should successfully return maxmindDBProvider if file db exists"() {

        when:
            def actual = config.maxmindDBProvider(env)
        then:
            1 * env.getRequiredProperty("maxmind.db.city.file.path") >> maxmindDbFile
        then:
            actual != null
            actual instanceof DatabaseReader
    }

    def "should thrown exception on maxmindDBProvider if file db path is invalid"() {

        when:
            config.maxmindDBProvider(env)
        then:
            1 * env.getRequiredProperty("maxmind.db.city.file.path") >> "src/test/resources/Invalid_GeoIP2-City-Test.mmdb"
        then:
            thrown(InvalidParameterException)
    }

    def "should thrown exception on maxmindDBProvider if file db path is folder"() {

        when:
            config.maxmindDBProvider(env)
        then:
            1 * env.getRequiredProperty("maxmind.db.city.file.path") >> "src/test/resources/"
        then:
            thrown(InvalidParameterException)
    }

    def "should thrown exception on maxmindDBProvider if environment doesn't contain the maxmind db path property"() {

        when:
            config.maxmindDBProvider(env)
        then:
            1 * env.getRequiredProperty("maxmind.db.city.file.path") >> { throw new IllegalStateException() }
        then:
            thrown(IllegalStateException)
    }
}
