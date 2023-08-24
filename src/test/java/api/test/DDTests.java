package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userid,String username,String Fname,String lname,String email,String password,String phone)
	{
		User userpayload=new User();
		userpayload.setId(Integer.parseInt(userid));
		userpayload.setUsername(username);
		userpayload.setFirstName(Fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(email);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);
		Response response= UserEndpoints.createUser(userpayload);
		Assert.assertEquals(response.getStatusCode(),200);
					
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUser(String username)
	{
		Response response=UserEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
