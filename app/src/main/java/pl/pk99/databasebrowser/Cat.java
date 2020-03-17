package pl.pk99.databasebrowser;

public class Cat {
    private int id;
    private String name, breed, gender, microchipped, birthDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public String getMicrochipped() {
        return microchipped;
    }

    public String getBirthDate() {
        return birthDate;
    }


    public Cat(int id, String name, String breed, String birthDate, String gender, String microchipped) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.microchipped = microchipped;
        this.birthDate = birthDate;
    }

    public Cat(String name, String breed, String birthDate, String gender, String microchipped) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.microchipped = microchipped;
        this.birthDate = birthDate;
    }
}
