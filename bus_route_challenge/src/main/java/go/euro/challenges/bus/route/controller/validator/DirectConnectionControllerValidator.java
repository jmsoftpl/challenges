package go.euro.challenges.bus.route.controller.validator;

import go.euro.challenges.bus.route.exception.BusStationValidationException;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class DirectConnectionControllerValidator {

    public void validate(Integer departureId, Integer arrivalId) {
        if (departureId == null || arrivalId == null) {
            throw new BusStationValidationException(format("Cannot process unspecified station ids, departureId: %s, arrivalId: %s", departureId, arrivalId));
        }
        if (departureId == arrivalId) {
            throw new BusStationValidationException("Cannot process the same departure and arrival station. Please provide different stations ids");
        }
    }
}
