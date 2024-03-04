package fr.iut.metier;

/**
 *
 * @author Colette Johnen
 */
public class Game implements IGame{
    /**
     *
     */
    private final String name;
    /**
     *
     */
    private Loan myLoan = null;

    /**
     * @param valueName is the name of the game.
     */
    public Game(final String valueName) {
        this.name = valueName;
    }

    /**
     * @return the Loan object if the game is borrowed.
    */
    public String getLoan() {
        return myLoan.toString();
    }

    /**
     *
     * @param member member that borrows the game.
     * @return true if the borrowing has been done.
     */
    public final boolean borrow(final Member member) {
        if (myLoan != null) {
            return false;
        }
        if (member == null) {
            return false;
        }
        myLoan = new Loan(this, member);
        member.addLoan(myLoan);
        return true;
    }

     /**
     *
     * @return true if the return is done correctly
     */
    public final boolean endLoan() {
        if (myLoan == null) {
            return false;
        }
        myLoan.destruction();
        myLoan = null;
        return true;
    }

    /**
     * @return name of game
     */
    public String getName() {
        return name;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<game> : ");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }
}
