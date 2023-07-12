package com.auto.rest.api.config.production;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties", "system:env", "file:${user.dir}/src/test/resources/production/env.properties"
})
public interface IProdEnvConfig extends Config {
    @DefaultValue("PROD")
    String ENVIRONMENT();

    @Key("${ENVIRONMENT}_ADMIN_USERNAME")
    String ADMIN_USERNAME();

    @Key("${ENVIRONMENT}_ADMIN_PASSWORD")
    String ADMIN_PASSWORD();

    @Key("${ENVIRONMENT}_BASE_URL")
    String BASE_URL();

    String AUTH_ENDPOINT();

    String BOOKING_ENDPOINT();

    @DefaultValue("/booking/%s")
    String GET_ALL_BOOKING_ENDPOINT(final String bookingId);

    @DefaultValue("/booking/%s")
    String MODIFY_BOOKING_ENDPOINT(final String bookingId);

    @DefaultValue("/booking/%s")
    String DELETE_BOOKING_ENDPOINT(final String bookingId);

    @DefaultValue("src/test/java/com/auto/rest/api/schemas/booking/%s")
    String BOOKING_SCHEMA_DIR(final String filePath);
}
