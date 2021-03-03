package com.appsdeveloperblogappws.mobileappws.service.impl;

import com.appsdeveloperblogappws.mobileappws.io.entity.AddressEntity;
import com.appsdeveloperblogappws.mobileappws.io.entity.UserEntity;
import com.appsdeveloperblogappws.mobileappws.io.repositories.AddressRepository;
import com.appsdeveloperblogappws.mobileappws.io.repositories.UserRepository;
import com.appsdeveloperblogappws.mobileappws.service.AddressService;
import com.appsdeveloperblogappws.mobileappws.shared.dto.AddressDto;
import com.appsdeveloperblogappws.mobileappws.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAddresses(String userId) {
        List<AddressDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(userId);


        if(userEntity == null) {
            throw new UsernameNotFoundException("User with ID: " + userId + " was not found");
        }

        Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
        for(AddressEntity addressEntity:addresses) {
            returnValue.add(modelMapper.map(addressEntity, AddressDto.class));
        }

        return returnValue;
    }

    @Override
    public AddressDto getAddressById(String addressId) {
        AddressDto returnValue = null;
        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);

        if(addressEntity == null) {
            throw new UsernameNotFoundException("User with ID: " + addressId + " was not found");
        }

        returnValue = new ModelMapper().map(addressEntity, AddressDto.class);

        return returnValue;
    }
}
