package mocks;

import projekt2.ConnectionStatus;
import projekt2.MalformedRecipientException;
import projekt2.MessageService;
import projekt2.SendingStatus;

public class MessageServiceMock implements MessageService {
  private boolean connectionExists = false;
  private boolean messageSent = false;

  public void setConnection(boolean value) {
    connectionExists = value;
  }

  public void setMessageSent(boolean value) {
    messageSent = value;
  }

  @Override
  public ConnectionStatus checkConnection(String server) {
    if (isConnectionCorrect(server, ".pl")) {
      return ConnectionStatus.SUCCESS;
    }
    return ConnectionStatus.FAILURE;
  }

  private boolean isConnectionCorrect(String server, String suffix) {
    return connectionExists && server != null && server.endsWith(suffix);
  }

  @Override
  public SendingStatus send(String server, String message) throws MalformedRecipientException {
    try {
      isNull(server);
      isNull(message);
      isCorrectLength(server, 5);
      isCorrectLength(message, 3);
      if (messageSent) {
        return SendingStatus.SENT;
      } else {
        return SendingStatus.SENDING_ERROR;
      }
    } catch (MalformedRecipientException e) {
      return SendingStatus.MESSAGE_CONSTRUCTION_ERROR;
    }
  }

  private void isCorrectLength(String value, int minLength) throws MalformedRecipientException {
    if (value.length() < minLength) {
      throw new MalformedRecipientException();
    }
  }

  private void isNull(String value) throws MalformedRecipientException {
    if (value == null) {
      throw new MalformedRecipientException();
    }
  }
}