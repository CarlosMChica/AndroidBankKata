package me.panavtec.androidbankkata;

import me.panavtec.androidbankkata.account.BankAccount;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ShowStatementActivityServiceLocatorShould {

  @Test public void provide_an_account_with_dependencies() {
    ShowStatementActivityServiceLocator serviceLocator = new ShowStatementActivityServiceLocator();

    BankAccount account = serviceLocator.getAccount();

    assertThat(account, notNullValue());
  }
}