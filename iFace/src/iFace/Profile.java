package iFace;

import java.util.ArrayList;
import java.util.List;


public class Profile {
	List<FriendshipRequest> friendshipRequest;
	List<String> myCommunities;
	List<String> friendName;
	List<String> myMenssage;
	private String name;
	private int age;
	private String sex;

	public Profile(String name, int age, String sex) {
		super();
		this.friendshipRequest = new ArrayList<>();
		this.myCommunities = new ArrayList<>();
		this.myMenssage = new ArrayList<>();
		this.friendName = new ArrayList<>();
		this.setName(name);
		this.setAge(age);
		this.setSex(sex);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<FriendshipRequest> getFriendshipRequest() {
		return friendshipRequest;
	}

	public void setFriendshipRequest(List<FriendshipRequest> friendshipRequest) {
		this.friendshipRequest = friendshipRequest;
	}

	public List<String> getMyCommunities() {
		return myCommunities;
	}

	public void setMyCommunities(List<String> myCommunities) {
		this.myCommunities = myCommunities;
	}

	public List<String> getFriendName() {
		return friendName;
	}

	public void setFriendName(List<String> friendName) {
		this.friendName = friendName;
	}

	public List<String> getMyMenssage() {
		return myMenssage;
	}

	public void setMyMenssage(List<String> myMenssage) {
		this.myMenssage = myMenssage;
	}

	@Override
	public String toString() {
		return "Profile [myCommunities=" + myCommunities + ", friendName=" + friendName + ", myMenssage=" + myMenssage
				+ ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
	
	

}