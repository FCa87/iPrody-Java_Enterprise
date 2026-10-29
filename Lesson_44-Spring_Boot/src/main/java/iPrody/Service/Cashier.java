package iPrody.Service;

import iPrody.Model.TypeOfCash;

public interface Cashier {

    TypeOfCash getCash();

    default String pay(){
        return (int)(Math.random() * 1000000d) + " " + getCash().getFullName();
    }

}
