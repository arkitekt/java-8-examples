package chapter6;

import menu.Dish;
import menu.Menu;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class GroupingByExamples {

  public enum CaloricLevel {DIET, NORMAL, FAT}

  public Map<Dish.Type, List<Dish>> groupDishesByType() {
    return Menu.getDishes().stream().collect(groupingBy(Dish::getType));
  }

  public Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
    return Menu.getDishes().stream().collect(groupingBy(
      getCaloricLevelForDish()
    ));
  }

  public Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishesByTypeAndCaloricLevel() {
    return Menu.getDishes().stream().collect(groupingBy(Dish::getType, groupingBy(getCaloricLevelForDish())));
  }

  private Function<Dish, CaloricLevel> getCaloricLevelForDish() {
    return dish -> {
      if (dish.getCalories() <= 400) {
        return CaloricLevel.DIET;
      }
      else if (dish.getCalories() <= 700) {
        return CaloricLevel.NORMAL;
      }
      else {
        return CaloricLevel.FAT;
      }
    };
  }

  public Map<Dish.Type, Long> countSubGroupItems() {
    return Menu.getDishes().stream().collect(groupingBy(Dish::getType, counting()));
  }

  public Map<Dish.Type, Dish> findMostCaloricDishesForGroup() {
    return Menu.getDishes().stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
  }
}
