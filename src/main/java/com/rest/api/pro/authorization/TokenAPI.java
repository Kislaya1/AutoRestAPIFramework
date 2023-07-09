package com.rest.api.pro.authorization;

import com.rest.api.pro.apiBuilder.ApiBuilder;
import com.rest.api.pro.config.production.IProdEnvConfig;
import com.rest.api.pro.pojo.token.TokenRequest;
import com.rest.api.pro.pojo.token.TokenResponse;
import org.aeonbits.owner.ConfigFactory;

import static com.rest.api.pro.apiBuilder.apiMethodFactory.ApiMethodType.POST;
import static com.rest.api.pro.authorization.IHeaderUtils.withBasicHeaders;

@SuppressWarnings("unchecked")
public class TokenAPI {
    private static final IProdEnvConfig config = ConfigFactory.create(IProdEnvConfig.class);
    private static final String baseUri = config.BASE_URL();

    private final String authToken;

    private TokenAPI() {
        this.authToken = new ApiBuilder()
                .baseUri(baseUri)
                .headers(withBasicHeaders().fetch())
                .body(TokenRequest.getTokenRequest())
                .fetchResponse(POST, config.AUTH_ENDPOINT())
                .as(TokenResponse.class)
                .getToken();
    }

    public static synchronized String getInstance() {
        return TokenHelper.tokenAPIInstance.authToken;
    }

    private static class TokenHelper {
        private static final TokenAPI tokenAPIInstance = new TokenAPI();
    }
}
