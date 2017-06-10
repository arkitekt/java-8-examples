package chapter3;

import chapter5.Trader;
import chapter5.Transaction;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

/**
 * @author Olavi
 */
public class TransactionStreamsTest {

  private List<Transaction> createTransactions() {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = new ArrayList<>();

    transactions.add(new Transaction(brian, 2011, 300));
    transactions.add(new Transaction(raoul, 2012, 1000));
    transactions.add(new Transaction(raoul, 2011, 400));
    transactions.add(new Transaction(mario, 2012, 710));
    transactions.add(new Transaction(mario, 2012, 700));
    transactions.add(new Transaction(alan, 2012, 950));

    return transactions;
  }

  @Test
  public void testTransactionsInYear2011() throws Exception {
    List<Transaction> result = createTransactions().stream().filter(t -> t.getYear() == 2011).sorted(comparing(Transaction::getValue)).collect(toList());

    assertFalse(result.isEmpty());
    assertEquals(2, result.size());
    assertEquals(300, result.get(0).getValue());
    assertEquals(400, result.get(1).getValue());
  }

  @Test
  public void testFindUniqueCities() throws Exception {
    Set<String> cities = createTransactions().stream().map(t -> t.getTrader().getCity()).collect(toSet());

    assertThat(cities.size(), is(2));
    assertThat(cities, contains("Milan", "Cambridge"));
  }

  @Test
  public void testSortingAllCambridgeTradesByName() throws Exception {
    List<Trader> traders = createTransactions().stream()
      .filter(t -> t.getTrader().getCity().equalsIgnoreCase("Cambridge"))
      .map(Transaction::getTrader)
      .distinct()
      .sorted(comparing(Trader::getName))
      .collect(toList());

    assertFalse(traders.isEmpty());
    assertThat(traders.size(), is(3));
    assertThat(traders.get(0).getName(), is("Alan"));
    assertThat(traders.get(1).getName(), is("Brian"));
    assertThat(traders.get(2).getName(), is("Raoul"));
  }

  @Test
  public void testSortingAllCambridgeTradesByNameFromTheBook() throws Exception {
    List<Trader> traders = createTransactions().stream()
      .map(Transaction::getTrader)
      .filter(trader -> trader.getCity().equals("Cambridge"))
      .distinct()
      .sorted(comparing(Trader::getName)).collect(toList());

    assertFalse(traders.isEmpty());
    assertThat(traders.size(), is(3));
    assertThat(traders.get(0).getName(), is("Alan"));
    assertThat(traders.get(1).getName(), is("Brian"));
    assertThat(traders.get(2).getName(), is("Raoul"));
  }

  @Test
  public void testReturningAStringWithAllTraderNamesSortedAlphabetically() throws Exception {
    StringBuilder sb = new StringBuilder();
    createTransactions().stream().map(Transaction::getTrader).sorted(comparing(Trader::getName)).map(Trader::getName).distinct().forEach(n -> sb.append(n).append(" "));
    String result = sb.toString().trim();

    assertThat(result, is("Alan Brian Mario Raoul"));
  }

  @Test
  public void testReturningAStringWithAllTraderNamesSortedAlphabeticallyFromTheBook() throws Exception {
    String result = createTransactions().stream()
      .map(transaction -> transaction.getTrader().getName())
      .distinct()
      .sorted()
      .reduce("", (n1, n2) -> n1 + n2);

    assertThat(result, is("AlanBrianMarioRaoul"));

    String anotherResult = createTransactions().stream()
      .map(transaction -> transaction.getTrader().getName())
      .distinct()
      .sorted()
      .collect(joining());

    assertThat(result, is("AlanBrianMarioRaoul"));
  }

  @Test
  public void testAnyTradersBasedInMilan() throws Exception {
    Optional<Trader> trader = createTransactions().stream()
      .map(Transaction::getTrader)
      .filter(t -> t.getCity().equals("Milan"))
      .findAny();

    assertThat(trader.isPresent(), is(true));
  }

  @Test
  public void testFromTheBook() throws Exception {
    boolean anyTradersBasedMilan = createTransactions().stream()
      .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

    assertThat(anyTradersBasedMilan, is(true));
  }

  @Test
  @Ignore("because of the NPE - investigate")
  public void testPrintAllTransactionValuesFromTradersLivingInCambridge() throws Exception {
    createTransactions().stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);
  }

  @Test
  @Ignore("because of the NPE - investigate")
  public void testPrintAllTransactionValuesFromTradersLivingInCambridgeFromTheBook() throws Exception {
    List<Transaction> items = createTransactions().stream()
      .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity())).collect(toList());
//      .map(Transaction::getValue)
//      .forEach(System.out::println);

    System.out.println(Arrays.toString(items.toArray()));
  }

  @Test
  public void testTheHighestValueOfAllTransactions() throws Exception {
    Optional<Transaction> transaction = createTransactions().stream().max(comparing(Transaction::getValue));
    assertThat(transaction.get().getValue(), is(1000));
  }

  @Test
  public void testTheHighestValueOfAllTransactionsFromTheBook() throws Exception {
    Optional<Integer> highestValue = createTransactions().stream().map(Transaction::getValue).reduce(Integer::max);
    assertThat(highestValue.get(), is(1000));
  }

  @Test
  public void testSmallestTransaction() throws Exception {
    Optional<Transaction> transaction = createTransactions().stream().min(comparing(Transaction::getValue));
    assertThat(transaction.get().getValue(), is(300));
  }

  @Test
  public void testSmallestTransactionFromTheBook() throws Exception {
    Optional<Transaction> smallestTransaction = createTransactions().stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
    assertThat(smallestTransaction.get().getValue(), is(300));
  }
}
