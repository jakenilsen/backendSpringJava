package com.appsdeveloperblogappws.mobileappws.service;

import com.appsdeveloperblogappws.mobileappws.io.entity.AddressEntity;
import com.appsdeveloperblogappws.mobileappws.shared.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAddresses(String userId);
    AddressDto getAddressById(String addressId);
}
