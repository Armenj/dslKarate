package backend;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class karateRunner {

    @Test
    void testExample() {
        Results results = Runner.path("classpath:backend")
                .tags("@integration")
                .parallel(4);
//        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}