package bank.server.configTest;

import bank.server.configuration.QuartzConfig;
import org.junit.jupiter.api.Test;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@SpringJUnitConfig(classes = QuartzConfig.class)
public class QuartzConfigTest {

    @Autowired
    private JobDetail dailyJobDetail;

    @Autowired
    private Trigger dailyJobTrigger;

    @Test
    void testDailyJobDetailNotNull() {
        assertNotNull(dailyJobDetail);
    }

    @Test
    void testDailyJobTriggerNotNull() {
        assertNotNull(dailyJobTrigger);
    }
}
