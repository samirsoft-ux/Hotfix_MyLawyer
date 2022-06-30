package com.acme.mylawyerbe.lawyer.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("lawyerMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ClientMapper clientMapper(){
        return new ClientMapper();
    }

    @Bean
    public AppointmentMapper appointmentMapper(){
        return new AppointmentMapper();
    }

    @Bean
    public ContactMapper contactMapper(){
        return new ContactMapper();
    }

    @Bean
    public FavoriteLawyerMapper favoriteLawyerMapper(){
        return new FavoriteLawyerMapper();
    }

    @Bean
    public NotificationMapper notificationMapper(){
        return new NotificationMapper();
    }

    @Bean
    public LawyerMapper lawyerMapper(){
        return new LawyerMapper();
    }

    @Bean
    public SpecialtyMapper specialtyMapper(){
        return new SpecialtyMapper();
    }

    @Bean
    public LawyerSpecialtyMapper lawyerSpecialtyMapper(){
        return new LawyerSpecialtyMapper();
    }
}
