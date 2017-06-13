package hairsaon;

import hairsaon.models.Client;
import hairsaon.models.Master;
import hairsaon.models.RecordClient;
import hairsaon.models.classes_for_master.Record;
import hairsaon.myExtends.LightCalendar;
import hairsaon.myExtends.MyCalendar;
import hairsaon.repository.ClientRepository;
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
    private final ClientRepository clientRepository;

    @Autowired
    public HairsalonApplication(MasterRepository masterRepository, ClientRepository clientRepository) {
        this.masterRepository = masterRepository;
        this.clientRepository = clientRepository;
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
    @Scheduled(cron = "0 34 3 * * *")
    public void updateCalendar(){
        System.out.println(new MyCalendar().getTime());
        ArrayList <Master> allMasters = new ArrayList<Master>(masterRepository.findAll());
        for (Master master : allMasters) {
            master.getAddressMaster().update();
            System.out.println(master.getEmail());
            masterRepository.save(master);
        }

        ArrayList <Client> allClients = new ArrayList<Client>(clientRepository.findAll());
        for (Client client : allClients){
            for (int i = 0; i < client.getRecords().size(); i++) {
                RecordClient recordClient = client.getRecords().get(i);
                LightCalendar today = new LightCalendar(new MyCalendar());
                LightCalendar recordCalendar = recordClient.getCalendar();
                RecordClient record = client.getRecords().get(i);
                if (today.compareTo(recordCalendar)>0){
                    System.out.println("i: " + i);
                    System.out.println("Запись клиента " + record.getClient() + " на " + record.getCalendar() + " " + record.getStarTime() + " - удалена");
                    client.getRecords().remove(i);

                }else {
                    System.out.println("i: " + i);
                    System.out.println("Запись клиента " + record.getClient() + " на " + record.getCalendar() + " " + record.getStarTime() + " - НЕ удалена");

                }
            }
            clientRepository.save(client);
        }

    }

}
