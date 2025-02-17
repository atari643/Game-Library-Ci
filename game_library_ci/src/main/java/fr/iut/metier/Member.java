
package fr.iut.metier;

import java.util.ArrayList;
import java.util.List;

/**
 * member of library ; he/she may borrow game.
 *
 * @author  Colette Johnen
 */
public class Member implements IMember{
    /**
     *
     */
    private final String name;
    /**
     *
     */
    private List<Loan> myLoans;

    /**
     * @param valueName name of the created Member
     */
    public Member(final String valueName) {
        this.name = valueName;
        myLoans = new ArrayList<>();
    }
    /**
     *
    * @return name of the Member
    */
    public String getName() {
    return name;
}

/**
 *
 * @param l new loan to add at the list of current loans.
 */

    public void addLoan(final Loan l) {
        myLoans.add(l);
    }
/**
 *
 * @param l loan that is terminated.
 */
    public void stopLoan(final Loan l) {
        myLoans.remove(l);
    }


    @Override
    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<member> : ");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

}
