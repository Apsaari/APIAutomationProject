import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class TestAPI extends BaseTest {

    @Test(description = "Verify Activity Creation")
    public void testActivityCreation() {

        JSONObject userRequestJson = new JSONObject();
        userRequestJson.put("id", 1);
        userRequestJson.put("title", "Test Title");
        userRequestJson.put("dueDate", "2021-11-10T10:25:31.962Z");
        userRequestJson.put("completed", true);

        given()
                .request()
                .spec(requestSpec)
                .body(userRequestJson.toString()).log().all()
                .post(Constants.ACTIVITY_ENDPOINT)
                .then().log().all()
                .statusCode(SC_OK)
                .body("id", equalTo(1))
                .body("title", equalTo("Test Title"))
                .body("dueDate", equalTo("2021-11-10T10:25:31.962Z"))
                .body("completed", equalTo(true));
    }
    @Test(description = "Get Activity by Id")
    public void testActivityRetrieval() {
        given()
                .request()
                .spec(requestSpec)
                .get(Constants.ACTIVITY_ENDPOINT+"/10")
                .then().log().all()
                .statusCode(SC_OK);    }

}
