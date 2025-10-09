class Pet {

    // Initialisation
    protected String petType;
    protected String name;
    protected int age;
    protected String colour;
    protected double weight;
    protected String lastVisit;

    // Constructor
    public Pet(String petType, String name, int age, String colour, double weight, String lastVisit) {
        this.petType = petType;
        this.name = name;
        this.age = age;
        this.colour = colour;
        this.weight = weight;
        this.lastVisit = lastVisit;
        formatPetInformation();
    }

    // Return the pet type
    public String getPetType() {
        return petType;
    }

    // Return the pet name
    public String getName() {
        return name;
    }

    // Return the pet age
    public int getAge() {
        return age;
    }

    // Return the pet colour
    public String getColour() {
        return colour;
    }

    // Return the pet weight
    public double getWeight() {
        return weight;
    }

    // Return the pets last visit
    public String getLastVisit() { return lastVisit; }

    // Set pet type
    public void setPetType(String petType) {
        this.petType = petType;
    }

    // Set pet name
    public void setName(String name) {
        this.name = name;
    }

    // Set pet age
    public void setAge(int age) {
        this.age = age;
    }

    // Set pet colour
    public void setColour(String colour) {
        this.colour = colour;
    }

    // Set pet weight
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Set last visit
    public void setLastVisit(String date) { this.lastVisit = date; }

    // speak function placeholder to be modified in the subclasses
    public String speak() {
        return "Animal noise not supported.";
    }

    // Return the pets details
    public String getDetails() {
        return "Pet Type: " + this.petType + " | Name: " + this.name + " | Age: " + this.age + " | Colour: " + this.colour + " | Weight: " + this.weight;
    }

    // Format every word to start with a capital letter for data consistency
    protected String formatString(String stringToFormat) { // Changed visibility to protected
        // If string is empty, cancel operation
        if (stringToFormat == null || stringToFormat.isEmpty()) {
            return stringToFormat;
        }

        // try-catch block in case user doesn't input anything to prevent errors
        try {
            String[] words = stringToFormat.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (!words[i].isEmpty() && Character.isLowerCase(words[i].charAt(0))) {
                    words[i] = Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1);
                }
            }
            return String.join(" ", words);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("String cannot be empty!");
            return stringToFormat;
        }
    }

    // Method to format the name and colour attributes
    private void formatPetInformation() {
        this.petType = formatString(this.petType);
        this.name = formatString(this.name);
        this.colour = formatString(this.colour);
    }
}
