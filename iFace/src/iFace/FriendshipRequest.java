package iFace;

public class FriendshipRequest {
	private String senderName;
	private String receiverName;
	private boolean status;
	
	public void sendRequest(Profile a1, Profile a2) {
		//verificar se os dois já são amigos
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

}
