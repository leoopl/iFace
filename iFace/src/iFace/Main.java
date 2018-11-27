package iFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static HashMap<String, Account> accounts;
	private static HashMap<String, Community> communities;
	private static Account activeUser;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		accounts = new HashMap<>();
		communities = new HashMap<>();
		
		
		while (true) {
			System.out.println("..");
			int input = scan.nextInt();

			switch (input) {
			case 1:
				createAccount();
				break;
			case 2:
				createProfile();
				break;
			case 3:
				editProfile();
				break;
			case 4:
				sendFriendshipRequest();
				break;
			case 5:
				sendMessage();
				break;
			case 6:
				createComunite();
				break;
			case 7:
				searchCommunite();
				break;
			case 8:
				profileInfo();
				break;
			case 9:
				removeAccount();

			}

		}

	}

	private static void removeAccount() {
		String userToRemove = "";
		
		for(String key : accounts.keySet()) {
			Account acc = accounts.get(key);
			List<String> friendList = acc.getProfile().getFriendName();
			if(friendList.contains(userToRemove)) {
				friendList.remove(userToRemove);
			}
			acc.getProfile().setFriendName(friendList);
			//remover msg e friendshipRequest
			accounts.remove(userToRemove);
			activeUser = null;
		}
	}

	private static void profileInfo() {
		System.out.println(activeUser.getProfile().toString());
		
	}

	private static void searchCommunite() {
		//se quiser imprimir key set das hash
		//digitar o nome da comunidade
		
	}

	private static void createComunite() {
		// só posso criar se estiver logado
		// pedir o nome, a descrição, e salvar o id do criador;
		//add na hash
		
	}

	private static void sendMessage() {
		//mesmo estilo de sendfriendship
		
	}

	private static void sendFriendshipRequest() {
		//criar o FR
		// id do logado; id pra quem ele vai mandar; status -> enum ou simplesmente numeros;
		//
		
	}

	private static void editProfile() {
		Scanner intinput = new Scanner(System.in);
		Scanner stringinput = new Scanner(System.in);
		while(true) {
			System.out.println("O que você quer editar?\n [1] Nome\n [2] Idade\n [3] Genro");
			int input = intinput.nextInt();			
			switch(input) {
			case 1:
				System.out.println("Novo nome: ");
				String newName = stringinput.nextLine();
				activeUser.getProfile().setName(newName);
				break;
			case 2:
				System.out.println("Nova idade: ");
				int newAge = intinput.nextInt();
				activeUser.getProfile().setAge(newAge);
				break;
			case 3:
				System.out.println("Novo Genero: ");
				String newSex = stringinput.nextLine();
				activeUser.getProfile().setSex(newSex);
				break;
			default:
				System.out.println("Opção Invalida");
			}
			accounts.replace(activeUser.getName(), activeUser);
		}

	}

	private static void createProfile() {
		//ver se ja existe um profile
		if (activeUser == null) {
			System.out.println("conta não existente");
			return;
		}
		// user, age, sex;
		// 
		String name = "";
		int age = 0;
		String sex = "";
		Profile f = new Profile(name, age, sex);
		activeUser.setProfile(f);
		accounts.replace(activeUser.getName(), activeUser);
	}

	private static void createAccount() {
		// TODO Auto-generated method stub
		System.out.println("...");
		// scan pra login
		System.out.println("");
		// scan pra senha
		System.out.println();
		// scan pra nome
		//add hash usando nome do usuario como key
		activeUser = new Account();
		accounts.put("", activeUser);
	}

}
