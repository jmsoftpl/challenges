package go.euro.challenges.bus.route.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class BusRouteDataPath {

    private final String path;

    @Autowired
    public BusRouteDataPath(@Value("${bus.route.file.path}") String path) {
        this.path = path;
    }

    public boolean isValid() {
        final File file = new File(path);
        return file.exists() && file.canRead() && file.isFile();
    }

    public String getPath() {
        return path;
    }
}
