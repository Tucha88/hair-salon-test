package hairsaon;

import hairsaon.controller.MasterController;
import hairsaon.models.Master;
import hairsaon.repository.MasterRepository;
import hairsaon.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;

@EnableScheduling
@SpringBootApplication
public class HairsalonApplication {

    private final MasterRepository masterRepository;

    @Autowired
    public HairsalonApplication(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HairsalonApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/service/*");
        registrationBean.addUrlPatterns("/client/*");
        registrationBean.addUrlPatterns("/master/*");
        return registrationBean;
    }

    //@Scheduled(cron = "second, minute, hour, day of month, month, day(s) of week")
    @Scheduled(cron = "* 55 13 * * *")
    public void updateCalendar(){
        for (Master master : masterRepository.findAll()) {
            master.getAddressMaster().update();
            masterRepository.save(master);
        }
    }

}
