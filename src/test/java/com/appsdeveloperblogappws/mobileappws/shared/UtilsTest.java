package com.appsdeveloperblogappws.mobileappws.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UtilsTest {

    @Autowired
    Utils utils;

    @BeforeEach
    void setUp() {
    }

    @Test
    final void testGenerateUserId() {
        String userId = utils.generateUserId(30);
        String userId2 = utils.generateUserId(30);

        assertNotNull(userId);
        assertNotNull(userId2);
        assertTrue(userId.length() == 30);
        assertNotEquals(userId, userId2);
    }

    @Test
    final void testHasTokenNotExpired() {
        String token = utils.generateEmailVerificationToken("5sijahe85hs2");
        assertNotNull(token);

        boolean hasTokenExpired = Utils.hasTokenExpired(token);
        assertFalse(hasTokenExpired);
    }

    @Test
    final void testHasTokenExpired() {
        String expiredToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0M0B0ZXN0LmNvbSIsImV4cCI6MTYxNDg1NDA3M30.qVD7ZqNzyxkbr9cZVfVUUn0Xmn2o-7hGO3gz4K4mu2SQnaV9iiSnlgPSrB9DHRRA2ZSFrATcEzx1zxK5LADBwg";

        boolean hasTokenExpired = Utils.hasTokenExpired(expiredToken);
        assertTrue(hasTokenExpired);
    }
}