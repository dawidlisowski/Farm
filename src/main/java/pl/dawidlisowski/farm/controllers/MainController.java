package pl.dawidlisowski.farm.controllers;

import pl.dawidlisowski.farm.models.Animal;
import pl.dawidlisowski.farm.models.Barn;
import pl.dawidlisowski.farm.models.FarmDatabase;
import pl.dawidlisowski.farm.views.MenuView;

import java.util.Scanner;


public class MainController {
    private FarmDatabase farmDatabase;
    private Scanner scanner;

    public MainController() {
        farmDatabase = new FarmDatabase();
        scanner = new Scanner(System.in);
    }

    public void startApp() {
        createMainLoop();
    }

    private void createMainLoop() {
        String userAnswer;
        do {
            MenuView.printMenu();
            userAnswer = getAnswerFromUser();
            parseUserAnswer(userAnswer);
        } while (!userAnswer.equals("exit"));
    }

    private String getAnswerFromUser() {
        return scanner.nextLine();
    }

    private void parseUserAnswer(String answerFromUser) {
        switch (answerFromUser) {
            case "1": {
                createBarn();
                break;
            }
            case "2": {
                createAnimal();
                break;
            }
            case "3": {
                farmDatabase.showBarns();
                break;
            }
            case "4": {
                farmDatabase.showBarnContent(pickBarn());
                break;
            }
            case "5": {
                farmDatabase.showAllAnimals();
                break;
            }
            case "6": {
                farmDatabase.deleteAnimal(pickAnimal());
                break;
            }
            case "7": {
                farmDatabase.deleteBarn(pickBarn());
                break;
            }
            case "8": {
                farmDatabase.showFiveOldest();
                break;
            }
            case "9": {
                farmDatabase.showFiveYoungest();
                break;
            }
            case "10": {
                farmDatabase.showVaccinated();
                break;
            }
            case "11": {
                farmDatabase.biggestBarn();
                break;
            }
            case "12": {
                farmDatabase.biggestSpecies();
                break;
            }
            case "exit": {
                break;
            }
        }
    }

    private int pickAnimal() {
        MenuView.askForAnimalId();
        return Integer.parseInt(getAnswerFromUser());
    }

    private String pickBarn() {
        MenuView.askForTheNameOfBarn();
        return getAnswerFromUser();
    }

    private void createBarn() {
        MenuView.askForTheNameOfBarn();
        String barnName = getAnswerFromUser();
        farmDatabase.addBarn(new Barn(barnName));
    }

    private void createAnimal() {
        MenuView.askForAnimalSpecies();
        String species = getAnswerFromUser();

        MenuView.askForAnimalAge();
        int age = Integer.parseInt(getAnswerFromUser());

        MenuView.askAboutVaccination();
        boolean vaccinated = false;
        if (getAnswerFromUser().equals("tak")) {
            vaccinated = true;
        }

        MenuView.askForTheNameOfBarn();
        String barnName = getAnswerFromUser();

        farmDatabase.addAnimal(new Animal(species, age, vaccinated, barnName));
    }
}
