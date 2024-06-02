import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.TestUtil;


public class BasicApiTest  {
    public Response response = null; //Response
    public JsonPath jp  = null; //JsonPath
    TestUtil testUtil = new TestUtil();
    
	 @BeforeMethod
	    public void setup() {
		 RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		 
	    }

	@Test
	public void T01_GetUsersTest() {
	      

		    // Call the API using GET
	         response = utils.RestAssuredUtil.getResponse("/users");
	         jp  = null; //JsonPath

	        testUtil.checkStatusIs200(response); // Test that the response return 200 successful
	        jp = utils.RestAssuredUtil.getJsonPath(response);
	        // Print response body
	        System.out.println("Users count: " + testUtil.getClients(jp).size());
	     // Print each id with the relative name
			for (int i = 0; i < testUtil.getClients(jp).size(); i++) {
			System.out.println("User id: " + jp.getString("[" + i + "].id") + "\t" + "User name: "
					+ jp.getString("[" + i + "].name"));
		}

	}


	@Test
	public void T02_GetPostsTest() {
		response = utils.RestAssuredUtil.getResponse("/posts"); // Call the API using GET
																								// method
		testUtil.checkStatusIs200(response); // Test that the response return 200 successful
		jp = utils.RestAssuredUtil.getJsonPath(response);
		for (int i = 0; i < testUtil.getClients(jp).size(); i++) {
			
			//The id 17 has title “ fugit voluptas sed molestias voluptatem provident “
			if (jp.getString("[" + i + "].id").equals("17")) {
				assertEquals("fugit voluptas sed molestias voluptatem provident", jp.getString("[" + i + "].title"));
				
			}
			//In each object , we have the keys (userid, id body, title) with non empty values
		    String userId = jp.getString("[" + i + "].userId");
		    String id = jp.getString("[" + i + "].id");
		    String body = jp.getString("[" + i + "].body");
		    String title = jp.getString("[" + i + "].title");

		    assertTrue(!userId.isEmpty());
		    assertTrue(!id.isEmpty());
		    assertTrue(!body.isEmpty());
		    assertTrue(!title.isEmpty());

		}

	}
    @AfterClass
    public void afterTest() {
        //Reset Values
        utils.RestAssuredUtil.resetBaseURI();
   
    }

}