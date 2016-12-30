package go.euro.challenges.bus.route.domain;

import java.util.Map;

public class BusLine {

    private final int id;
    // key - stationId, value - orderNum
    private final Map<Integer, Integer> stations;

    public BusLine(int id, Map<Integer, Integer> stations) {
        this.id = id;
        this.stations = stations;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Integer> getStations() {
        return stations;
    }

}
