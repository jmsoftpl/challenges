package go.euro.challenges.bus.route.validator;

import go.euro.challenges.bus.route.domain.BusRouteDataPath;
import go.euro.challenges.bus.route.exception.InvalidFilePathException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PathValidatorTest {

    private static final String SIMPLE_PATH = "simplePath";
    private final PathValidator pathValidator = new PathValidator();
    @Mock
    private BusRouteDataPath busRouteDataPath;

    @Test
    public void shouldValidatePath() {
        // given
        given(busRouteDataPath.isValid()).willReturn(true);

        // when
        final Throwable throwable = catchThrowable(() -> pathValidator.validatePath(busRouteDataPath));

        // then
        assertThat(throwable).isNull();
    }

    @Test
    public void shouldNotValidatePath() {
        // given
        given(busRouteDataPath.isValid()).willReturn(false);
        given(busRouteDataPath.getPath()).willReturn(SIMPLE_PATH);

        // when
        final Throwable throwable = catchThrowable(() -> pathValidator.validatePath(busRouteDataPath));

        // then
        assertThat(throwable).isNotNull()
                .isInstanceOf(InvalidFilePathException.class)
                .hasMessage("Cannot read data from file: " + SIMPLE_PATH + ", please check path correctness, is it a file and it has read access rights");
    }
}