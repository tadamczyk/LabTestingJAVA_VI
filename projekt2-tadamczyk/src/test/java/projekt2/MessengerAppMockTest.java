package projekt2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mocks.MessageServiceMock;

public class MessengerAppMockTest {
  private final String VALID_SERVER = "inf.ug.edu.pl";
  private final String INVALID_SERVER = "ab.c";
  private final String VALID_MESSAGE = "some message";
  private final String INVALID_MESSAGE = "ab";

  private Messenger messenger;
  private MessageServiceMock mock;

  @BeforeEach
  public void setUp() throws Exception {
    mock = new MessageServiceMock();
    messenger = new Messenger(mock);
  }

  @Test
  public void testVerifyConnectionWhenValidServerAndMockSetOnConnectionStatus() {
    mock.setConnection(true);

    int actual = messenger.verifyConnection(VALID_SERVER);
    int expected = ConnectionStatus.SUCCESS.ordinal();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testVerifyConnectionWhenValidServerAndMockSetOnNoConnectionStatus() {
    mock.setConnection(false);

    int actual = messenger.verifyConnection(VALID_SERVER);
    int expected = ConnectionStatus.FAILURE.ordinal();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testVerifyConnectionWhenInvalidServerAndMockSetOnConnectionStatus() {
    mock.setConnection(true);

    int actual = messenger.verifyConnection(INVALID_SERVER);
    int expected = ConnectionStatus.FAILURE.ordinal();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testVerifyConnectionWhenInvalidServerAndMockSetOnNoConnectionStatus() {
    mock.setConnection(false);

    int actual = messenger.verifyConnection(INVALID_SERVER);
    int expected = ConnectionStatus.FAILURE.ordinal();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testVerifyConnectionWhenNullServerAndMockSetOnConnectionStatus() {
    mock.setConnection(true);

    int actual = messenger.verifyConnection(null);
    int expected = ConnectionStatus.FAILURE.ordinal();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testVerifyConnectionWhenNullServerAndMockSetOnNoConnectionStatus() {
    mock.setConnection(false);

    int actual = messenger.verifyConnection(null);
    int expected = ConnectionStatus.FAILURE.ordinal();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testSendMessageWhenMockSetOnSentSendingStatus() {
    mock.setMessageSent(true);

    int expected = SendingStatus.SENT.ordinal();
    int actual = messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);
    assertEquals(expected, actual);
  }

  @Test
  public void testSendMessageWhenMockSetOnSendingErrorSendingStatus() {
    mock.setMessageSent(false);

    int expected = SendingStatus.SENDING_ERROR.ordinal();
    int actual = messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);
    assertEquals(expected, actual);
  }

  @Test
  public void testSendMessageWithValuesNullServerAndNullMessage() {
    int actual = messenger.sendMessage(null, null);
    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    assertThat(actual, is(expected));
  }

  @Test
  public void testSendMessageWithValuesNullServerAndValidMessage() {
    int actual = messenger.sendMessage(null, VALID_MESSAGE);
    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    assertThat(actual, is(expected));
  }

  @Test
  public void testSendMessageWithValuesNullServerAndInvalidMessage() {
    int actual = messenger.sendMessage(null, INVALID_MESSAGE);
    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    assertThat(actual, is(expected));
  }

  @Test
  public void testSendMessageWithValuesValidServerAndNullMessage() {
    int actual = messenger.sendMessage(VALID_SERVER, null);
    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    assertThat(actual, is(expected));
  }

  @Test
  public void testSendMessageWithValuesInvalidServerAndNullMessage() {
    int actual = messenger.sendMessage(INVALID_SERVER, null);
    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    assertThat(actual, is(expected));
  }

  @Test
  public void testSendMessageWithValuesValidServerAndInvalidMessage() {
    int actual = messenger.sendMessage(VALID_SERVER, INVALID_MESSAGE);
    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    assertThat(actual, is(expected));
  }

  @AfterEach
  public void tearDown() throws Exception {
    messenger = null;
    mock = null;
  }
}