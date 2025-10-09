import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {

    @Test
    void setBreed() {
        Cat catTest = new Cat("Cat", "Jim", 4, "Black", 8.4, "Tabby", "04/04/25");
        String expectedResult = "Tabby";
        String actualResult = catTest.getBreed();
        assertEquals(expectedResult, actualResult);
        System.out.println(actualResult);
    }
}