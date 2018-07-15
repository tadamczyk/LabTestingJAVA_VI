package projekt2;

public class Messenger {
  private MessageService service;

  public Messenger(MessageService service) {
    this.service = service;
  }

  public int sendMessage(String server, String message) {
    try {
      SendingStatus sendingStatus = service.send(server, message);
      switch (sendingStatus) {
      case SENT:
        return SendingStatus.SENT.ordinal();
      case SENDING_ERROR:
        return SendingStatus.SENDING_ERROR.ordinal();
      case MESSAGE_CONSTRUCTION_ERROR:
        return SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
      default:
        return -1;
      }
    } catch (MalformedRecipientException e) {
      return SendingStatus.MESSAGE_CONSTRUCTION_ERROR.ordinal();
    }
  }

  public int verifyConnection(String server) {
    ConnectionStatus connectionStatus = service.checkConnection(server);
    switch (connectionStatus) {
    case SUCCESS:
      return ConnectionStatus.SUCCESS.ordinal();
    case FAILURE:
      return ConnectionStatus.FAILURE.ordinal();
    default:
      return -1;
    }
  }
}