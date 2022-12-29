package dev.avorakh.workshop.geo.pub.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import dev.avorakh.workshop.geo.pub.LocationData
import spock.lang.Shared
import spock.lang.Specification

class LocationDataDeserializerTest extends Specification {

    @Shared
    ObjectMapper objectMapper

    def setupSpec() {

        objectMapper = new ObjectMapper()
        def deserializationModule = new SimpleModule()
        deserializationModule.addDeserializer(LocationData.class, new LocationDataDeserializer())
        objectMapper.registerModule(deserializationModule)
    }

    def "should successfully deserialize login credentials if countyIsoCode and timeZone are present"() {

        given:
            def countyIsoCode = 'PL'
            def timeZone = 'Europe/Warsaw'
            def json = "{ \"countyIsoCode\" : \"$countyIsoCode\", \"timeZone\" : \"$timeZone\" }"
        when:
            def result = objectMapper.readValue(json, LocationData.class)
        then:
            result != null
            with(result as LocationData) {
                it.countyIsoCode() == countyIsoCode
                it.timeZone() == timeZone
            }
    }

    def "should successfully deserialize login credentials if only countyIsoCode is present"() {

        given:
            def countyIsoCode = 'DE'
            def json = "{ \"countyIsoCode\" : \"$countyIsoCode\"}"
        when:
            def result = objectMapper.readValue(json, LocationData.class)
        then:
            result != null
            with(result as LocationData) {
                it.countyIsoCode() == countyIsoCode
                it.timeZone() == null
            }
    }

}
