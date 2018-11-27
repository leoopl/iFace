package iFace;

import java.util.List;

public class Community {
	private String name;
	private String desc;
	private String owner;
	private static List<String> members;
	
	/* remover comunidade
	 * if(activeUser.getProfile().myCommunities.contains(communiteName)) {
				activeUser.getProfile().myCommunities.remove(communiteName);
				Community.removeMember(activeUser.getName());
	 * */
	
	public static void removeMember(String name) {
		members.remove(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public List<String> getMembers() {
		return members;
	}
	public void setMembers(List<String> members) {
		this.members = members;
	}

	
}
