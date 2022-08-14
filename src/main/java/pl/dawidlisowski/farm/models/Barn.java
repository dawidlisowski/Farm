package pl.dawidlisowski.farm.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Barn {
    private int id;
    private String barnName;
    private List<Animal> animalsList;

    public Barn(String barnName) {
        this.barnName = barnName;
    }
}
