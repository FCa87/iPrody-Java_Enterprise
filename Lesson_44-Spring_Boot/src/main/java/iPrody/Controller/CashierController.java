package iPrody.Controller;

import iPrody.Service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashierController {

    private final Cashier cashier;

    @Autowired
    public CashierController(Cashier cashier) {
        this.cashier = cashier;
    }

    @PostMapping(value = "/pay", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greet(){
        return cashier.pay();
    }
}
