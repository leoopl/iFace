package iFace;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import dao.AccountDAO;

public class Account {

	private HashMap<String, AccountDAO> accounts;

	public Account() {
		accounts = new HashMap<>();
	}
	
	public boolean containsKey(String key) {
		if (accounts.containsKey(key)) {
			return true;
		}
		return false;
	}

	public void createAccount() {
		Scanner scan = new Scanner(System.in);
		AccountDAO acc = new AccountDAO();
		acc = new AccountDAO();
		System.out.println("Digite seu Login: ");
		String theLogin = scan.nextLine();
		acc.setLogin(theLogin);
		System.out.println("Digite sua Senha: ");
		String thePsw = scan.nextLine();
		acc.setPsw(thePsw);
		System.out.println("Digite seu Nome: ");
		String theName = scan.nextLine();
		acc.setName(theName);

		accounts.put(theName, acc);
	}

	public void removeAccount(AccountDAO activeUser) {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta não logada");
			return;
		}
		System.out.println("Excluind conta....");
		String userToRemove = activeUser.getName();

		if (activeUser.getProfile() == null) {
			accounts.remove(userToRemove);
			activeUser = null;
		} else {
			for (String key : accounts.keySet()) {
				AccountDAO acc = accounts.get(key);
				List<String> friendList = acc.getProfile().getFriendName();
				if (friendList.contains(userToRemove)) {
					friendList.remove(userToRemove);
				}
				acc.getProfile().setFriendName(friendList);

				accounts.replace(acc.getName(), acc);
			}
			accounts.remove(userToRemove);
			activeUser = null;
		}
	}

	public HashMap<String, AccountDAO> getAccounts() {
		return accounts;
	}


}
