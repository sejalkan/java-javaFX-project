package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Regular tests for Epic Feature 1.
 * There should be only one test for US 1.1
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic1RegularTests {
    static Facade facade; // Creates Facade object

    @BeforeAll
    public static void setup() {
        facade.createUserAccount("john.doe@gmail.com", "John Doe", "UnknownPhantom897", "UnknownPhantom897","+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-1032");
        facade.createUserAccount("mary.jane@yahoo.com", "Mary Jane", "NonExistentPerson888", "NonExistentPerson888","+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-5698");
    }

    @Test
    public void userLoginTest() {
        String expectedValue = "User john.doe@gmail.com was logged in.";
        String actualValue = facade.userLogin("john.doe@gmail.com", "Password123");
        assertEquals(expectedValue, actualValue);

        expectedValue = "User mary.jane@yahoo.com was logged in";
        actualValue = facade.userLogin("mary.jane@yahoo.com", "NonExistentPerson888");
        assertEquals(expectedValue, actualValue);
    }
}
