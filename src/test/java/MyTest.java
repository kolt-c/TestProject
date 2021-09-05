import framework.TestListener;
import framework.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class MyTest {

    private Logger logger = LoggerFactory.getLogger(MyTest.class);

    @Test
    public void sampleTest(){
        logger.info("Sample test started.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Sample test finished.");
    }
}
