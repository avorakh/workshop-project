package dev.avorakh.workshop.geo.pub.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import dev.avorakh.workshop.geo.pub.LocationData
import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

class LocationDataSerializerTest extends Specification {

    @Shared
    ObjectMapper objectMapper

    def setupSpec() {

        objectMapper = new ObjectMapper()
        def serializationModule = new SimpleModule()
        serializationModule.addSerializer(LocationData.class, new LocationDataSerializer())
        objectMapper.registerModule(serializationModule)
    }

    def 'should successfully serialize if countyIsoCode and timeZone are present'() {

        given:
            def countyIsoCode = 'PL'
            def timeZone = 'Europe/Warsaw'
            def locationData = new LocationData(countyIsoCode, timeZone)
        and:
            def jsonSlurper = new JsonSlurper()
        when:

            def result = objectMapper.writeValueAsString(locationData)
        then:
            result != null
        and:
            def jsonMap = jsonSlurper.parseText(result) as Map
        then:
            !jsonMap.isEmpty()
            jsonMap.size() == 2
            jsonMap.countyIsoCode == countyIsoCode
            jsonMap.timeZone == timeZone
    }

    def 'should successfully serialize if only countyIsoCode is present'() {

        given:
            def countyIsoCode = 'US'
            def locationData = LocationData.builder()
                .countyIsoCode(countyIsoCode)
                .build()
        and:
            def jsonSlurper = new JsonSlurper()
        when:
            def result = objectMapper.writeValueAsString(locationData)
        then:
            result != null
        and:
            def jsonMap = jsonSlurper.parseText(result) as Map
        then:
            !jsonMap.isEmpty()
            jsonMap.size() == 1
            jsonMap.countyIsoCode == countyIsoCode
            jsonMap.timeZone == null
    }
}
