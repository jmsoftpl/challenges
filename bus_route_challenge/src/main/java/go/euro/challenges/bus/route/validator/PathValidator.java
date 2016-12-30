package go.euro.challenges.bus.route.validator;

import go.euro.challenges.bus.route.domain.BusRouteDataPath;
import go.euro.challenges.bus.route.exception.InvalidFilePathException;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class PathValidator {

    public void validatePath(BusRouteDataPath path) {
        if (!path.isValid()) {
            throw new InvalidFilePathException(
                    format("Cannot read data from file: %s, please check path correctness, is it a file and it has read access rights", path.getPath()));
        }
    }
}
