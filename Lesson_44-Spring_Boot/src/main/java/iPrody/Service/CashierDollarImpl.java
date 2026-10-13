package iPrody.Service;

import iPrody.Model.TypeOfCash;

public class CashierDollarImpl implements Cashier{
    @Override
    public TypeOfCash getCash() {
        return TypeOfCash.$;
    }
}
