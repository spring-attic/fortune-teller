package io.spring.cloud.samples.fortuneteller.ui.services.fortunes;


import au.com.dius.pact.consumer.*;
import au.com.dius.pact.model.PactFragment;
import io.spring.cloud.samples.fortuneteller.ui.Application;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles({"pact"})
public class FortuneServicePactTest {

    @Autowired
    FortuneService fortuneService;

    @Rule
    public PactRule rule = new PactRule("localhost", 8080, this);

    @Pact(state = "FortuneState", provider = "FortuneService", consumer = "FortuneUi")
    public PactFragment createFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        PactDslJsonBody responseBody = new PactDslJsonBody()
                .numberType("id")
                .stringType("text");

        return builder.uponReceiving("a request for a random fortune")
                .path("/random")
                .method("GET")
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body(responseBody).toFragment();
    }

    @Test
    @PactVerification("FortuneState")
    public void runTest() {
        Fortune fortune = fortuneService.randomFortune();
        assertNotNull(fortune);
        assertThat(fortune.getId(), is(greaterThan(0L)));
        assertThat(fortune.getId(), is(not(equalTo(42L))));
        assertThat(fortune.getText(), not(isEmptyOrNullString()));
        assertThat(fortune.getText(), is(not(equalTo("Your future is unclear."))));
    }
}
