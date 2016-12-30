package go.euro.challenges.bus.route.service;

import go.euro.challenges.bus.route.domain.BusLine;
import go.euro.challenges.bus.route.domain.BusRouteDataPath;
import go.euro.challenges.bus.route.validator.PathValidator;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileReaderTest {

    @Test
    public void shouldReadBusRoutes() throws Exception {
        // given
        final BusRouteDataPath busRouteDataPath = new BusRouteDataPath("src/test/resources/test-files/sampleData");
        final FileReader routeFileReader = new FileReader(busRouteDataPath, new PathValidator());

        // when
        final List<BusLine> busLines = routeFileReader.readBusRoutes();

        // then

        assertThat(busLines).isNotEmpty();
    }

}