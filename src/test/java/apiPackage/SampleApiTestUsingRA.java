package apiPackage;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SampleApiTestUsingRA {
    @Test
    public void GetCommentForJsonPlaceHolder(){
        given().log().all().contentType(ContentType.JSON).
//                when().get("https://jsonplaceholder.typicode.com/comments/5").
                when().get("https://jsonplaceholder.typicode.com/comments/{id}", 5).
                then().log().all().statusCode(200).body("name", equalTo("vero eaque aliquid doloribus et culpa")).
                body("email", equalTo("Hayden@althea.biz")).
                body("body", equalTo("harum non quasi et ratione\ntempore iure ex voluptates in ratione\nharum architecto fugit inventore cupiditate\nvoluptates magni quo et"));
    }

    @Test
    public void PostACommentForJsonPlaceHolder(){
        HashMap<String, String> postBody = new HashMap<>();
        postBody.put("postId", "1");
        postBody.put("name", "My first comment");
        postBody.put("email", "lateef.abdulsalam@sydney.com");
        postBody.put("body", "I like the post make by my best friend");


        given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON).with().body(postBody).
        when().post("https://jsonplaceholder.typicode.com/comments").
                then().log().all().statusCode(201).
                body("name", equalTo("My first comment")).
                body("email", equalTo("lateef.abdulsalam@sydney.com")).
                body("body", equalTo("I like the post make by my best friend"));
    }

}
