package krupkoillia.chesstracker.gateway.filter.supplier;

import krupkoillia.chesstracker.gateway.filter.UserIdFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.SimpleFilterSupplier;

public class UserIdFilterSupplier extends SimpleFilterSupplier {

    public UserIdFilterSupplier() {
        super(UserIdFilterFunctions.class);
    }
}
