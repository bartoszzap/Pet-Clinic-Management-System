import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveTest {

    @Test
    void saveData() {
        Clinic clinic = new Clinic();
        Dog dogTest = new Dog("Dog", "Jack", 6, "White", 6.4, "Jack Russel",
                "12/04/25");
        clinic.addPet(dogTest);
        clinic.saveData();
    }
}