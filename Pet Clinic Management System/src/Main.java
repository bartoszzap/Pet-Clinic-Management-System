import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialise Scanner for user inputs
        Scanner scanner = new Scanner(System.in);

        // Clinic class
        Clinic clinic = new Clinic();

        // Load data stored on disk
        clinic.loadData();

        // While loop to run and control the user input of the menu
        boolean clinicRunning = true;

        while (clinicRunning) {

            // Console menu
            System.out.println("\n-- " + clinic.getClinicName() + " Console --");
            System.out.println("Total Registered Pets: " + clinic.getRegisteredPetCount());
            System.out.print(
                    "1. Add Pet\n" +
                    "2. Remove Pet\n" +
                    "3. Modify Pet\n" +
                    "4. View All Pets\n" +
                    "5. View Clinic Report\n" +
                    "6. Search for a Pet\n" +
                    "7. Save & Exit\n" +
                    "8. Exit without Saving\n" +
                    "----------------------------\n" +
                    "Choose an option: ");

            switch (scanner.nextLine()) {
                // Add Pet
                case "1":
                    System.out.println("Chosen option 1: Add Pet\n");

                    System.out.print("Enter Pet Type (Cat/Dog): ");
                    String petType = scanner.nextLine();
                    if (!petType.equalsIgnoreCase("cat") && !petType.equalsIgnoreCase("dog")) {
                        System.out.println("Invalid Pet Type! Must be Cat or Dog!");
                        break;
                    }

                    System.out.print("Enter Pet Name: ");
                    String petName = scanner.nextLine();

                    System.out.print("Enter Pet Age: ");
                    // Initialise Pet Age & Check if it's an integer
                    int petAge = 0;
                    try {
                        petAge = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number! Error: " + e.getMessage());
                        break;
                    }
                    scanner.nextLine();

                    System.out.print("Enter Pet Colour: ");
                    String petColor = scanner.nextLine();

                    System.out.print("Enter Pet Weight (KG): ");
                    // Initialise Pet Weight & Check if it's a double
                    double petWeight = 0.0;
                    try {
                        petWeight = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number! Error: " + e.getMessage());
                        break;
                    }
                    scanner.nextLine();

                    System.out.print("Enter Pet Breed: ");
                    String petBreed = scanner.nextLine();

                    System.out.print("Enter Pets Last Visit (DD/MM/YY): ");
                    String petLastVisit = scanner.nextLine();

                    // equalsIgnoreCase is used so that case sensitivity isn't an issue
                    if (petType.equalsIgnoreCase("Cat")) {
                        clinic.addPet(new Cat(petType, petName, petAge, petColor, petWeight, petBreed, petLastVisit));
                        clinic.incrementRegisteredPetCount();
                    } else if (petType.equalsIgnoreCase("Dog")) {
                        clinic.addPet(new Dog(petType, petName, petAge, petColor, petWeight, petBreed, petLastVisit));
                        clinic.incrementRegisteredPetCount();
                    }
                    break;

                // Remove Pet
                case "2":
                    System.out.println("Chosen option 2: Remove Pet\n");
                    System.out.print("Enter Pet Name: ");
                    clinic.removePet(scanner.nextLine());
                    break;

                // Modify Pet
                case "3":
                    System.out.println("Chosen option 3: Modify Pet\n");
                    System.out.print("Enter Pet's name: ");
                    clinic.modifyPet(scanner.nextLine());
                    break;

                // View All Pets
                case "4":
                    System.out.println("Chosen option 4: View All Pets\n");
                    System.out.println("Pets in the Clinic:");
                    clinic.viewPets();
                    break;

                // Print Clinic Report
                case "5":
                    System.out.println("Chosen option 5: View Clinic Report\n");
                    clinic.getReport();
                    break;

                // Search for a Pet
                case "6":
                    System.out.println("Chosen option 6: Search for a Pet\n");
                    System.out.print("Search for Pet by Colour or Name: ");
                    String searchPetName = scanner.nextLine();
                    clinic.searchPet(searchPetName);
                    break;

                // Save and Exit
                case "7":
                    System.out.println("Chosen option 7: Save & Exit\n");
                    clinic.saveData();
                    clinicRunning = false;
                    break;

                // Exit without Saving
                case "8":
                    System.out.println("Chosen option 8: Exit without Saving\n");
                    System.out.print("Are you sure you'd like to exit without saving? (Y/N): ");
                    if (scanner.nextLine().equalsIgnoreCase("y")) {
                        System.out.println("Terminating program...\n");
                        clinicRunning = false;
                    }
                    break;

                // Default for invalid inputs
                default:
                    System.out.println("Invalid Input!\n");
                    break;
            }


            // Ask if user wants to continue running the program
            if (clinicRunning) {
                System.out.print("Would you like to run another command? (Y/N): ");
                if (scanner.nextLine().equalsIgnoreCase("n")) {
                    System.out.println("Remember to save before exiting to prevent loss of progress! Are you sure you would like to exit? (Y/N)");
                    if (scanner.nextLine().equalsIgnoreCase("y"))
                        clinicRunning = false;
                    }
            }
        }
    }
}
