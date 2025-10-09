import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadTest {

    @Test
    void loadData() {
        Clinic clinic = new Clinic();
        System.out.println("Before loading data:");
        clinic.getReport();
        System.out.println("\nAfter loading data:");
        clinic.loadData();
        clinic.getReport();
    }
}