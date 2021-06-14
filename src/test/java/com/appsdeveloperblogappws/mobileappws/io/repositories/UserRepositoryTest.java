package com.appsdeveloperblogappws.mobileappws.io.repositories;

import com.appsdeveloperblogappws.mobileappws.io.entity.AddressEntity;
import com.appsdeveloperblogappws.mobileappws.io.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    static boolean recordsCreated = false;

    @BeforeEach
    void setUp() {
       if(!recordsCreated) {
           createRecords();
       }
    }

    @Test
    void testFindAllUsersWithConfirmedEmailAddress() {
        Pageable pageableRequest = PageRequest.of(1, 1);
        Page<UserEntity> page = userRepository.findAllUsersWithConfirmedEmailAddress(pageableRequest);

        assertNotNull(page);

        List<UserEntity> userEntities = page.getContent();
        assertNotNull(userEntities);
        assertTrue(userEntities.size() == 1);
    }

    @Test
    void testFindUserByFirstName() {
        String firstName = "Jacob";
        List<UserEntity> users = userRepository.findUserByFirstName(firstName);

        assertNotNull(users);
        assertTrue(users.size() == 2);

        UserEntity user = users.get(0);
        assertTrue(user.getFirstName().equals(firstName));
    }

    @Test
    void testFindUserByLastName() {
        String lastName = "Nilsen";
        List<UserEntity> users = userRepository.findUserByLastName(lastName);

        assertNotNull(users);
        assertTrue(users.size() == 2);

        UserEntity user = users.get(0);
        assertTrue(user.getLastName().equals(lastName));
    }

    @Test
    void testFindUserByKeyword() {

        // case sensitive
        String keyword = "Ja";
        List<UserEntity> users = userRepository.findUserByKeyword(keyword);

        assertNotNull(users);
        assertTrue(users.size() == 2);

        UserEntity user = users.get(0);
        assertTrue(user.getLastName().contains(keyword) || user.getFirstName().contains(keyword));
    }

    @Test
    void testFindUserFirstNameAndLastNameByKeyword() {

        // case sensitive
        String keyword = "co";
        List<Object[]> users = userRepository.findUserFirstNameAndLastNameByKeyword(keyword);

        assertNotNull(users);
        assertTrue(users.size() == 2);

        Object[] user = users.get(0);

        assertTrue(user.length == 2);

        String userFirstName = String.valueOf(user[0]);
        String userLastName = String.valueOf(user[1]);

        assertNotNull(userFirstName);
        assertNotNull(userLastName);

        System.out.println("First name = " + userFirstName);
        System.out.println("Last name = " + userLastName);
    }

    @Test
    final void testUpdateUserEmailVerificationStatus() {
        boolean newEmailVerificationStatus = false;
        userRepository.updateUserEmailVerificationStatus(newEmailVerificationStatus, "12jds3");

        UserEntity storedUserDetails = userRepository.findByUserId("12jds3");

        boolean storedEmailVerificationStatus = storedUserDetails.getEmailVerificationStatus();

        assertTrue(storedEmailVerificationStatus == newEmailVerificationStatus);
    }

    private void createRecords() {
        // Prepare user
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Jacob");
        userEntity.setLastName("Nilsen");
        userEntity.setUserId("12jds3");
        userEntity.setEncryptedPassword("csda");
        userEntity.setEmail("test@test.com");
        userEntity.setEmailVerificationStatus(true);

        // Prepare addresses
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setType("shipping");
        addressEntity.setAddressId("asdas213");
        addressEntity.setCity("Daegu");
        addressEntity.setCountry("South Korea");
        addressEntity.setPostalCode("AEEQQQ");
        addressEntity.setStreetName("123 Streetname");

        List<AddressEntity> addresses = new ArrayList<>();
        addresses.add(addressEntity);

        userEntity.setAddresses(addresses);

        userRepository.save(userEntity);

        // Prepare user
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setFirstName("Jacob");
        userEntity2.setLastName("Nilsen");
        userEntity2.setUserId("12jds3qq");
        userEntity2.setEncryptedPassword("csda");
        userEntity2.setEmail("test2@test.com");
        userEntity2.setEmailVerificationStatus(true);

        // Prepare addresses
        AddressEntity addressEntity2 = new AddressEntity();
        addressEntity2.setType("shipping");
        addressEntity2.setAddressId("asdas213qq");
        addressEntity2.setCity("Daegu");
        addressEntity2.setCountry("South Korea");
        addressEntity2.setPostalCode("AEEQQQ");
        addressEntity2.setStreetName("123 Streetname");

        List<AddressEntity> addresses2 = new ArrayList<>();
        addresses.add(addressEntity2);

        userEntity2.setAddresses(addresses2);

        userRepository.save(userEntity2);

        recordsCreated = true;
    }
}