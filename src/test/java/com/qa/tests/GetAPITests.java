package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAPITests extends TestBase {
    TestBase testBase;
    String serviceURL;
    String apiURL;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    public static JSONObject responseJson;

    @BeforeMethod
    public void setUp() {
        testBase = new TestBase();
        serviceURL = prop.getProperty("URL");
        apiURL = prop.getProperty("serviceURL");

        url = serviceURL + apiURL;
    }

    @Test(priority = 1)
    public void getTest() throws IOException {
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);

        //a.status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code ---------> " + statusCode);

        Assert.assertEquals(statusCode, 200, "Status code is not 200");
    }
    @Test(priority = 2)
    public void getnameValueTest () throws IOException {

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        responseJson = new JSONObject(responseString);
        System.out.println("Response body ------->" + responseJson);

        String nameValue = TestUtil.getValueByPath(responseJson, "/Name");
        System.out.println("Name Value ------->" + nameValue);

        Assert.assertEquals(nameValue, "Carbon credits");
    }

    @Test(priority = 3)
    public JSONObject getcanRelistTest () throws IOException {

        String canRelist = TestUtil.getValueByPath(responseJson, "/CanRelist");
        System.out.println("CanRelist Value ------->" + canRelist);

        Assert.assertEquals(canRelist, "true");
        return responseJson;
    }

    @Test(priority = 4)
    public void getdescriptionTest () throws IOException {

        for (int i = 0; i < 4; i++) {
            String Name = TestUtil.getValueByPath(responseJson, "/Promotions[" + i + "]/Name");
            if (Name.equals("Gallery")) {
                String desciption = TestUtil.getValueByPath(responseJson, "/Promotions[" + i + "]/Description");

                System.out.println("description Value ------->" + desciption);

                Assert.assertEquals(desciption, "Good position in category");
            }
        }
    }
}
