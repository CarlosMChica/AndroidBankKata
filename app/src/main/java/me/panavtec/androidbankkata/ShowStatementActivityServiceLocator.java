package me.panavtec.androidbankkata;

import me.panavtec.androidbankkata.account.BankAccount;
import me.panavtec.androidbankkata.account.Clock;
import me.panavtec.androidbankkata.account.statement.StatementFormatter;
import me.panavtec.androidbankkata.account.transaction.TransactionRepository;

class ShowStatementActivityServiceLocator {

  private static ShowStatementActivityServiceLocator instance;

  public static ShowStatementActivityServiceLocator getInstance() {
    if (instance == null) {
      instance = new ShowStatementActivityServiceLocator();
    }
    return instance;
  }

  static void setInstance(ShowStatementActivityServiceLocator serviceLocator) {
    instance = serviceLocator;
  }

  public BankAccount getAccount() {
    return new BankAccount(new TransactionRepository(), new StatementFormatter(), new Clock());
  }
}
