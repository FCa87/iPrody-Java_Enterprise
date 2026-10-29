package iPrody.Config;

import iPrody.Service.Cashier;
import iPrody.Service.CashierDollarImpl;
import iPrody.Service.CashierRubleImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class CashierServiceAutoConfiguration {

    @Bean
    @ConditionalOnProperty(
            name = "app.cashusing",
            havingValue = "rub",
            matchIfMissing = true
    )
    public Cashier CashierRuble(){
        return new CashierRubleImpl();
    }

    @Bean
    @ConditionalOnProperty(
            name = "app.cashusing",
            havingValue = "dol"
    )
    public Cashier CashierDollar(){
        return new CashierDollarImpl();
    }

}
