package com.wiredbrain.friends.service;

import com.wiredbrain.friends.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressService extends CrudRepository<Address, Integer> {
}
