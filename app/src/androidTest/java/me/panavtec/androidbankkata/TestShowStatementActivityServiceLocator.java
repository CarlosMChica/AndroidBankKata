package me.panavtec.androidbankkata;

import me.panavtec.androidbankkata.account.BankAccount;

class TestShowStatementActivityServiceLocator extends ShowStatementActivityServiceLocator {

  private final BankAccount account;

  public TestShowStatementActivityServiceLocator(BankAccount account) {
    this.account = account;
  }

  @Override public BankAccount getAccount() {
    return account;
  }
}
