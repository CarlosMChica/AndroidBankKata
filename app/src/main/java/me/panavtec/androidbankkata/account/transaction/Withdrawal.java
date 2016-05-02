package me.panavtec.androidbankkata.account.transaction;

import java.util.Date;

public class Withdrawal extends Transaction {
  public Withdrawal(int amount, Date date) {
    super(-amount, date);
  }
}
