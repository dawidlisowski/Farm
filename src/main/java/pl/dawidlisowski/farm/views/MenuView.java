package pl.dawidlisowski.farm.views;

import pl.dawidlisowski.farm.models.Animal;
import pl.dawidlisowski.farm.models.Barn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    private Scanner scanner;

    public MenuView() {
        scanner = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println("1 - Dodaj stodołę" +
                "\n2 - Dodaj zwierzę" +
                "\n3 - Wyświetl stodoły" +
                "\n4 - Wyświetl zawartość stodoły" +
                "\n5 - Wyświetl wszystkie zwierzęta" +
                "\n6 - Usuń zwierzę" +
                "\n7 - Usuń stodołę" +
                "\n8 - Wyświetl 5 najstarszych" +
                "\n9 - Wyświetl 5 najmłodszych" +
                "\n10 - Wyświetl zaszczepione" +
                "\n11 - Najwieksza stodoła" +
                "\n12 - Najliczniejszy gatunek" +
                "\nexit - Wyjdź");
    }

    public String getAnswerFromUser() {
        return scanner.nextLine();
    }

    public Barn createBarn() {
        System.out.println("Nazwa: ");
        String name = getAnswerFromUser();
        return new Barn(name);
    }

    public Animal createAnimal() {
        System.out.println("Gatunek: ");
        String species = getAnswerFromUser();

        System.out.println("Wiek: ");
        int age = Integer.parseInt(getAnswerFromUser());

        System.out.println("Zaszczepiony?");
        boolean vaccinated = false;
        if (getAnswerFromUser().equals("tak")) {
            vaccinated = true;
        }

        System.out.println("Do której stodoły?");
        String barn = getAnswerFromUser();
        return new Animal(species, age, vaccinated, barn);
    }

    public String pickBarn() {
        System.out.println("Podaj nazwę stodoły: ");
        return getAnswerFromUser();
    }

    public int pickAnimal() {
        System.out.println("Podaj Id zwierzęcia: ");
        return Integer.parseInt(getAnswerFromUser());
    }
}
