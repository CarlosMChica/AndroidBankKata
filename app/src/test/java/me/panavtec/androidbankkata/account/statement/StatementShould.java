package me.panavtec.androidbankkata.account.statement;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import me.panavtec.androidbankkata.account.transaction.Deposit;
import me.panavtec.androidbankkata.account.transaction.Transaction;
import me.panavtec.androidbankkata.account.transaction.Withdrawal;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Calendar.JANUARY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatementShould {

  @Test public void generate_statement_lines_with_running_balance() {
    Statement statement = givenStatementWithTransactions();

    List<StatementLine> lines = statement.lines();

    assertThat(lines, is(linesFromTransactions()));
  }

  private List<StatementLine> linesFromTransactions() {
    return asList(new StatementLine(new Deposit(1000, date(2012, JANUARY, 10)), 1000),
        new StatementLine(new Deposit(2000, date(2012, JANUARY, 13)), 3000),
        new StatementLine(new Withdrawal(500, date(2012, JANUARY, 14)), 2500));
  }

  private Statement givenStatementWithTransactions() {
    return new Statement(transactions());
  }

  private List<Transaction> transactions() {
    return asList(new Deposit(1000, date(2012, JANUARY, 10)),
        new Deposit(2000, date(2012, JANUARY, 13)), new Withdrawal(500, date(2012, JANUARY, 14)));
  }

  private static Date date(int year, int month, int day) {
    return new GregorianCalendar(year, month, day).getTime();
  }
}