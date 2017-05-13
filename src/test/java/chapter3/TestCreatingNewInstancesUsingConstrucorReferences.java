package chapter3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Olavi
 */
public class TestCreatingNewInstancesUsingConstrucorReferences {

  @Test
  public void testCreatingInstanceUsingSupplier() throws Exception {
    Supplier<Entity> c1 = Entity::new;
    Entity entity = c1.get();

    Supplier<Entity> entitySupplier = () -> new Entity();
    Entity anotherEntity = entitySupplier.get();

    assertTrue(entity instanceof Entity);
    assertTrue(anotherEntity instanceof Entity);
  }

  @Test
  public void testCreatingInstanceUsingFunction() throws Exception {
    Function<Integer, Entity> entityFunction = Entity::new;
    Entity entity = entityFunction.apply(110);

    assertThat(entity.getId(), is(110));

    Function<Integer, Entity> anotherEntityFunction = (id) -> new Entity(id);
    Entity anotherEntity = anotherEntityFunction.apply(120);

    assertThat(anotherEntity.getId(), is(120));
  }

  @Test
  public void testCreatingMultipleEntities() throws Exception {
    List<Integer> integers = Arrays.asList(5, 17, 98, 205);
    List<Entity> items = map(integers, Entity::new);

    assertThat(items.size(), is(4));
    assertThat(items.get(0).getId(), is(5));
    assertThat(items.get(1).getId(), is(17));
    assertThat(items.get(2).getId(), is(98));
    assertThat(items.get(3).getId(), is(205));
  }

  private List<Entity> map(List<Integer> source, Function<Integer, Entity> f) {
    List<Entity> entities = new ArrayList<>();

    for (Integer integer : source) {
      entities.add(f.apply(integer));
    }

    return entities;
  }
}

class Entity {

  private Integer id;
  private String name;
  private String age;

  public Entity() {
  }

  public Entity(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }
}
