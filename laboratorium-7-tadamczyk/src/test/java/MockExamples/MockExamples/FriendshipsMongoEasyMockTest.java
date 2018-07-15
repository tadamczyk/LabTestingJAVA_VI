package MockExamples.MockExamples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EasyMockRunner.class)
public class FriendshipsMongoEasyMockTest {

  @TestSubject
  FriendshipsMongo friendships = new FriendshipsMongo();

  @Mock(type = MockType.NICE)
  FriendsCollection friends;

  @Test
  public void mockingWorksAsExpected() {
    Person joe = new Person("Joe");
    expect(friends.findByName("Joe")).andReturn(joe);
    replay(friends);
    assertThat(friends.findByName("Joe")).isEqualTo(joe);
  }

  @Test
  public void alexDoesNotHaveFriends() {
    assertThat(friendships.getFriendsList("Alex")).isEmpty();
  }

  @Test
  public void joeHas5Friends() {
    List<String> expected = Arrays.asList(new String[] { "Karol", "Dawid", "Maciej", "Tomek", "Adam" });
    Person joe = createMock(Person.class);
    expect(friends.findByName("Joe")).andReturn(joe);
    expect(joe.getFriends()).andReturn(expected);
    replay(friends);
    replay(joe);
    assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol", "Dawid", "Maciej", "Tomek",
        "Adam");
  }

  @Test
  public void joeAndAdamAreNotFriendsThenReturnFalse() {
    Person joe = createMock(Person.class);
    expect(friends.findByName("Joe")).andReturn(joe);
    assertFalse(friendships.areFriends("Joe", "Adam"));
  }

  @Test
  public void joeAndNullValueAreNotFriendsThenReturnFalse() {
    Person joe = createMock(Person.class);
    expect(friends.findByName("Joe")).andReturn(joe);
    assertFalse(friendships.areFriends("Joe", null));
  }

  @Test
  public void joeAndAdamAreFriends() {
    List<String> expected = Arrays.asList(new String[] { "Karol", "Dawid", "Maciej", "Tomek", "Adam" });
    Person joe = createMock(Person.class);
    expect(friends.findByName("Joe")).andReturn(joe);
    expect(joe.getFriends()).andReturn(expected);
    replay(friends);
    replay(joe);
    assertTrue(friendships.areFriends("Joe", "Adam"));
  }
}