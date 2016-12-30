package go.euro.challenges.bus.route.controller.validator;

import go.euro.challenges.bus.route.exception.BusStationValidationException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(JUnitParamsRunner.class)
public class DirectConnectionControllerValidatorTest {

    private final DirectConnectionControllerValidator validator = new DirectConnectionControllerValidator();

    @Test
    public void shouldValidateDifferentStations() {
        // given
        final Integer departureId = 1;
        final Integer arrivalId = 2;

        // when
        final Throwable throwable = catchThrowable(() -> validator.validate(departureId, arrivalId));

        // then
        assertThat(throwable).isNull();
    }

    @Parameters(method = "invalidStations")
    @Test
    public void shouldNotValidateEmptyOrIdenticalStations(Integer departureId, Integer arrivalId, String errorMessage) {
        // given

        // when
        final Throwable throwable = catchThrowable(() -> validator.validate(departureId, arrivalId));

        // then
        assertThat(throwable).isNotNull()
                .isInstanceOf(BusStationValidationException.class)
                .hasMessage(errorMessage);
    }

    private Object[] invalidStations() {
        return new Object[]{
                new Object[]{1, 1, "Cannot process the same departure and arrival station. Please provide different stations ids"},
                new Object[]{1, null, "Cannot process unspecified station ids, departureId: 1, arrivalId: null"},
                new Object[]{null, 1, "Cannot process unspecified station ids, departureId: null, arrivalId: 1"},
                new Object[]{null, null, "Cannot process unspecified station ids, departureId: null, arrivalId: null"},
        };
    }
}