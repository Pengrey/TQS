package code;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class AppTest{

    private final String API_URL = "https://jsonplaceholder.typicode.com/todos";

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", API_URL).then().statusCode(200);
    }

    @Test
    public void whenRequestGetToDo_thenReturnToDo(){
        when().request("GET", API_URL+"/4").then().assertThat().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void whenRequestAllToDo_thenReturnAllToDO(){
        when().request("GET", API_URL).then().body("id", hasItems(198,199));
    }
}
