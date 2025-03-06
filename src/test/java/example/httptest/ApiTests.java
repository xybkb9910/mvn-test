package example.httptest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
public class ApiTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://visit.cnblogs.com/api/v1/events";
        RestAssured.requestSpecification = new RequestSpecBuilder().addCookie("\n" +
                        "Hm_lvt_866c9be12d4a814454792b1fd0fed295=1738821786; HMACCOUNT=2A0E0B3450988CA7; _ga=GA1.1.2098377796.1738827852; affinity=1740971574.034.661.34805|8475adb1a89a48fdb29b348a077b821c; _ga_M95P3TTWJZ=GS1.1.1740971572.22.1.1740972477.0.0.0; Hm_lpvt_866c9be12d4a814454792b1fd0fed295=1740972478")
                .build();
    }

    @Test
    public void testGetHello() {
        // 发送GET请求并获取响应
        Response response = RestAssured.get("/specific-method");
        // 验证响应状态码为200
        response.then().statusCode(200);
        // 验证响应体中包含特定字段
        response.then().body("clientId", equalTo("2ed3ffe1-10a6-4e99-bb74-4b19da96e57c"));
        // 验证响应头中的Content-Type为application/json
        response.then().header("Content-Type", "application/json");
    }
}
