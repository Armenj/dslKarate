import com.intuit.karate.junit5.Karate;

public class karateRunner {
    @Karate.Test
    Karate testTugs(){
        return Karate.run("classpath:backend/cat/morpheus.feature");
    }
}
