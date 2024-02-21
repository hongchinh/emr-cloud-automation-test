package api;

import api.model.ExchangeTokenResponse;
import config.Constant;
import config.Endpoint;
import core.BaseApi;
import io.qameta.allure.Step;

import javax.net.ssl.HttpsURLConnection;

public class IamApi extends BaseApi {

    @Step
    public void login(){
        String payload = "{\"loginId\":\"%s\",\"password\":\"%s\"}";
        sendPost(Endpoint.LOGIN, String.format(payload, Constant.DEFAULT_USERNAME, Constant.DEFAULT_PASSWORD));
        validateStatusCode(HttpsURLConnection.HTTP_OK);
        ExchangeTokenResponse res = (ExchangeTokenResponse) saveResponseObject(ExchangeTokenResponse.class);
        setHeader("Authorization", "Bearer " + res.getData().getToken());
    }
}
