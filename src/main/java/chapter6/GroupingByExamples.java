package chapter6;

import menu.Dish;
import menu.Menu;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class GroupingByExamples {

  public enum CaloricLevel {DIET, NORMAL, FAT}

  public Map<Dish.Type, List<Dish>> groupDishesByType() {
    return Menu.getDishes().stream().collect(groupingBy(Dish::getType));
  }

  public Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
    return Menu.getDishes().stream().collect(groupingBy(
      dish -> {
        if (dish.getCalories() <= 400) {
          return CaloricLevel.DIET;
        }
        else if (dish.getCalories() <= 700) {
          return CaloricLevel.NORMAL;
        }
        else {
          return CaloricLevel.FAT;
        }
      }
    ));
  }
}
