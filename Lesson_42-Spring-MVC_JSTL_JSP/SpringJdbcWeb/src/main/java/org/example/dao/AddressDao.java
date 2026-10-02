package org.example.dao;

import org.example.model.Address;

import java.util.List;

public interface AddressDao extends Dao<Address> {
    List<Address> findByUserId(Integer userId);
}
