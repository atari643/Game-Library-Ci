package fr.iut.metier;
import java.util.Date;

/**
 * record the current load of games by a member.
 * @author Colette Johnen
 */
public class Loan {
    /**   */
    private final IGame borrowedGame;
    /**   */
    private final IMember borrower;
    /**  */
    private final Date borrowedOn;
   /**
    *
    * @param game
    * @param member
    */
    Loan(final IGame game, final IMember member) {
        borrowedGame = game;
        borrower = member; borrowedOn = new Date();
    }
    /**
     *
     * @return number of second since the game is borrowed.
     */
    final int getSecondsSinceLoanMade() {
        final long now = new Date().getTime();
        final int milli = 1000;
        return (int) ((now - borrowedOn.getTime()) / milli);
    }

    final IMember getBorrower() {
        return borrower;
    }

    final IGame getBorrowedGame() {
        return borrowedGame;
    }

    /**
    */
    void destruction() {
        borrower.stopLoan(this);
    }

    @Override
    public final String toString() {
        if (borrowedGame == null) {
            return "";
        }
        String st = borrower.toString();
        st = st.concat(" has borrowed ");
        st = st.concat(borrowedGame.toString());
        st = st.concat(" since ");
        st = st.concat(borrowedOn.toString());
        return st;   }

}
