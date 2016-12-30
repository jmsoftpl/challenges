package go.euro.challenges.bus.route.domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import java.io.File;

import static java.io.File.separator;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BusRouteDataPathTest {

    public static final String ROOT_TMP_DIR = "tmp";
    public static final String TMP_SUB_DIR = "subfolder";
    public static final String TEMP_FILE = "tempFile";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private File tempFile;
    private File tempSubFolder;

    @Before
    public void setUp() throws Exception {
        tempSubFolder = temporaryFolder.newFolder(ROOT_TMP_DIR, TMP_SUB_DIR);
        tempFile = temporaryFolder.newFile(ROOT_TMP_DIR + separator + TMP_SUB_DIR + separator + TEMP_FILE);
    }

    @Test
    public void shouldValidatePath() {
        // given
        final BusRouteDataPath busRouteDataPath = new BusRouteDataPath(tempFile.getPath());

        // when
        final Boolean valid = busRouteDataPath.isValid();

        // then
        assertThat(valid).isTrue();
    }

    @Parameters(method = "invalidPaths")
    @Test
    public void fakePathsShouldBeInvalid(boolean flushPathName, String fakePath) {
        // given
        final BusRouteDataPath busRouteDataPath = new BusRouteDataPath(preparePath(flushPathName, fakePath));

        // when
        final Boolean valid = busRouteDataPath.isValid();

        // then
        assertThat(valid).isFalse();
    }

    private String preparePath(boolean flushPathName, String fakePath) {
        if (flushPathName) {
            return fakePath;
        } else {
            return tempSubFolder.getPath() + fakePath;
        }
    }

    private Object[] invalidPaths() {
        return new Object[]{
                new Object[]{true, ""},
                new Object[]{false, separator + "fakeFolder" + separator + "fileName"},
                new Object[]{false, separator + TEMP_FILE + "xyz"}
        };
    }
}