package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.http.ContentType;
import groovy.lang.Sequence;

public class EmpApiTestHelper {
    RequestSpecification requestSpecification;

    public EmpApiTestHelper(RequestSpecification requestSpecfication) {
        this.requestSpecification = requestSpecfication;
    }

    public Response getEmployeeList(String param) {
        if (param.length() != 0) {
            return requestSpecification.given().when().get(param);
        } else {
            return requestSpecification.given().when().get();
        }
    }

    public Response createEmployee() {
        return requestSpecification.contentType(ContentType.JSON).body(createEmployeeJsonDataSet()).when().post("users");
    }

    public Map<String, String> createEmployeeJsonDataSet() {
        Map<String, String> employeeData = new HashMap<>();
        employeeData.put("name", "Damithc");
        employeeData.put("job", "qae");
        return employeeData;
    }

    public Response getEmployee(String param) {
        if (param.length() != 0) {
            return requestSpecification.given().when().get(param);
        } else {
            return requestSpecification.given().when().get();
        }
    }

    public Response deleteEmployeeById(int employeeId) {
        return RestAssured.given().when().delete(String.valueOf(employeeId));
    }

    public List getUpdatedJsonDataSet(List employeeList, String employeeEmail, String employeeLastName) {
        List<Object> updateList = new ArrayList<>();
        for (Object item : employeeList) {
            if (String.valueOf(((HashMap) item).get("first_name")).contains("Michael")) {
                ((HashMap) item).put("email", employeeEmail);
                ((HashMap) item).put("lastname", employeeLastName);
                updateList.add(item);
                break;
            }
        }
        return updateList;
    }

}
