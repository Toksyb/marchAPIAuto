package step_Definitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utililities.RequestBodyService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class JsonPlaceHolderStepDefs extends BaseSteps {
    Response responseForGetComment, responseForPostComment;
    RequestBodyService requestBodyService;


    @Given("service is up and running")
    public void service_is_up_and_running() {
        setHeadersWithContentType();
        setEndpointPath(serviceUrl);
        getCall();
        Response responseForGetService = getResponse();
        assertThat(responseForGetService.statusCode(), is(200));
    }

    @When("i search for {string} of a comment with a GET method")
    public void i_search_for_of_a_comment_with_a_get_method(String id) {

        setHeadersWithContentType();
        setEndpointPath(makeCommentEndpoint + id);
        getCall();
        responseForGetComment = getResponse();


    }

    @Then("i should get the correct {string}, {string}, {string} and {string} returned with status code of {int}")
    public void i_should_get_the_correct_and_returned_with_status_code_of(String id, String name, String email, String body, Integer sCode) {
        assertThat(responseForGetComment.statusCode(), is(sCode));
        assertThat(responseForGetComment.body().jsonPath().get("id"), is(Integer.parseInt(id)));
        assertThat(responseForGetComment.body().jsonPath().get("name"), is(equalTo(name)));
        assertThat(responseForGetComment.body().jsonPath().get("email"), is(equalTo(email)));
        assertThat(responseForGetComment.body().jsonPath().get("body"), is(equalTo(body)));
    }


    /////POST section


    @When("I create a new comment with the following details {string},{string}, {string} and {string},")
    public void i_create_a_new_comment_with_the_following_details_and(String postId, String name, String email, String body) {
        setHeadersWithContentType();
        setEndpointPath(makeCommentEndpoint);
        requestBodyService = new RequestBodyService();
        DocumentContext requestBody = loadJsonTemplate(MakeACommentPayload);
        requestBodyService.SetRequestBodyForComment(requestBody, postId, name, email, body);

        getPostCall();
        responseForPostComment = getResponse();


    }

    @Then("i should get the correct {string},{string}, {string} and {string} returned and with status code of {int}")
    public void i_should_get_the_correct_and_returned_and_with_status_code_of(String postId, String name, String email, String body, Integer sCode) {
        assertThat(responseForPostComment.statusCode(), is(sCode));
        assertThat(responseForPostComment.body().jsonPath().get("postId"), is(equalTo(postId)));
        assertThat(responseForPostComment.body().jsonPath().get("name"), is(equalTo(name)));
        assertThat(responseForPostComment.body().jsonPath().get("email"), is(equalTo(email)));
        assertThat(responseForPostComment.body().jsonPath().get("body"), is(equalTo(body)));
    }

    @When("I create a new comment with the following details {string},{string}, {string} and {string} for Users")
    public void iCreateANewCommentWithTheFollowingDetailsAndForUsers(String arg0, String arg1, String arg2, String arg3) {
        
    }

    @Then("i should get the correct {string},{string}, {string} and {string} returned and with status code of {int}  for users")
    public void iShouldGetTheCorrectAndReturnedAndWithStatusCodeOfForUsers(String arg0, String arg1, String arg2, String arg3, int arg4) {
    }
}
