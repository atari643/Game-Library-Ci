package fr.iut.ihm;

import fr.iut.metier.Game;
import fr.iut.metier.Loan;
import fr.iut.metier.IMember;
import fr.iut.metier.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author  Bruno Méry modified by Colette Johnen
 */
public class LibraryApp {
    /**   */
    private Map<String, Game> gAccess;
    /**  */
    private Map<String, IMember> mAccess;
   /**  */
    private Scanner scan;
    /** */
    private ArrayList<String> menuItems;
    /** */
    static final int TROIS = 3;
    /** */
    static final int QUATRE = 4;
    /** */
    static final int CINQ = 5;
    /** */
    static final int SIX = 6;
    /** */
    static final int SEVEN = 7;


    LibraryApp() {
        gAccess = new HashMap<>();
        mAccess = new HashMap<>();
        scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        menuItems = new ArrayList<>();
        menuItems.add("Quit");
        menuItems.add("List all games in the library");
        menuItems.add("List all IMenbers of the library");
        menuItems.add("List all loans");
        menuItems.add("Add new  game");
        menuItems.add("Add new member");
        menuItems.add("Borrow  game");
        menuItems.add("Return game");
    }

    final void addGame(final String name) {
        Game g = gAccess.get(name);
        if (g == null) {
            gAccess.put(name, new Game(name));
        }
        else {
            System.out.println("There is alrealdy a game having the name " + name + ".");
        }
    }

    final void addMember(final String name) {
        IMember m = mAccess.get(name);
        if (m == null) {
            mAccess.put(name, new Member(name));
        }
        else {
            System.out.println("There is alrealdy a member having the name " + name + ".");
        }
    }

    final void addBorrowGame(final String nameM, final String nameG) {
        Game g = gAccess.get(nameG);
        IMember m = mAccess.get(nameM);
        if ((g == null) || (m == null)) {
            return;
        }
        if (g.borrow(m)) {
            System.out.println("The loan of the game "+ nameG + " by " + nameM + " is a success.");
        } 
        else {
            System.out.println("The loan of the game "+ nameG + " by " + nameM + " fail.");
        }
    }

    final void removeLoan(final String nameG) {
        Game g = gAccess.get(nameG);
        if (g == null) {
            return;
        }
        if (g.endLoan()) {
            System.out.println("The return of the game "+ nameG + " is a success.");
        } 
        else {
            System.out.println("The return of the game "+ nameG + " fails.");
        }
    }

    final void run() {
        boolean quit = false;
        do {
            displayMenu();
            int choice = userNumericInput(0, menuItems.size() - 1,
                "Choose an action");
            quit = performAction(choice);
        } while (!quit);
    }

    private void displayMenu() {
        System.out.println("What do you want to do?");
        displayList(menuItems);
    }

    private void displayList(final ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("Giving up: no matches.");
            return;
        }
        for (int i = 0; i < list.size(); ++i) {
            System.out.println("" + i + ". " + list.get(i));
        }
    }

    private int userNumericInput(final int min, final int max,final String prompt) {
        int input = -1;
        do {
            System.out.format("%s.%nYour choice? [%d - %d]", prompt, min, max);
            System.out.println();
            input = scan.nextInt();
        } while (min > input || max < input);
        return input;
    }

    private String userStringInput(final String prompt) {
        String input;
        do {
            System.out.format("Please enter %s:", prompt);
            System.out.println();
            input = scan.next();
        } while (input.length() < 1);
        return input;
    }

    private ArrayList<String> searchListByPart(final String part,
        final ArrayList<String> list) {
        ArrayList<String> res = new ArrayList<>();
        for (String item : list) {
            if (item.contains(part)) {
                res.add(item);
            }
        }
        return res;
    }

    private ArrayList<String> searchMemberByName(final String partName) {
        Set<String> members = mAccess.keySet();
        ArrayList<String> memberNames = new ArrayList<>();
        for (String member : members) {
            memberNames.add(member);
        }
        return searchListByPart(partName, memberNames);
    }


    private ArrayList<String> searchGameByName(final String partName) {
        Set<String> games = gAccess.keySet();
        ArrayList<String> gameNames = new ArrayList<>();
        for (String game : games) {
            gameNames.add(game);
        }
        return searchListByPart(partName, gameNames);
    }

    private boolean performAction(final int choice) {
        boolean res = false;
        switch (choice) {
            case 0:
                res = true;
                break;
            case 1:
                displayAllGames();
                break;
            case 2:
                displayAllMembers();
                break;
            case TROIS:
                displayAllLoans();
                break;
            case QUATRE:
                performGameEntry();
                break;
            case CINQ:
                performMemberEntry();
                break;
            case SIX:
                performGameLoan();
                break;
            case SEVEN:
                performGameReturn();
                break;
            default:
                break;
        }
        return res;
    }

    private void performMemberEntry() {
        String name = userStringInput("the name of the new member");
        addMember(name);
    }


    private void performGameEntry() {
        String name = userStringInput("the name of a new Game");
        addGame(name);
    }

    private void performGameLoan() {
        String partName = userStringInput("part of the name of the member");
        ArrayList<String> members = searchMemberByName(partName);
        if (members.isEmpty()) {
            System.out.println("No member has a name containing "+ partName + ".");
            return;
        }
        int memberNumber = 0;
        if (members.size() > 1) {
            displayList(members);
            memberNumber = userNumericInput(0, members.size() - 1,
                "Which member?");
        }
        String member = members.get(memberNumber);
        partName = userStringInput("part of the name of the game");
        ArrayList<String> games = searchGameByName(partName);
        if (games.isEmpty()) {
            System.out.println("No game has a name containing "+ partName + ".");
            return;
        }
        int gameNumber = 0;
        if (games.size() > 1) {
            displayList(games);
            gameNumber = userNumericInput(0, games.size() - 1,
            "Which game?");
        }
        String game = games.get(gameNumber);
        addBorrowGame(member, game);
    }

    private void performGameReturn() {
        String partName = userStringInput("part of the name of the game");
        ArrayList<String> games = searchGameByName(partName);
        if (games.isEmpty()) {
            System.out.println("No game has a name containing "+ partName + ".");
            return;
        }
        int gameNumber = 0;
        if (games.size() > 1) {
            displayList(games);
            gameNumber = userNumericInput(0, games.size() - 1,
            "Which game?");
        }
        String game = games.get(gameNumber);
        removeLoan(game);
    }

    private void displayAllGames() {
        ArrayList<String> lg = new ArrayList<>();
        System.out.println("Beginning of display all games : ");
        for (String st : gAccess.keySet()) {
            lg.add(gAccess.get(st).toString());
        }
        displayList(lg);
        System.out.println("End of display all games.");
    }

    private void displayAllMembers() {
        ArrayList<String> lm = new ArrayList<>();
        System.out.println("Beginning of display all members : ");
        for (String st : mAccess.keySet()) {
            lm.add(mAccess.get(st).toString());
        }
        displayList(lm);
        System.out.println("End of display all members.");
    }

    private void displayAllLoans() {
        ArrayList<String> ll = new ArrayList<>();
        System.out.println("Beginning of display all Loans : ");
        Loan l;
        for (String st : gAccess.keySet()) {
            l = gAccess.get(st).getLoan();
            if (l != null) {
                ll.add(l.toString());
            }
        }
        displayList(ll);
        System.out.println("End of display all loans.");
    }

}
