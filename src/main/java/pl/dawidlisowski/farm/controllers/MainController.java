package pl.dawidlisowski.farm.controllers;

import pl.dawidlisowski.farm.models.FarmDatabase;
import pl.dawidlisowski.farm.views.MenuView;


public class MainController {
    private MenuView menuView;
    private FarmDatabase farmDatabase;

    public MainController() {
        menuView = new MenuView();
        farmDatabase = new FarmDatabase();
    }

    public void startApp() {
        createMainLoop();
    }

    private void createMainLoop() {
        String userAnswer;
        do {
            menuView.printMenu();
            userAnswer = menuView.getAnswerFromUser();
            parseUserAnswer(userAnswer);
        } while (!userAnswer.equals("exit"));
    }

    private void parseUserAnswer(String answerFromUser) {
        switch (answerFromUser) {
            case "1" : {
                farmDatabase.addBarn(menuView.createBarn());
                break;
            }
            case "2" : {
                farmDatabase.addAnimal(menuView.createAnimal());
                break;
            }
            case "3" : {
                farmDatabase.showBarns();
                break;
            }
            case "4" : {
                farmDatabase.showBarnContent(menuView.pickBarn());
                break;
            }
            case "5" : {
                farmDatabase.showAllAnimals();
                break;
            }
            case "6" : {
                farmDatabase.deleteAnimal(menuView.pickAnimal());
                break;
            }
            case "7" : {
                farmDatabase.deleteBarn(menuView.pickBarn());
                break;
            }
            case "8" : {
                farmDatabase.showFiveOldest();
                break;
            }
            case "9" : {
                farmDatabase.showFiveYoungest();
                break;
            }
            case "10" : {
                farmDatabase.showVaccinated();
                break;
            }
            case "11" : {
                farmDatabase.biggestBarn();
                break;
            }
            case "12" : {
                farmDatabase.biggestSpecies();
                break;
            }
            case "exit" : {
                break;
            }
        }
    }
}
