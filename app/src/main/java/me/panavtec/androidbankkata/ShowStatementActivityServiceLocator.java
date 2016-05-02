package me.panavtec.androidbankkata;

import me.panavtec.androidbankkata.account.BankAccount;

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
    return null;
  }
}
