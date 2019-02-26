package dao;

public class FriendshipRequest {
	private String senderName;
	private String receiverName;
	private boolean status;
	
	public void sendRequest(AccountDAO a1, AccountDAO a2) {
		setSenderName(a1.getName());
		setReceiverName(a2.getName());
		setStatus(true);		
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	@Override
	public String toString() {
		return "FriendshipRequest [senderName=" + senderName + "]";
	}

	
}
