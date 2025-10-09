public class Dog extends Pet {
    // Initialisation
    private String breed;

    // Dog Constructor
    public Dog(String petType, String name, int age, String colour, double weight, String breed, String lastVisit) {
        super(petType, name, age, colour, weight, lastVisit);

        // Check is breed is empty
        if (breed.isEmpty()) {
            throw new IllegalArgumentException("Breed cannot be empty.");
        }
        this.breed = breed;
        formatBreed();
    }

    // Return the breed of the pet
    public String getBreed() {
        return this.formatString(breed);
    }

    // Set the breed of the pet
    public void setBreed(String breed) {

        // Check is breed is empty
        if (breed.isEmpty() || breed == null) {
            throw new IllegalArgumentException("Breed cannot be empty.");
        }
        this.breed = breed;
    }

    // Override speak method from Pet and print out animal information informally
    @Override
    public String speak() {
        return "Woof! I am " + this.getName() + ", a " + this.getAge() + " year old " + this.getBreed();
    }

    // Print out information about the Pet to the console
    public String getDetails() {
        return super.getDetails() + " | Breed: " + this.getBreed() + " | Last Visit: " + this.getLastVisit();
    }

    // Format breed, others don't need to be formatted as they already are in the Pet class
    private void formatBreed() {
        this.breed = formatString(this.breed);
    }

}
