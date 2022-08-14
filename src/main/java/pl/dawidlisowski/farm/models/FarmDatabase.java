package pl.dawidlisowski.farm.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FarmDatabase {
    private List<Animal> animalList;
    private List<String> lines;

    public FarmDatabase() {
        lines = new ArrayList<>();
    }

    public void addBarn(Barn barn) {
        File newBarnFile = new File(Config.DATABASE_PATH + barn.getBarnName() + ".txt");

        if (newBarnFile.exists()) {
            System.out.println("Podana stodoła istnieje");
            return;
        }

        try {
            lines = Files.readAllLines(Config.BARNS_FILE.toPath());
//            Files.write(Config.BARNS_FILE.toPath(), barnIdAndName.getBytes(), StandardOpenOption.APPEND);
            newBarnFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String barnIdAndName = lines.get(0) + " " + barn.getBarnName();
        lines.set(0, String.valueOf((Integer.parseInt(lines.get(0)) + 1)));
        lines.add(barnIdAndName);
        rewriteFile(Config.BARNS_FILE);
    }

    public void addAnimal(Animal animal) {
        String animalIdAndData = null;
        File barnFile = new File(Config.DATABASE_PATH + animal.getBarn() + ".txt");

        if (!barnFile.exists()){
            System.out.println("Podana stodoła nie istnieje");
            return;
        }
        try {
            lines = Files.readAllLines(Config.ANIMALS_FILE.toPath());
            if (animal.isVaccinated()) {
                animalIdAndData = lines.get(0) + " " + animal.getSpecies() + " " + animal.getAge() + " tak " + animal.getBarn();
            } else {
                animalIdAndData = lines.get(0) + " " + animal.getSpecies() + " " + animal.getAge() + " nie " + animal.getBarn();
            }
//            Files.write(Config.ANIMALS_FILE.toPath(), animalIdAndData.getBytes(), StandardOpenOption.APPEND);
            Files.write(barnFile.toPath(), (animalIdAndData + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.set(0, String.valueOf((Integer.parseInt(lines.get(0)) + 1)));
        lines.add(animalIdAndData);
        rewriteFile(Config.ANIMALS_FILE);
    }

    public void showBarns() {
        showFileContent(Config.BARNS_FILE);
    }

    public void showAllAnimals() {
        showFileContent(Config.ANIMALS_FILE);
    }

    private void showFileContent(File file) {
        readFile(file);

        if (lines.size() == 0) {
            System.out.println("Stodoła jest pusta");
        }
        for (String line : lines) {
            if (!Pattern.matches("\\d+", line)) {
                System.out.println(line);
            }
        }
    }

    public void showBarnContent(String barnName) {
        File barnFile = new File(Config.DATABASE_PATH + barnName + ".txt");

        if (!barnFile.exists()){
            System.out.println("Podana stodoła nie istnieje");
            return;
        }

        showFileContent(barnFile);
    }

    public void deleteAnimal(int animalId) {
        readFile(Config.ANIMALS_FILE);
        String animalToDelete = null;

        for (String line : lines) {
            String[] strings = line.split(" ");
            if (Integer.parseInt(strings[0]) == animalId) {
                animalToDelete = line;
            }
        }

        lines.remove(animalToDelete);

        rewriteFile(Config.ANIMALS_FILE);

//        StringBuilder newLines = new StringBuilder();
//
//        for (String line : lines) {
//            newLines.append(line).append("\r\n");
//        }
//
//        try {
//            Files.write(Config.ANIMALS_FILE.toPath(), newLines.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (animalToDelete != null) {
            deleteAnimalFromBarn(animalToDelete);
        }
    }

    private void deleteAnimalFromBarn(String animal) {
        String[] strings = animal.split(" ");
        File barnFile = new File(Config.DATABASE_PATH + strings[4] + ".txt");

        readFile(barnFile);

        lines.removeIf(s -> s.equals(animal));

        rewriteFile(barnFile);

//        StringBuilder newLines = new StringBuilder();
//
//        for (String line : lines) {
//            newLines.append(line).append("\r\n");
//        }
//
//        try {
//            Files.write(barnFile.toPath(), newLines.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void rewriteFile(File file) {
        StringBuilder newLines = new StringBuilder();

        for (String line : lines) {
            newLines.append(line).append("\r\n");
        }

        try {
            Files.write(file.toPath(), newLines.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<String> readFile(File file) {
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void deleteBarn(String barnName) {
        File barnFile = new File(Config.DATABASE_PATH + barnName + ".txt");
        String barnToDelete = null;

        if (!barnFile.exists()){
            System.out.println("Podana stodoła nie istnieje");
            return;
        }

        List<String> animalsToDelete = readFile(barnFile);

        readFile(Config.ANIMALS_FILE);

        lines.removeAll(animalsToDelete);

        rewriteFile(Config.ANIMALS_FILE);

        List<String> barnList = readFile(Config.BARNS_FILE);

        for (int i = 1; i < barnList.size(); i++) {
            String[] strings = barnList.get(i).split(" ");
            if (strings[1].equals(barnName)) {
                barnToDelete = barnList.get(i);
            }
        }

        barnList.remove(barnToDelete);

        rewriteFile(Config.BARNS_FILE);

        barnFile.delete();
    }

    public void showFiveOldest() {
        stringsToAnimalList();

        animalList.stream()
                .sorted((s, s1) -> Integer.compare(s1.getAge(), s.getAge()))
                .limit(5)
                .forEach(s -> System.out.println(s));
    }

    public List<Animal> stringsToAnimalList() {
        readFile(Config.ANIMALS_FILE);
        animalList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] strings = lines.get(i).split(" ");
            if (strings[3].equals("tak")) {
                animalList.add(new Animal(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]), true, strings[4]));
            } else {
                animalList.add(new Animal(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]), false, strings[4]));
            }
        }

        return animalList;
    }

    public void showFiveYoungest() {
        stringsToAnimalList();

        animalList.stream()
                .sorted((s, s1) -> Integer.compare(s.getAge(), s1.getAge()))
                .limit(5)
                .forEach(System.out::println);
    }

    public void showVaccinated() {
        stringsToAnimalList();

        for (Animal animal : animalList) {
            if (animal.isVaccinated()){
                System.out.println(animal);
            }
        }
    }

    public void biggestBarn() {
        stringsToAnimalList();

        animalList.stream()
                .collect(Collectors.groupingBy(s -> s.getBarn()))
                .values()
                .stream()
                .max((s, s1) -> Integer.compare(s.size(), s1.size()))
                .get()
                .stream()
                .findAny()
                .ifPresent(s -> System.out.println(s.getBarn()));

    }

    public void biggestSpecies() {
        stringsToAnimalList();

        animalList.stream()
                .collect(Collectors.groupingBy(s -> s.getSpecies()))
                .values()
                .stream()
                .max((s, s1) -> Integer.compare(s.size(), s1.size()))
                .get()
                .stream()
                .findAny()
                .ifPresent(s -> System.out.println(s.getSpecies()));
    }
}
