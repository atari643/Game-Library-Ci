package fr.iut.metier;

public interface IMember {
    String getName();
    void addLoan(Loan l);
    void stopLoan(Loan l);
    String toString();
    
}