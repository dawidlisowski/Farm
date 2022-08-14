package pl.dawidlisowski.farm.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Animal {
    private int id;
    private String species;
    private int age;
    private boolean vaccinated;
    private String barn;

    public Animal(String species, int age, boolean vaccinated, String barn) {
        this.species = species;
        this.age = age;
        this.vaccinated = vaccinated;
        this.barn = barn;
    }

    @Override
    public String toString() {
        if (vaccinated) {
            return id + " " + species + " " + age + " tak " + barn;
        } else {
            return id + " " + species + " " + age + " nie " + barn;
        }
    }
}
