package fr.iut.metier;

public interface IGame {
    public String getLoan();
    public boolean borrow(IMember member);
    public boolean endLoan();
    
}
