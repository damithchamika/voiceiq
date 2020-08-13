package restapi;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.EmpApiTestHelper;
import util.RestAssuredUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class ApiTest {
    private static final String INVALID_PARAM = "invalid";


    private static final Logger LOG = Logger.getLogger(ApiTest.class.getName());
    RequestSpecification requestSpecification;
    EmpApiTestHelper empApiHelper;

    @BeforeClass
    public void complaintApiTest() {
        requestSpecification = RestAssuredUtil.setBaseURI("https://reqres.in/api/");
        empApiHelper = new EmpApiTestHelper(requestSpecification);
    }

    @Test
    public void testEmployeeInvalidUrl() {
        Response response = empApiHelper.getEmployeeList(INVALID_PARAM);
        assertEquals("Status code is incorrect", HttpStatus.SC_NOT_FOUND, response.getStatusCode());
        assertEquals("Content Type is incorrect", ContentType.JSON.toString(), response.getContentType().split(";")[0]);
        Map jsonResponse = response.jsonPath().get();
        assertTrue("Not Found".equals(jsonResponse.get("error")), "Incorrect Error Message");
    }

    @Test
    public void testCreateEmployee() {
        Response response = empApiHelper.createEmployee();
        assertEquals("Status code is incorrect", HttpStatus.SC_CREATED, response.getStatusCode());
        assertEquals("Content type is incorrect", ContentType.JSON.toString(), response.getContentType().split(";")[0]);
        HashMap postCallData = response.jsonPath().get();
        String employeeId = (String) postCallData.get("id");
        LOG.info("Created Employee ID " + employeeId);
        Response getCallResponse = empApiHelper.getEmployee("users/" + employeeId);
        HashMap getCallData = getCallResponse.jsonPath().get();
        LOG.info("API received " + getCallData.size() + " data records");
        empApiHelper.deleteEmployeeById(Integer.parseInt(employeeId));
        verifyEmployeeData(getCallData);
    }

    @Test
    public void testGetAllEmployees() {
        Response response = empApiHelper.getEmployeeList("users?page=2");
        assertEquals("Status code is incorrect", HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("Content type is incorrect", ContentType.JSON.toString(), response.getContentType().split(";")[0]);
        HashMap employeeList = response.jsonPath().get();
        List lists = (List) employeeList.get("data");
        LOG.info("API received " + employeeList.size() + " data records");
        System.out.println(lists);
    }

    @Test
    public void testGetEmployee() {
        Response response = empApiHelper.getEmployee("users/2");
        assertEquals("Status code is incorrect", HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("Content type is incorrect", ContentType.JSON.toString(), response.getContentType().split(";")[0]);
        HashMap<String, Object> postCallData = response.jsonPath().get();
        HashMap hashMap = (HashMap) postCallData.get("data");
        System.out.println(hashMap);
    }

    @Test
    public void testUpdateEmployee() {
        List updateList;
        updateList = empApiHelper.getUpdatedJsonDataSet((List) empApiHelper.getEmployeeList("users?page=2").jsonPath().get("data"), "Editmichael.lawson@reqres.ie", "LawsonEdit");
        if (updateList.size() == 0) {
            empApiHelper.createEmployee();
            updateList = empApiHelper.getUpdatedJsonDataSet((List) empApiHelper.getEmployeeList("users?page=2").jsonPath().get("data"), "Editmichael.lawson@reqres.ie", "LawsonEdit");
        }
        int templateDetailId = (int) ((HashMap) updateList.get(0)).get("id");
        System.out.println(templateDetailId);
        System.out.println(updateList.get(0));
        Response updateCallResponse = requestSpecification.contentType(ContentType.JSON).body(updateList.get(0)).when().post("users");
        assertEquals("Status code is incorrect", HttpStatus.SC_CREATED, updateCallResponse.getStatusCode());
    }

    @Test
    public void testDeleteEmployee() {
        Response response = empApiHelper.deleteEmployeeById(2);
        assertEquals("Status code is incorrect", HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("Content type is incorrect", ContentType.JSON.toString(), response.getContentType().split(";")[0]);
        //   HashMap<String, Object> postCallData = response.jsonPath().get();
    }

    private void verifyEmployeeData(HashMap responseList) {
        assertEquals("Employee Name not matching", String.valueOf(responseList.get("name")), ("Damithc"));
        assertEquals("Job is  not matching", String.valueOf(responseList.get("job")), ("qae"));
    }
}
