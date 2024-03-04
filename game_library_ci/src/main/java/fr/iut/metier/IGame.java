package fr.iut.metier;

public interface IGame {
    public Loan getLoan();
    public boolean borrow(Member member);
    public boolean endLoan();

    
}
