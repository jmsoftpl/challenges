package go.euro.challenges.bus.route.service;

import go.euro.challenges.bus.route.domain.BusLine;
import go.euro.challenges.bus.route.domain.BusRouteDataPath;
import go.euro.challenges.bus.route.exception.FileReadException;
import go.euro.challenges.bus.route.validator.PathValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static go.euro.challenges.bus.route.util.BusRouteApplicationUtil.FILE_ITEM_SEPARATOR;
import static java.lang.Integer.valueOf;

@Service
public class FileReader {

    private final BusRouteDataPath path;
    private final PathValidator pathValidator;

    @Autowired
    public FileReader(BusRouteDataPath path, PathValidator pathValidator) {
        this.path = path;
        this.pathValidator = pathValidator;
    }

    public List<BusLine> readBusRoutes() {
        pathValidator.validatePath(path);

        final Scanner sc = createScanner();
        final List<BusLine> busLines = new ArrayList<>();
        final int busRoutes = valueOf(sc.nextLine());

        for (int i = 0; i < busRoutes; ++i) {
            final String[] line = sc.nextLine().split(FILE_ITEM_SEPARATOR);
            final int busLineId = valueOf(line[0]);
            final Map<Integer, Integer> stations = new HashMap<>();
            for (int j = 1; j < line.length; ++j) {
                stations.put(valueOf(line[j]), j);
            }
            busLines.add(new BusLine(busLineId, stations));
        }
        return busLines;

    }

    private Scanner createScanner() {
        try {
            return new Scanner(new FileInputStream(path.getPath()));
        } catch (FileNotFoundException e) {
            throw new FileReadException(e.getMessage());
        }
    }
}
