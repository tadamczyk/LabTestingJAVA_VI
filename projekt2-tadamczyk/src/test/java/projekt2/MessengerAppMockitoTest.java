package projekt2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mocks.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MessengerAppMockitoTest {
  private final String VALID_SERVER = "inf.ug.edu.pl";
  private final String VALID_MESSAGE = "some message";

  private Messenger messenger;

  @Mock
  private MessageService mock;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    messenger = new Messenger(mock);
  }

  @Test
  public void testSendMessageWithValuesValidServerAndValidMessageAndMockSendingStatusIsSent()
      throws MalformedRecipientException {
    doReturn(SendingStatus.SENT).when(mock).send(VALID_SERVER, VALID_MESSAGE);

    int expected = SendingStatus.SENT.ordinal();
    int actual = messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);
    verify(mock).send(VALID_SERVER, VALID_MESSAGE);
    assertEquals(expected, actual);
  }

  @Test
  public void testSendMessageWithValuesValidServerAndValidMessageAndMockSendingStatusIsSendingError()
      throws MalformedRecipientException {
    when(mock.send(VALID_SERVER, VALID_MESSAGE)).thenReturn(SendingStatus.SENDING_ERROR);

    int expected = SendingStatus.SENDING_ERROR.ordinal();
    int actual = messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);
    verify(mock).send(VALID_SERVER, VALID_MESSAGE);
    assertEquals(expected, actual);
  }

  @Test
  public void testSendMessageWithValuesValidServerAndNullMessageAndMockSendingStatusIsMessageConstructionError()
      throws MalformedRecipientException {
    when(mock.send(VALID_SERVER, null)).thenReturn(SendingStatus.MESSAGE_CONSTRUCTION_ERROR);

    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    int notExpected = SendingStatus.SENDING_ERROR.ordinal();
    int actual = messenger.sendMessage(VALID_SERVER, null);
    verify(mock).send(VALID_SERVER, null);
    assertThat(actual).isSameAs(expected).isNotEqualTo(notExpected);
  }

  @Test
  public void testSendMessageWithValuesNullServerAndValidMessageAndMockSendingStatusIsMessageConstructionError()
      throws MalformedRecipientException {
    when(mock.send(null, VALID_MESSAGE)).thenReturn(SendingStatus.MESSAGE_CONSTRUCTION_ERROR);

    int expected = SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    int actual = messenger.sendMessage(null, VALID_MESSAGE);
    verify(mock).send(null, VALID_MESSAGE);
    assertThat(actual).isSameAs(expected).isNotNull();
  }

  @Test
  public void testSendMessageWithValuesNullServerAndNullMessageAndMockSendingStatusIsMessageConstructionError()
      throws MalformedRecipientException {
    when(mock.send(null, null)).thenReturn(SendingStatus.MESSAGE_CONSTRUCTION_ERROR);

    int notExpected = SendingStatus.SENDING_ERROR.ordinal();
    int actual = messenger.sendMessage(null, null);
    verify(mock).send(null, null);
    assertThat(actual, is(not(notExpected)));
  }

  @Test
  public void testSendMessageWithValuesNullServerAndNullMessageAndMockSendingStatusIsSendingError()
      throws MalformedRecipientException {
    doReturn(SendingStatus.SENDING_ERROR).when(mock).send(null, null);

    int expected = SendingStatus.SENDING_ERROR.ordinal();
    int actual = messenger.sendMessage(null, null);
    verify(mock).send(null, null);
    assertThat(actual, is(expected));
  }

  @AfterEach
  public void tearDown() throws Exception {
    messenger = null;
    mock = null;
  }
}