package me.panavtec.androidbankkata.account.transaction;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WithdrawalShould {

  @Test public void have_a_negative_amount() {
    Withdrawal withdrawal = givenWithdrawalWith(anAmount());

    int amount = withdrawal.getAmount();

    assertThat(amount, is(aNegativeAmount()));
  }

  private Withdrawal givenWithdrawalWith(int amount) {
    return new Withdrawal(amount, null);
  }

  private int aNegativeAmount() {
    return -anAmount();
  }

  private int anAmount() {
    return 1000;
  }
}
