package com.auto.rest.api.pojo.token;

import com.auto.rest.api.config.production.IProdEnvConfig;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.aeonbits.owner.ConfigFactory;

@Builder
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TokenRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public static TokenRequest getTokenRequest() {
        final IProdEnvConfig config = ConfigFactory.create(IProdEnvConfig.class);
        return new TokenRequestBuilder()
                .username(config.ADMIN_USERNAME())
                .password(config.ADMIN_PASSWORD())
                .build();
    }
}
