package com.example.studydynamicschedulerv1.controller;

import com.example.studydynamicschedulerv1.entity.JobSchedule;
import com.example.studydynamicschedulerv1.exception.ApiException;
import com.example.studydynamicschedulerv1.service.JobScheduleService;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batch")
public class JobScheduleController {

    private final JobScheduleService service;

    public JobScheduleController(JobScheduleService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<JobSchedule> search() {
        return service.search();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("registered-list")
    public List<JobSchedule> searchRegister() throws ApiException {
        return service.registeredList();
    }

    /*
        @RequestMapping(value = "/delete")
    public Response delete(JobSchedule.request.delete job){
        Response response = new Response();
        try{
            if(!jobService.deleteJob(new StdSchedulerFactory().getScheduler(), job)){
                response.setMessage(messagePop.getMessage("error.code.E0001"));
            }else{
                log.error("### job delete success !!! -> {}", new Gson().toJson(job));
            }
        }catch (SchedulerException e){
            log.error("### exception => {}", e.getMessage());
            response.setMessage(messagePop.getMessage("error.code.E0001"));
        }
        return response;
    }
     */

    @PostMapping("delete")
    public void delete(JobSchedule jobSchedule) throws ApiException {
        service.delete(jobSchedule);
    }

    @GetMapping("register/{id}")
    public void register(@PathVariable String id) throws ApiException, SchedulerException {
        service.register(id);
    }

    @PutMapping("update/{id}")
    public void update(@PathVariable String id, @RequestBody JobSchedule schedule) throws ApiException {
        service.update(id, schedule);
    }
}
