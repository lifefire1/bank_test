package bank.server.configuration;

import bank.server.job.DailyJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class QuartzConfig {
    /**
     * Определяет детали задачи, которая будет запускаться ежедневно.
     *
     * @return Детали задачи для ежедневной работы.
     */
    @Bean
    public JobDetail dailyJobDetail() {
        return JobBuilder.newJob(DailyJob.class)
                .withIdentity("dailyJob")
                .withDescription("Invoke Daily Job")
                .storeDurably()
                .build();
    }

    /**
     * Определяет триггер, который запускает ежедневную задачу.
     *
     * @return Триггер для ежедневной задачи.
     */
    @Bean
    public Trigger dailyJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(dailyJobDetail())
                .withIdentity("dailyTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")) // Запускать каждые 30 секунд
                .build();
    }
}