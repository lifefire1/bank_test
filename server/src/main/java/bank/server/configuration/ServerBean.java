package bank.server.configuration;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Конфигурационный класс, определяющий бины для серверной части приложения.
 */

@Configuration
@ComponentScan("bank.server.service")
public class ServerBean {

    /**
     * Определяет планировщик задач Quartz.
     *
     * @return Планировщик задач Quartz.
     * @throws SchedulerException если возникает ошибка при создании планировщика.
     */

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }

    /**
     * Определяет RestTemplate для выполнения HTTP запросов.
     *
     * @return RestTemplate для выполнения HTTP запросов.
     */

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
