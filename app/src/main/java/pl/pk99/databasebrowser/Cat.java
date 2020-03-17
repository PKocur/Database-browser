package pl.pk99.databasebrowser;

public class Cat {
    private int id;
    private String name, breed, gender, microchipped, birthDate;

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getBreed() {
        return breed;
    }

    String getGender() {
        return gender;
    }

    String getMicrochipped() {
        return microchipped;
    }

    String getBirthDate() {
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
}
