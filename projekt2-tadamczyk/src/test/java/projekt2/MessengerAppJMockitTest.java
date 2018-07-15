package projekt2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;

public class MessengerAppJMockitTest {
  private final String VALID_SERVER = "inf.ug.edu.pl";
  private final String INVALID_SERVER = "a.bc";

  @Tested
  private Messenger messenger;

  @Mocked
  private MessageService mock;

  @BeforeEach
  public void setUp() throws Exception {
    messenger = new Messenger(mock);
  }

  @Test
  public void testVerifyConnectionWithAnyServerNameAndWithSuccess() {
    new Expectations() {
      {
        mock.checkConnection(anyString);
        result = ConnectionStatus.SUCCESS;
      }
    };

    int expected = ConnectionStatus.SUCCESS.ordinal();
    int actual = messenger.verifyConnection(".");
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testVerifyConnectionWithInvalidServerAndWithSuccess() {
    new Expectations() {
      {
        mock.checkConnection(INVALID_SERVER);
        result = ConnectionStatus.SUCCESS;
      }
    };

    int expected = ConnectionStatus.SUCCESS.ordinal();
    int actual = messenger.verifyConnection(INVALID_SERVER);
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testVerifyConnectionWithValidServerAndWithoutSuccess() {
    new Expectations() {
      {
        mock.checkConnection(VALID_SERVER);
        result = ConnectionStatus.FAILURE;
      }
    };

    int notExpected = ConnectionStatus.SUCCESS.ordinal();
    int actual = messenger.verifyConnection(VALID_SERVER);
    assertThat(actual, is(not(equalTo(notExpected))));
  }

  @Test
  public void testVerifyConnectionWithInvalidServerAndWithoutSuccess() {
    new Expectations() {
      {
        mock.checkConnection(INVALID_SERVER);
        result = ConnectionStatus.FAILURE;
      }
    };

    int notExpected = ConnectionStatus.SUCCESS.ordinal();
    int actual = messenger.verifyConnection(INVALID_SERVER);
    assertThat(actual, is(not(equalTo(notExpected))));
  }

  @Test
  public void testVerifyConnectionWithNullServerAndWithSuccess() {
    new Expectations() {
      {
        mock.checkConnection(null);
        result = ConnectionStatus.SUCCESS;
      }
    };

    int expected = ConnectionStatus.SUCCESS.ordinal();
    int actual = messenger.verifyConnection(null);
    assertThat(actual).isEqualTo(expected);
  }

  @AfterEach
  public void tearDown() throws Exception {
    messenger = null;
    mock = null;
  }
}