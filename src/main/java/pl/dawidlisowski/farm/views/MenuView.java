package pl.dawidlisowski.farm.views;

public class MenuView {

    public static void printMenu() {
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

    public static void askForTheNameOfBarn() {
        System.out.println("Podaj nazwę stodoły: ");
    }

    public static void askForAnimalSpecies() {
        System.out.println("Gatunek: ");
    }

    public static void askForAnimalAge() {
        System.out.println("Wiek: ");
    }

    public static void askAboutVaccination() {
        System.out.println("Zaszczepiony?");
    }

    public static void askForAnimalId() {
        System.out.println("Podaj Id zwierzęcia: ");
    }
}
