package go.euro.challenges.bus.route.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static go.euro.challenges.bus.route.TestDataProvider.prepareMockBusRoutes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(JUnitParamsRunner.class)
public class DirectBusRouteInspectorTest {

    @Mock
    private FileReader fileReader;

    @InjectMocks
    private DirectBusRouteInspector busRouteInspector;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Parameters(method = "stationsToInspect")
    @Test
    public void shouldInspectDirectBusRoute(Integer departureId, Integer arrivalId, boolean expectedResult) {
        // given
        given(fileReader.readBusRoutes()).willReturn(prepareMockBusRoutes(1, 3, 5, 7, 9, 11, 245, 6, 4));
        busRouteInspector.initBusLines();

        // when
        final boolean result = busRouteInspector.inspectDirectBusRoute(departureId, arrivalId);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private Object[] stationsToInspect() {
        return new Object[]{
                new Object[]{3, 5, true},
                new Object[]{1, 4, true},
                new Object[]{6, 4, true},
                new Object[]{11, 6, true},

                new Object[]{11, 3, false},
                new Object[]{33, 44, false},
                new Object[]{1, 44, false},
                new Object[]{33, 4, false},
        };
    }
}