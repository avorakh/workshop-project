package dev.avorakh.workshop.geo.svc.impl;

import com.maxmind.geoip2.DatabaseProvider;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import dev.avorakh.workshop.geo.pub.LocationData;
import dev.avorakh.workshop.geo.svc.GeoIP2LocationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class AbstractMaxmindDbGeoIP2LocationService implements GeoIP2LocationService {

    /**
     * The reader to use the database API
     */
    @NotNull DatabaseProvider provider;

    /**
     * Lookup geolocation data by ip addresses.
     *
     * @param ip IPv4 or IPv6 address to lookup..
     * @return {@link dev.avorakh.workshop.geo.pub.LocationData} Location data associated with an IP address.
     */
    protected @NotNull Optional<LocationData> lookup(@NotNull InetAddress ip) throws IOException, GeoIp2Exception {

        return provider.tryCity(ip)
            .map(cityResponse -> new LocationData(
                    cityResponse.getCountry().getIsoCode(),
                    cityResponse.getLocation().getTimeZone()
                )
            );
    }
}
