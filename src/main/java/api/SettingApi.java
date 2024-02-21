package api;

import config.Endpoint;
import core.BaseApi;
import io.qameta.allure.Step;

import javax.net.ssl.HttpsURLConnection;

public class SettingApi extends BaseApi {

    @Step
    public void setInput(){
        String body = "{\n" +
                "    \"systemConfMenus\": [\n" +
                "        {\n" +
                "            \"systemGenerationConfs\": [],\n" +
                "            \"systemConf\": {\n" +
                "                \"hpId\": 1,\n" +
                "                \"grpCd\": 5005,\n" +
                "                \"grpEdaNo\": 0,\n" +
                "                \"val\": 1,\n" +
                "                \"param\": \"\",\n" +
                "                \"biko\": \"\",\n" +
                "                \"isUpdatePtRyosyo\": false,\n" +
                "                \"systemSettingModelStatus\": 2\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        sendPost(Endpoint.SAVE_SETTING, body);
        validateStatusCode(HttpsURLConnection.HTTP_OK);
    }
}
