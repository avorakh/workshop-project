package dev.avorakh.workshop.geo.svc;

import dev.avorakh.workshop.geo.pub.LocationData;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;

/**
 * Common interface for geolocation data lookups by ip addresses.
 *
 * @since 0.1.0
 */
public interface GeoIP2LocationService {

    /**
     * Lookup geolocation data by ip addresses.
     *
     * @param ip IPv4 or IPv6 address to lookup..
     * @return {@link dev.avorakh.workshop.geo.pub.LocationData} Location data associated with an IP address.
     */
    @NotNull LocationData lookup(@NotNull String ip);


}

