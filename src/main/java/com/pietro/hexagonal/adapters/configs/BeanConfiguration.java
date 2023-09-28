package com.pietro.hexagonal.adapters.configs;

import com.pietro.hexagonal.HexagonalApplication;
import com.pietro.hexagonal.core.ports.PessoaPersistencePort;
import com.pietro.hexagonal.core.services.PessoaServicePortImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Essa classe de configuração existe para fazer @Service dentro do PessoaServicePortImpl,
 * pois essa implementação está dentro do core e não pode ter @Service.
 * <p>
 * Também estamos utilizando o Bean do ModelMapper para realizar as conversões.
 **/

@Configuration
@ComponentScan(basePackageClasses = HexagonalApplication.class)
public class BeanConfiguration {

    @Bean
    PessoaServicePortImpl pessoaServicePortImpl(PessoaPersistencePort pessoaPersistencePort) {
        return new PessoaServicePortImpl(pessoaPersistencePort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
