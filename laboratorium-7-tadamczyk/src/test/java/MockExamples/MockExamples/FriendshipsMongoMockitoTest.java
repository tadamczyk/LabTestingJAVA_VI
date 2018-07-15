package MockExamples.MockExamples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class FriendshipsMongoMockitoTest {

  @Mock
  FriendsCollection friends;

  @InjectMocks
  FriendshipsMongo friendships;

  @Test
  public void mockingWorskAsExpected() {
    Person joe = new Person("Joe");
    doReturn(joe).when(friends).findByName("Joe");
    assertThat(friends.findByName("Joe")).isEqualTo(joe);
  }

  @Test
  public void alexDoesNotHaveFriends() {
    assertThat(friendships.getFriendsList("Alex")).isEmpty();
  }

  @Test
  public void joeHas5Friends() {
    List<String> expected = Arrays.asList(new String[] { "Karol", "Dawid", "Maciej", "Tomek", "Adam" });
    // Spy przechwytuje wywołania z przekazywanymi pośrednio danymi w celu
    // późniejszego zbadania tych danych w teście - tutaj w przykładzie lista
    // przyjaciół Joe
    Person joe = spy(new Person("Joe"));
    doReturn(joe).when(friends).findByName("Joe");
    doReturn(expected).when(joe).getFriends();
    assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol", "Dawid", "Maciej", "Tomek",
        "Adam");
  }

  @Test
  public void joeAndAlexAreFriendsAfterMakeFriends() {
    Person joe = spy(new Person("Joe"));
    Person alex = spy(new Person("Alex"));
    doReturn(joe).when(friends).findByName("Joe");
    doReturn(alex).when(friends).findByName("Alex");
    friendships.makeFriends("Joe", "Alex");
    assertTrue(friendships.areFriends("Joe", "Alex"));
  }

  @Test
  public void joeAndAdamAreNotFriendsThenReturnFalse() {
    Person joe = spy(new Person("Joe"));
    doReturn(joe).when(friends).findByName("Joe");
    assertFalse(friendships.areFriends("Joe", "Adam"));
  }

  @Test
  public void joeAndNullValueAreNotFriendsThenReturnFalse() {
    Person joe = spy(new Person("Joe"));
    doReturn(joe).when(friends).findByName("Joe");
    assertFalse(friendships.areFriends("Joe", null));
  }

  @Test
  public void joeAndAdamAreFriends() {
    List<String> expected = Arrays.asList(new String[] { "Karol", "Dawid", "Maciej", "Tomek", "Adam" });
    Person joe = spy(new Person("Joe"));
    doReturn(joe).when(friends).findByName("Joe");
    when(joe.getFriends()).thenReturn(expected);
    assertTrue(friendships.areFriends("Joe", "Adam"));
  }
}