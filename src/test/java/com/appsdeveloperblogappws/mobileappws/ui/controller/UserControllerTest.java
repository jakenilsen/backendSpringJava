package com.appsdeveloperblogappws.mobileappws.ui.controller;

import com.appsdeveloperblogappws.mobileappws.service.UserService;
import com.appsdeveloperblogappws.mobileappws.shared.dto.AddressDto;
import com.appsdeveloperblogappws.mobileappws.shared.dto.UserDto;
import com.appsdeveloperblogappws.mobileappws.ui.model.response.UserRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    UserDto userDto;

    final String USER_ID = "kghj2328sajdh723";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userDto = new UserDto();
        userDto.setFirstName("Jacob");
        userDto.setLastName("Nilsen");
        userDto.setEmail("test@test.com");
        userDto.setEmailVerificationStatus(Boolean.FALSE);
        userDto.setEmailVerificationToken(null);
        userDto.setUserId(USER_ID);
        userDto.setAddresses(getAddressesDto());
        userDto.setEncryptedPassword("sdc34535sd22");
    }

    @Test
    final void testGetUser() {
        when(userService.getUserByUserId(anyString())).thenReturn(userDto);

        UserRest userRest = userController.getUser(USER_ID);

        assertNotNull(userRest);
        assertEquals(USER_ID, userRest.getUserId());
        assertEquals(userDto.getFirstName(), userRest.getFirstName());
        assertTrue(userDto.getAddresses().size() == userRest.getAddresses().size());
    }

    private List<AddressDto> getAddressesDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setType("shipping");
        addressDto.setCity("Daegu");
        addressDto.setCountry("South Korea");
        addressDto.setPostalCode("ABC123");
        addressDto.setStreetName("123 Street name");

        AddressDto billingAddressDto = new AddressDto();
        billingAddressDto.setType("billing");
        billingAddressDto.setCity("Daegu");
        billingAddressDto.setCountry("South Korea");
        billingAddressDto.setPostalCode("ABC123");
        billingAddressDto.setStreetName("123 Street name");

        List<AddressDto> addresses = new ArrayList<>();
        addresses.add(addressDto);
        addresses.add(billingAddressDto);

        return addresses;
    }
}