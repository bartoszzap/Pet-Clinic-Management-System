import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClinicTest {

    @Test
    void addPet() {
        Clinic clinic = new Clinic();
        Dog dogTest = new Dog("Dog", "Bob", 2, "Brown", 4.4, "Golden Retriever",
                "09/04/25");
        clinic.addPet(dogTest);
        String expectedResult = "Bob";
        String actualResult = dogTest.getName();
        assertEquals(expectedResult, actualResult);
    }
}