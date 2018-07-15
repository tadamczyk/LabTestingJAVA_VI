package projekt2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import mocks.EasyMockExtension;

@ExtendWith(EasyMockExtension.class)
public class MessengerAppEasyMockTest {
  private final String VALID_SERVER = "inf.ug.edu.pl";
  private final String INVALID_SERVER = "a.bc";
  private final String VALID_MESSAGE = "inf ug edu pl";
  private final String INVALID_MESSAGE = "ab";

  private Messenger messenger;
  private MessageService mock;

  @BeforeEach
  public void setUp() throws Exception {
    mock = EasyMock.createNiceMock(MessageService.class);
    messenger = new Messenger(mock);
  }

  @Test
  public void testSendMessageWithValuesValidServerAndInvalidMessage() throws MalformedRecipientException {
    expect(mock.send(VALID_SERVER, INVALID_MESSAGE)).andReturn(SendingStatus.MESSAGE_CONSTRUCTION_ERROR);
    replay(mock);

    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    int actual = messenger.sendMessage(VALID_SERVER, INVALID_MESSAGE);
    assertEquals(expected, actual);
  }

  @Test
  public void testSendMessageWithValuesInvalidServerAndValidMessage() throws MalformedRecipientException {
    expect(mock.send(INVALID_SERVER, VALID_MESSAGE)).andThrow(new MalformedRecipientException());
    replay(mock);

    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    int actual = messenger.sendMessage(INVALID_SERVER, VALID_MESSAGE);
    assertThat(actual).isEqualTo(expected).isPositive().isNotNull();
  }

  @Test
  public void testSendMessageWithValuesNullServerAndInvalidMessage() throws MalformedRecipientException {
    expect(mock.send(null, INVALID_MESSAGE)).andReturn(SendingStatus.MESSAGE_CONSTRUCTION_ERROR);
    replay(mock);

    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    int actual = messenger.sendMessage(null, INVALID_MESSAGE);
    assertEquals(expected, actual);
  }

  @Test
  public void testSendMessageWithValuesInvalidServerAndNullMessage() throws MalformedRecipientException {
    expect(mock.send(INVALID_SERVER, null)).andThrow(new MalformedRecipientException());
    replay(mock);

    int notExpected = SendingStatus.SENDING_ERROR.ordinal();
    int actual = messenger.sendMessage(INVALID_SERVER, null);
    assertThat(actual).isNotEqualTo(notExpected).isNotNull().isNotNegative();
  }

  @Test
  public void testSendMessageWithValuesInvalidServerAndInvalidMessage() throws MalformedRecipientException {
    expect(mock.send(INVALID_SERVER, INVALID_MESSAGE)).andReturn(SendingStatus.MESSAGE_CONSTRUCTION_ERROR);
    replay(mock);

    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    int actual = messenger.sendMessage(INVALID_SERVER, INVALID_MESSAGE);
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testSendMessageWithValuesInvalidServerAndInvalidMessageAndMockSendingStatusIsSent()
      throws MalformedRecipientException {
    expect(mock.send(INVALID_SERVER, INVALID_MESSAGE)).andReturn(SendingStatus.SENT);
    replay(mock);

    int notExpected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    int actual = messenger.sendMessage(INVALID_SERVER, INVALID_MESSAGE);
    assertThat(actual, is(not(equalTo(notExpected))));
  }

  @AfterEach
  public void tearDown() throws Exception {
    messenger = null;
    mock = null;
  }
}