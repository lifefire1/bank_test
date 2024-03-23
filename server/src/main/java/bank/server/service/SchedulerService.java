package bank.server.service;

import bank.server.job.DailyJob;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Api(tags = "Scheduler Service", description = "Service for managing scheduler")
public class SchedulerService {
    private final Scheduler scheduler;
    private final JobDetail dailyJobDetail;
    private final Trigger dailyJobTrigger;

    private final DailyJob dailyJob = new DailyJob();

    @Autowired
    public SchedulerService(Scheduler scheduler, JobDetail dailyJobDetail, Trigger dailyJobTrigger) {
        this.scheduler = scheduler;
        this.dailyJobDetail = dailyJobDetail;
        this.dailyJobTrigger = dailyJobTrigger;
    }

    @PostConstruct
    @ApiOperation("Initialize scheduler")
    public void init(){
        try{
            scheduler.start();
            scheduler.scheduleJob(dailyJobDetail, dailyJobTrigger);
        }   catch (SchedulerException exception){
            log.error(exception.getMessage());
        }
    }

    @PreDestroy
    @ApiOperation("Shutdown scheduler")
    public void preDestroy(){
        try{
            scheduler.shutdown();
        }   catch (SchedulerException exception){
            log.error(exception.getMessage());
        }
    }

}

