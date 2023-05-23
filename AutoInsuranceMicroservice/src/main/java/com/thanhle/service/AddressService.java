package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.Address;

public interface AddressService {
    Address saveAddress(Address address);
    List<Address> getAllAddresses();
}
