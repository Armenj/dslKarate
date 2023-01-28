package backend;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class karateRunner {

    @Karate.Test
    Karate testTags(){
        return Karate.run("classpath:backend").tags("@reqres", "@integration");
    }

    // альтернативный запуск тестов по тегам с использованием многопоточности
    @Test
    void testExample() {
        Results results = Runner.path("classpath:backend")
                .tags("@reqres", "@integration")
                .parallel(5);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}