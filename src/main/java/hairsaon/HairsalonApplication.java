package hairsaon;

import hairsaon.security.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class HairsalonApplication {
    private int maxUploadSizeInMb = 1 * 1024 * 1024; // 1 MB

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/service/*");
        registrationBean.addUrlPatterns("/client/*");
        registrationBean.addUrlPatterns("/master/*");


        return registrationBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(HairsalonApplication.class, args);
    }



}
