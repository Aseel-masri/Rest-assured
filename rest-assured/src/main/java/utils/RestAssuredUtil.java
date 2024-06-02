
package utils;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestAssuredUtil {
    //Sets Base URI
    public static void setBaseURI() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }



    //Reset Base URI (after test)
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }


    //Returns response by given path
    public static Response getResponse(String path) {
        return given().get(path);
    }

 
    //Returns JsonPath object
    public static JsonPath getJsonPath(Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }
}
