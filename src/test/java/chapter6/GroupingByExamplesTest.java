package chapter6;

import menu.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static chapter6.GroupingByExamples.CaloricLevel.*;
import static menu.Dish.Type.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GroupingByExamplesTest {

  private GroupingByExamples uut;

  @Before
  public void setUp() throws Exception {
    uut = new GroupingByExamples();
  }

  @Test
  public void groupDishesByType() throws Exception {
    Map<Dish.Type, List<Dish>> groupedByType = uut.groupDishesByType();

    assertThat(groupedByType.size(), is(3));
    assertThat(groupedByType, hasKey(FISH));
    assertThat(groupedByType, hasKey(MEAT));
    assertThat(groupedByType, hasKey(OTHER));

    assertThat(groupedByType.get(FISH).size(), is(2));
    assertThat(groupedByType.get(MEAT).size(), is(3));
    assertThat(groupedByType.get(OTHER).size(), is(4));
  }

  @Test
  public void testGroupDishesByCaloricLevel() throws Exception {
    Map<GroupingByExamples.CaloricLevel, List<Dish>> groupedByCaloricLevel = uut.groupDishesByCaloricLevel();

    assertThat(groupedByCaloricLevel, hasKey(DIET));
    assertThat(groupedByCaloricLevel, hasKey(NORMAL));
    assertThat(groupedByCaloricLevel, hasKey(FAT));

    assertThat(groupedByCaloricLevel.get(DIET).size(), is(4));
    assertThat(groupedByCaloricLevel.get(NORMAL).size(), is(4));
    assertThat(groupedByCaloricLevel.get(FAT).size(), is(1));
  }

  @Test
  public void testGroupingByDishTypeAndCaloricLevel() throws Exception {
    Map<Dish.Type, Map<GroupingByExamples.CaloricLevel, List<Dish>>> result = uut.groupDishesByTypeAndCaloricLevel();

    assertThat(result.isEmpty(), is(false));
    assertThat(result.size(), is(3));
    assertThat(result, hasKey(FISH));
    assertThat(result, hasKey(MEAT));
    assertThat(result, hasKey(OTHER));

    assertThat(result.get(FISH).size(), is(2));
    assertThat(result.get(MEAT).size(), is(3));
    assertThat(result.get(OTHER).size(), is(2));

    assertThat(result.get(FISH).containsKey(DIET), is(true));
    assertThat(result.get(FISH).containsKey(NORMAL), is(true));

    assertThat(result.get(FISH).get(DIET).size(), is(1));
    assertThat(result.get(FISH).get(NORMAL).size(), is(1));

    assertThat(result.get(MEAT).containsKey(DIET), is(true));
    assertThat(result.get(MEAT).containsKey(NORMAL), is(true));
    assertThat(result.get(MEAT).containsKey(FAT), is(true));

    assertThat(result.get(MEAT).get(DIET).size(), is(1));
    assertThat(result.get(MEAT).get(NORMAL).size(), is(1));
    assertThat(result.get(MEAT).get(FAT).size(), is(1));

    assertThat(result.get(OTHER).containsKey(DIET), is(true));
    assertThat(result.get(OTHER).containsKey(NORMAL), is(true));

    assertThat(result.get(OTHER).get(DIET).size(), is(2));
    assertThat(result.get(OTHER).get(NORMAL).size(), is(2));
  }
}