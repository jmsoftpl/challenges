package go.euro.challenges.bus.route.controller;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static go.euro.challenges.bus.route.util.BusRouteApplicationUtil.*;
import static java.lang.Integer.valueOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(JUnitParamsRunner.class)
public class DirectConnectionControllerTest {

    private final String TEST_API_PATH = "/" + ROOT_API_PATH + "/" + FIND_DIRECT_CONNECTION_PATH;

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void shouldFindDirectConnection() throws Exception {
        // given
        final String depId = "2";
        final String arrId = "3";
        final MockHttpServletRequestBuilder request = get(TEST_API_PATH).param(DEPARTURE_SID, depId).param(ARRIVAL_SID, arrId);

        // when
        final ResultActions resultActions = mockMvc.perform(request);

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.dep_sid", is(equalTo(valueOf(depId)))))
                .andExpect(jsonPath("$.arr_sid", is(equalTo(valueOf(arrId)))))
                .andExpect(jsonPath("$.direct_bus_route", is(equalTo(true))));
    }

    @Parameters(method = "invalidStationsIds")
    @Test
    public void shouldReturnBadRequestIfInputIsInvalid(String depId, String arrId, String expectedMessage) throws Exception {
        // given
        final MockHttpServletRequestBuilder request = get(TEST_API_PATH).param(DEPARTURE_SID, depId).param(ARRIVAL_SID, arrId);

        // when
        final ResultActions resultActions = mockMvc.perform(request);

        // then
        resultActions.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error", is(equalTo(expectedMessage))));
    }

    private Object[] invalidStationsIds() {
        return new Object[]{
                new Object[]{1, 1, "Cannot process the same departure and arrival station. Please provide different stations ids"},
                new Object[]{1, "", "Cannot process unspecified station ids, departureId: 1, arrivalId: null"},
                new Object[]{"", 1, "Cannot process unspecified station ids, departureId: null, arrivalId: 1"},
                new Object[]{"", "", "Cannot process unspecified station ids, departureId: null, arrivalId: null"},
        };
    }
}
