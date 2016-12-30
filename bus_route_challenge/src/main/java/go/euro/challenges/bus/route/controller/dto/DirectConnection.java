package go.euro.challenges.bus.route.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import go.euro.challenges.bus.route.util.BusRouteApplicationUtil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static go.euro.challenges.bus.route.util.BusRouteApplicationUtil.ARRIVAL_SID;
import static go.euro.challenges.bus.route.util.BusRouteApplicationUtil.DEPARTURE_SID;
import static go.euro.challenges.bus.route.util.BusRouteApplicationUtil.DIRECT_BUS_ROUTE;

public class DirectConnection implements Serializable {

    @NotNull
    @JsonProperty(DEPARTURE_SID)
    private int departureId;

    @JsonProperty(ARRIVAL_SID)
    private int arrivalId;

    @JsonProperty(DIRECT_BUS_ROUTE)
    private boolean result;

    public DirectConnection(Integer departureId, Integer arrivalId, boolean result) {
        this.departureId = departureId;
        this.arrivalId = arrivalId;
        this.result = result;
    }

    public int getDepartureId() {
        return departureId;
    }

    public void setDepartureId(int departureId) {
        this.departureId = departureId;
    }

    public int getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(int arrivalId) {
        this.arrivalId = arrivalId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
