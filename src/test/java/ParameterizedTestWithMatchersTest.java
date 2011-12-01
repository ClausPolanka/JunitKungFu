import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class ParameterizedTestWithMatchersTest {
    private int value;
    private Matcher expectedValue;

    @Parameters
    public static Collection data() {
        return asList(new Object[][]{
            {1, greaterThan(0)},
            {0, not(greaterThan(1))},
            {2, allOf(greaterThan(1), lessThan(3))},
            {4, allOf(greaterThan(1), lessThan(3))},
        });
    }

    public ParameterizedTestWithMatchersTest(int value, Matcher expectedValue) {
        this.value = value;
        this.expectedValue = expectedValue;
    }

    @Test
    public void valueShouldMatchExpectations() {
        assertThat(describeExpectations(), expectedValue.matches(value), is(true));
    }

    private String describeExpectations() {
        return value + " should be " + expectedValue;
    }
}
