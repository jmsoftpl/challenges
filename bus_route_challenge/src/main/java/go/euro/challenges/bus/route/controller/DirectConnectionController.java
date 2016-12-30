package go.euro.challenges.bus.route.controller;

import go.euro.challenges.bus.route.controller.dto.DirectConnection;
import go.euro.challenges.bus.route.controller.validator.DirectConnectionControllerValidator;
import go.euro.challenges.bus.route.service.DirectBusRouteInspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static go.euro.challenges.bus.route.util.BusRouteApplicationUtil.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(ROOT_API_PATH)
public class DirectConnectionController {

    private final DirectConnectionControllerValidator validator;
    private final DirectBusRouteInspector directBusRouteInspector;

    @Autowired
    public DirectConnectionController(DirectBusRouteInspector directBusRouteInspector, DirectConnectionControllerValidator validator) {
        this.directBusRouteInspector = directBusRouteInspector;
        this.validator = validator;
    }

    @RequestMapping(method = GET, path = FIND_DIRECT_CONNECTION_PATH)
    public DirectConnection findDirectConnection(@RequestParam(DEPARTURE_SID) Integer departureId, @RequestParam(ARRIVAL_SID) Integer arrivalId) {
        validator.validate(departureId, arrivalId);
        final boolean result = directBusRouteInspector.inspectDirectBusRoute(departureId, arrivalId);
        return new DirectConnection(departureId, arrivalId, result);
    }
}
