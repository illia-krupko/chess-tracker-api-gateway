package krupkoillia.chesstracker.gateway.config;

import krupkoillia.chesstracker.gateway.filter.supplier.UserIdFilterSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterConfiguration {

    @Bean
    UserIdFilterSupplier userIdFilterSupplier() {
        return new UserIdFilterSupplier();
    }

}
