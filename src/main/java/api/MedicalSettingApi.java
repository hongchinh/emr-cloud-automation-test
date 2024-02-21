package api;

import config.Endpoint;
import core.BaseApi;
import io.qameta.allure.Step;

import javax.net.ssl.HttpsURLConnection;

public class MedicalSettingApi extends BaseApi {
    @Step
    public void resetMedicalSetting() {
        String body = "{\n" +
                "            \"userConfs\": [\n" +
                "            {\n" +
                "                \"userId\": 1,\n" +
                "                    \"grpCd\": 921,\n" +
                "                    \"grpItemCd\": 5,\n" +
                "                    \"grpItemEdaNo\": 0,\n" +
                "                    \"val\": 0,\n" +
                "                    \"param\": \"00001\"\n" +
                "            }\n" +
                "    ]\n" +
                "        }";
        sendPost(Endpoint.MEDICAL_SETTING, body);
        validateStatusCode(HttpsURLConnection.HTTP_OK);

    }
}
