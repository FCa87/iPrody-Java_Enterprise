package iPrody.Service;

import iPrody.Model.TypeOfCash;

public class CashierRubleImpl implements Cashier{
    @Override
    public TypeOfCash getCash() {
        return TypeOfCash.R;
    }
}
