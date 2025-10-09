import java.util.*;
import java.io.*;

public class Clinic {
    // Initialisation
    private String clinicName;
    private final List<Pet> pets;
    private int registeredPetCount = 0;

    // File names for storing and loading data
    private String clinicDetails = "ClinicsDetails.txt";
    private String petDetails = "PetDetails.txt";

    // Constructor
    public Clinic() {
        this.clinicName = "Ulster University's Pet Clinic";
        this.pets = new ArrayList<>();
    }

    // Return the Clinic's name
    public String getClinicName() {
        return clinicName;
    }

    public int getRegisteredPetCount() {
        return registeredPetCount;
    }

    public void incrementRegisteredPetCount() {
        registeredPetCount++;
    }

    public void decreaseRegisteredPetCount() {
        registeredPetCount--;
    }

    // Save data to file
    public void saveData() {
        System.out.println("Saving clinic and pet details to " + getClinicName());
        saveClinic();
        savePets();
    }

    // Load data from file
    public void loadData() {
        loadClinicData();
        loadPetData();
    }

    // Save clinic details to file specified in the clinicDetails primitive
    private void saveClinic() {
        try (PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(clinicDetails)))) {
            writeFile.println(clinicName); // Save clinic name
            writeFile.println(pets.size()); // Save the number of registered animals
        } catch (IOException e) {
            System.out.println("Failed to write to " + clinicDetails + ": " + e.getMessage());
        }
    }

    // Save pet details to file specified in the petDetails primitive
    private void savePets() {
        try (PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(petDetails)))) {
            for (Pet pet : pets) {

                if (pet instanceof Cat) { // Check if the pet is an instance of the class; Cat
                    Cat cat = (Cat) pet; // Cast to cat, so that we have the Cat subclass methods (getBreed())
                    writeFile.println(cat.getPetType() + "," + cat.getName() + "," + cat.getAge() + "," + cat.getColour() + "," + cat.getWeight() + "," + cat.getBreed() + "," + cat.getLastVisit());


                } else if (pet instanceof Dog) { // Check if the pet is an instance of the class; Dog
                    Dog dog = (Dog) pet; // Cast to dog, so that we have the Dog subclass methods (getBreed())
                    writeFile.println(dog.getPetType() + "," + dog.getName() + "," + dog.getAge() + "," + dog.getColour() + "," + dog.getWeight() + "," + dog.getBreed() + "," + dog.getLastVisit());
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to write to " + petDetails + "! Error message: " + e.getMessage());
        }
    }

    // Load clinic details
    private void loadClinicData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(clinicDetails))) {
            String readClinic = reader.readLine();
            if (readClinic != null) {
                this.clinicName = readClinic;
                String petCount = reader.readLine();
                if (petCount != null) {
                    try {
                        registeredPetCount = Integer.parseInt(petCount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid format for pet count in " + clinicDetails + "!" + " Make sure that only numbers are being recorded.");
                    }
                } else {
                    System.out.println("Pet count information is missing in " + clinicDetails + "!");
                }
            } else {
                System.out.println(clinicDetails + " is empty!");
            }
        } catch (IOException e) {
            System.out.println("Can't read from " + clinicDetails + "! Error message: " + e.getMessage());
        }
    }

    // Load pet details
    private void loadPetData() {
        pets.clear(); // Clear existing pets before loading
        try (BufferedReader reader = new BufferedReader(new FileReader(petDetails))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Ensure that all information types are present
                if (parts.length == 7) {
                    // Pet primitives
                    String petType = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String colour = parts[3];
                    double weight = Double.parseDouble(parts[4]);
                    String breed = parts[5];
                    String lastVisit = parts[6];

                    // Check if petType is valid, Cat or Dog
                    if (petType.equalsIgnoreCase("Cat")) {
                        pets.add(new Cat(petType, name, age, colour, weight, breed, lastVisit));
                    } else if (petType.equalsIgnoreCase("Dog")) {
                        pets.add(new Dog(petType, name, age, colour, weight, breed, lastVisit));
                    } else {
                        System.out.println("Unknown pet type found! Pet type: " + petType);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file " + petDetails + e.getMessage());
        }
    }

    // Add a pet to the array list
    public void addPet(Pet newPet) {
        if (newPet.getName().isEmpty() || newPet.getColour().isEmpty()) {
            System.out.println("Pet details have missing fields. Please try again.");
            return;
        }
        this.pets.add(newPet);
        System.out.println(newPet.getName() + " has been added to the Clinic's database.");
    }

    // Remove a pet from the array list
    public void removePet(String name) {
        // Initialise pet to be removed, set to null every time method is run
        Pet petToRemove = null;

        // Find the pet stored in the 'pets' arraylist by name
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                petToRemove = pet;
                break;
            }
        }
        // If pet is found, print out to the user and call the method to remove the pet, else print that the pet wasn't found
        if (petToRemove != null) {
            pets.remove(petToRemove);
            System.out.println(name + " has been removed from the Clinic's database.");
            decreaseRegisteredPetCount();
        } else {
            System.out.println("Pet with name '" + name + "' was not found in the database.");
        }
    }

    // Modify a pet in the array list
    public void modifyPet(String name) {
        Scanner scanner = new Scanner(System.in);
        boolean foundPet = false;

        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                foundPet = true;
                System.out.println("\nModify " + name + "'s details:");
                System.out.print(
                                "1. Pet Type\n" +
                                "2. Name\n" +
                                "3. Age\n" +
                                "4. Colour\n" +
                                "5. Weight\n" +
                                "6. Breed\n" +
                                "7. Cancel\n");

                switch (scanner.nextLine()) {
                    // Modify Pet Type
                    case "1":
                        System.out.print("Enter the updated pet type (eg. Cat or Dog): ");
                        String newPetType = scanner.nextLine();
                        System.out.println(name + "'s pet type has been set to " + newPetType + ".");
                        pet.setPetType(newPetType);
                        break;

                    // Modify Name
                    case "2":
                        System.out.print("Enter the updated name: ");
                        String newName = scanner.nextLine();
                        System.out.println(name + "'s name has been set to " + newName + ".");
                        pet.setName(newName);
                        break;

                    // Modify Age
                    case "3":
                        System.out.print("Enter the updated age: ");
                        int newAge = scanner.nextInt();
                        System.out.println(name + "'s age has been set to " + newAge + ".");
                        pet.setAge(newAge);
                        break;

                    // Modify Colour
                    case "4":
                        System.out.print("Enter the updated colour: ");
                        String newColour = scanner.nextLine();
                        System.out.println(name + "'s colour has been set to " + newColour + ".");
                        pet.setColour(newColour);
                        break;

                    // Modify Weight
                    case "5":
                        System.out.print("Enter the updated weight: ");
                        double newWeight = scanner.nextInt();
                        System.out.println(name + "'s weight has been set to " + newWeight + ".");
                        pet.setWeight(newWeight);
                        break;

                    // Modify Breed
                    case "6":
                        System.out.print("Enter the updated breed: ");
                        String newBreed = scanner.nextLine();
                        System.out.println(name + "'s breed has been set to " + newBreed + ".");

                        if (pet instanceof Cat cat) { // Check if the pet is an instance of the class; Cat
                            // Cast to cat
                            cat.setBreed(newBreed);

                        } else if (pet instanceof Dog dog) { // Check if the pet is an instance of the class; Dog
                            // Cast to dog
                            dog.setBreed(newBreed);
                        }

                        break;

                    // Modify Last Visit
                    case "7":
                        System.out.print("Enter the updated visit date (DD/MM/YY): ");
                        String newVisit = scanner.nextLine();
                        System.out.println(name + "'s last visit date has been set to " + newVisit + ".");

                        if (pet instanceof Cat cat) { // Check if the pet is an instance of the class; Cat
                            // Cast to cat
                            cat.setLastVisit(newVisit);

                        } else if (pet instanceof Dog dog) { // Check if the pet is an instance of the class; Dog
                            // Cast to dog
                            dog.setLastVisit(newVisit);
                        }
                        break;

                    // Exit
                    case "8":
                        break;
                }
            }
        }

        // If pet isn't found, print to user
        if (!foundPet) {
            System.out.println("Pet with name '" + name + "' not found. Ensure correct spelling and try again.");
        }
    }

    // Get the dominant pet colour for the report
    private String getDominantPetColour(List<String> colours) {
        // If there are no colours, cancel operation
        if (colours.isEmpty()) {
            return "";
        }

        // Initialisation
        String dominantPetColour = "";
        int highestCount = 0;

        // Count instances of colours
        for (int i = 0; i < colours.size(); i++) {
            String currentColour = colours.get(i);
            int count = 0;

            // Count occurrences of the current colour in the list
            for (String colour : colours) {
                if (colour.equals(currentColour)) {
                    count++;
                }
            }

            // Update dominant colour if the current colour's count is higher
            if (count > highestCount) {
                highestCount = count;
                dominantPetColour = currentColour;
            }
        }

        return dominantPetColour;
    }

    // Generate a report for dominant colours, and pet count in clinic
    public void getReport() {
        // Check that the pets list isn't empty
        if (pets.isEmpty()) {
            System.out.println("No pets are registered, unable to generate a report.");
            return;
        }

        // Initialisation
        int catCount = 0;
        int dogCount = 0;
        List<String> catColours = new ArrayList<>();
        List<String> dogColours = new ArrayList<>();

        // Search through the pet list and get their colours
        for (Pet pet : pets) {
            if (pet instanceof Cat) {
                catCount++;
                catColours.add(pet.getColour());
            } else if (pet instanceof Dog) {
                dogCount++;
                dogColours.add(pet.getColour());
            }
        }

        String dominantCatColour = getDominantPetColour(catColours);
        String dominantDogColour = getDominantPetColour(dogColours);

        // Print out report
        System.out.println("Clinic Name: " + clinicName + '\n' +
                "Total Cats Registered: " + catCount + '\n' +
                "Dominant Cat Colour: " + (dominantCatColour.isEmpty() ? "N/A" : dominantCatColour) + '\n' +
                "Total Dogs Registered: " + dogCount + '\n' +
                "Dominant Dog Colour: " + (dominantDogColour.isEmpty() ? "N/A" : dominantDogColour));
    }

    // Search for a Pet
    public void searchPet(String searchKey) {
        boolean found = false;
        System.out.println("\nSearch Results for '" + searchKey + "'");
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(searchKey) || pet.getColour().equalsIgnoreCase(searchKey)) {
                System.out.println(pet.speak());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pets found matching your search term. Please ensure correct spelling.");
        }
    }

    // Print out all pets stored
    public void viewPets() {
        if (this.pets.isEmpty()) {
            System.out.println("No pets found in the clinic.");
        } else {
            for (Pet pet : this.pets) {
                System.out.println(pet.getDetails());
            }
        }
    }
}

