package go.euro.challenges.bus.route;

import go.euro.challenges.bus.route.domain.BusLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TestDataProvider {

    public static List<BusLine> prepareMockBusRoutes(Integer... stations) {
        final List<BusLine> busLines = new ArrayList<>();
        busLines.add(createBusLine(1, createStations(stations)));
        return busLines;
    }

    public static BusLine createBusLine(Integer busLineId, Map<Integer, Integer> stations) {
        return new BusLine(busLineId, stations);
    }

    public static Map<Integer, Integer> createStations(Integer... stationIds) {
        final Map<Integer, Integer> stations = new HashMap<>();
        int i = 0;
        for (Integer stationId : stationIds) {
            stations.put(stationId, ++i);
        }
        return stations;
    }
}
