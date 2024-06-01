import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;


public class BasicApiTest extends BaseTest {

	@Test
	public void T01_GetUsersTest() {
		res = utils.RestAssuredUtil.getResponse("https://jsonplaceholder.typicode.com/users"); // Call the API using GET
																								// method
		testUtil.checkStatusIs200(res); // Test that the response return 200 successful

		// Count the number of ids you got from the response
		jp = utils.RestAssuredUtil.getJsonPath(res);
		System.out.println("Users count: " + testUtil.getClients(jp).size());

		// Print each id with the relative name
		for (int i = 0; i < testUtil.getClients(jp).size(); i++) {
			System.out.println("User id: " + jp.getString("[" + i + "].id") + "\t" + "User name: "
					+ jp.getString("[" + i + "].name"));
		}

	}

//
	@Test
	public void T02_GetPostsTest() {
		res = utils.RestAssuredUtil.getResponse("https://jsonplaceholder.typicode.com/posts"); // Call the API using GET
																								// method
		testUtil.checkStatusIs200(res); // Test that the response return 200 successful
		jp = utils.RestAssuredUtil.getJsonPath(res);
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

}