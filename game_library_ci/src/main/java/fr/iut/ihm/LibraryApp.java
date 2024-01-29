package fr.iut.ihm;

import fr.iut.metier.Game;
import fr.iut.metier.Loan;
import fr.iut.metier.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author mery_labri
 */
public class LibraryApp {
    /**   */
    private Map<String, Game> gAccess;
    /**  */
    private Map<String, Member> mAccess;
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

    LibraryApp() {
        gAccess = new HashMap<>();
        mAccess = new HashMap<>();
        scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        menuItems = new ArrayList<>();
        menuItems.add("Quit");
        menuItems.add("List all games in the library");
        menuItems.add("Add new  game");
        menuItems.add("Add new member");
        menuItems.add("Borrow  game");
        menuItems.add("List all loans");
        menuItems.add("Return game");
    }

    final void addGame(final String name) {
        Game g = gAccess.get(name);
        if (g == null) {
            gAccess.put(name, new Game(name));
        }
    }

    final void addMember(final String name) {
        Member m = mAccess.get(name);
        if (m == null) {
            mAccess.put(name, new Member(name));
        }
    }

    final void addBorrowGame(final String nameM, final String nameG) {
        Game g = gAccess.get(nameG);
        Member m = mAccess.get(nameM);
        if ((g != null) && (m != null)) {
            g.borrow(m);
        }
    }

    final void removeLoan(final String nameG) {
        Game g = gAccess.get(nameG);
        if (g != null) {
            g.endLoan();
        }
    }

    final void run() {
        boolean quit = false;
        do {
            displayMenu();
            int choice = arrUserNumericInput(0, menuItems.size() - 1,
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

    private int arrUserNumericInput(final int min, final int max,
        final String prompt) {
        int input = -1;
        do {
            System.out.format("%s.%nYour choice? [%d - %d]", prompt, min, max);
            System.out.println();
            input = scan.nextInt();
        } while (min > input || max < input);
        return input;
    }

    private String arrUserStringInput(final String prompt) {
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



    private ArrayList<String> searchBoardGameByName(final String partName) {
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
                performGameEntry();
                break;
            case TROIS:
                performMemberEntry();
                break;
            case QUATRE:
                performGameLoan();
                break;
            case CINQ:
                displayAllLoans();
                break;
            case SIX:
                performGameReturn();
                break;
            default:
                break;
        }
        return res;
    }

    private void performMemberEntry() {
        String name = arrUserStringInput("the name of the new member");
        addMember(name);
    }


    private void performGameEntry() {
        String name = arrUserStringInput("the name of a new Game");
        addGame(name);
    }

    private void performGameLoan() {
        String partName = arrUserStringInput("part of the name of the member");
        ArrayList<String> members = searchMemberByName(partName);
        if (members.isEmpty()) {
            return;
        }
        int memberNumber = 0;
        if (members.size() > 1) {
            displayList(members);
            memberNumber = arrUserNumericInput(0, members.size() - 1,
                "Which member?");
        }
        String member = members.get(memberNumber);
        partName = arrUserStringInput("part of the name of the game");
        ArrayList<String> games = searchBoardGameByName(partName);
        if (games.isEmpty()) {
            return;
        }
        int gameNumber = 0;
        if (games.size() > 1) {
            displayList(games);
            gameNumber = arrUserNumericInput(0, games.size() - 1,
            "Which game?");
        }
        String game = games.get(gameNumber);
        Member m = mAccess.get(member);
        Game g = gAccess.get(game);
        g.borrow(m);
        displayAllLoans();
    }

    private void displayAllLoans() {
        ArrayList<String> ll = new ArrayList<>();
        Loan l;
        for (String st : gAccess.keySet()) {
            l = gAccess.get(st).getLoan();
            if (l != null) {
                ll.add(l.toString());
            }
        }
        displayList(ll);
    }

    private void performGameReturn() {
        String partName = arrUserStringInput("part of the name of the game");
        ArrayList<String> games = searchBoardGameByName(partName);
        if (games.isEmpty()) {
            return;
        }
        int gameNumber = 0;
        if (games.size() > 1) {
            displayList(games);
            gameNumber = arrUserNumericInput(0, games.size() - 1,
            "Which game?");
        }
        String game = games.get(gameNumber);
        Game g = gAccess.get(game);
        g.endLoan();
    }

    private void displayAllGames() {
        ArrayList<String> lg = new ArrayList<>();
        for (String st : gAccess.keySet()) {
            lg.add(gAccess.get(st).toString());
        }
        displayList(lg);
    }
}
