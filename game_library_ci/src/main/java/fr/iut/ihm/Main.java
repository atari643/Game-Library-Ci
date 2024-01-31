package fr.iut.ihm;


/**
 * @author  Bruno Méry modified by Colette Johnen
 */
public final class Main {

  /**
   * @param args
   */
  public static void main(final String[] args) {
    LibraryApp myLib = new LibraryApp();
    putExampleDataIn(myLib);
    myLib.run();
  }

  private static void putExampleDataIn(final LibraryApp lib) {
    String aliceName = "Alice";
    lib.addGame("7 Wonders");
    lib.addGame("Love Letter");
    lib.addGame("CLUEDO");
    lib.addGame("Dixit");
    lib.addGame("King of Tokyo");
    lib.addGame("Catan");
    lib.addGame("Monopolis");
    lib.addGame("UNO");

    lib.addMember(aliceName);
    lib.addMember("Bob");
    lib.addMember("Carol");
    lib.addMember("Dave");

    lib.addBorrowGame(aliceName, "Catan");
    lib.addBorrowGame(aliceName, "King of Tokyo");
    lib.addBorrowGame(aliceName, "Dixit");
    lib.addBorrowGame(aliceName, "Monopolis");
    lib.addBorrowGame(aliceName, "UNO");

  }

  private Main() {

  }
}
