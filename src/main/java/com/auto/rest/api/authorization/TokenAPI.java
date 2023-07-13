package com.auto.rest.api.authorization;

import com.auto.rest.api.apiBuilder.ApiBuilder;
import com.auto.rest.api.config.production.IProdEnvConfig;
import com.auto.rest.api.pojo.token.TokenRequest;
import com.auto.rest.api.pojo.token.TokenResponse;
import org.aeonbits.owner.ConfigFactory;

import static com.auto.rest.api.apiBuilder.apiMethodFactory.ApiMethodType.POST;

@SuppressWarnings("unchecked")
public class TokenAPI {
  private static final IProdEnvConfig config = ConfigFactory.create(IProdEnvConfig.class);
  private static final String BASE_URL = config.BASE_URL();

  private final String authToken;

  private TokenAPI() {
    this.authToken =
        new ApiBuilder()
            .baseUri(BASE_URL)
            .headers(IHeaderUtils.withBasicHeaders().fetch())
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
