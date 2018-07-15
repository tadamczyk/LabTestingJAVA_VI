package projekt2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessengerAppJMockTest {
  private final String VALID_SERVER = "inf.ug.edu.pl";
  private final String INVALID_SERVER = "a.bc";

  private Messenger messenger;
  private MessageService mock;
  private Mockery context;

  @BeforeEach
  public void setUp() throws Exception {
    context = new Mockery();
    mock = context.mock(MessageService.class);
    messenger = new Messenger(mock);
  }

  @Test
  public void testVerifyConnectionWithAnyServerNameAndWithSuccess() {
    context.checking(new Expectations() {
      {
        oneOf(mock).checkConnection(with(any(String.class)));
        will(returnValue(ConnectionStatus.SUCCESS));
      }
    });

    int expected = ConnectionStatus.SUCCESS.ordinal();
    int actual = messenger.verifyConnection(VALID_SERVER);
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testVerifyConnectionWithValidServerAndWithSuccess() {
    context.checking(new Expectations() {
      {
        oneOf(mock).checkConnection(VALID_SERVER);
        will(returnValue(ConnectionStatus.SUCCESS));
      }
    });

    int expected = ConnectionStatus.SUCCESS.ordinal();
    int actual = messenger.verifyConnection(VALID_SERVER);
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testVerifyConnectionWithValidServerAndWithoutSuccess() {
    context.checking(new Expectations() {
      {
        oneOf(mock).checkConnection(VALID_SERVER);
        will(returnValue(ConnectionStatus.FAILURE));
      }
    });

    int notExpected = ConnectionStatus.SUCCESS.ordinal();
    int actual = messenger.verifyConnection(VALID_SERVER);
    assertThat(actual, is(not(equalTo(notExpected))));
  }

  @Test
  public void testVerifyConnectionWithInvalidServerAndWithoutSuccess() {
    context.checking(new Expectations() {
      {
        oneOf(mock).checkConnection(INVALID_SERVER);
        will(returnValue(ConnectionStatus.FAILURE));
      }
    });

    int notExpected = ConnectionStatus.SUCCESS.ordinal();
    int actual = messenger.verifyConnection(INVALID_SERVER);
    assertThat(actual, is(not(equalTo(notExpected))));
  }

  @Test
  public void testVerifyConnectionWithNullServerAndWithSuccess() {
    context.checking(new Expectations() {
      {
        oneOf(mock).checkConnection(null);
        will(returnValue(ConnectionStatus.SUCCESS));
      }
    });

    int expected = ConnectionStatus.SUCCESS.ordinal();
    int notExpected = ConnectionStatus.FAILURE.ordinal();
    int actual = messenger.verifyConnection(null);
    assertThat(actual).isEqualTo(expected).isNotEqualTo(notExpected);
  }

  @Test
  public void testVerifyConnectionWithNullServerAndWithoutSuccess() {
    context.checking(new Expectations() {
      {
        oneOf(mock).checkConnection(null);
        will(returnValue(ConnectionStatus.FAILURE));
      }
    });

    messenger.verifyConnection(null);
    context.assertIsSatisfied();
  }

  @AfterEach
  public void tearDown() throws Exception {
    messenger = null;
    mock = null;
    context = null;
  }
}