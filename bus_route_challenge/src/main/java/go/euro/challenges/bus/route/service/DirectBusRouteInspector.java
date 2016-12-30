package go.euro.challenges.bus.route.service;

import go.euro.challenges.bus.route.domain.BusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

import static go.euro.challenges.bus.route.util.BusRouteApplicationUtil.FAKE_ORDER;

@Service
public class DirectBusRouteInspector {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final FileReader fileReader;
    private List<BusLine> busLines;

    @Autowired
    public DirectBusRouteInspector(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @PostConstruct
    public void initBusLines() {
        log.info("Read bus routes start");
        busLines = fileReader.readBusRoutes();
        log.info("Read bus routes end");
    }

    public boolean inspectDirectBusRoute(Integer departureId, Integer arrivalId) {
        boolean result = false;
        for (BusLine busLine : busLines) {
            final Map<Integer, Integer> stations = busLine.getStations();
            final Integer departureOrderOrFake = stations.getOrDefault(departureId, FAKE_ORDER);
            final Integer arrivalOrderOrFake = stations.getOrDefault(arrivalId, FAKE_ORDER);

            if (departureOrderOrFake != FAKE_ORDER && arrivalOrderOrFake != FAKE_ORDER && departureOrderOrFake < arrivalOrderOrFake) {
                result = true;
                break;
            }
        }
        return result;
    }

}
