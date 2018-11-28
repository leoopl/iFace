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
		
		int input;
		do {
			System.out.println("#####################################");
			System.out.println("----------------MENU----------------");
			System.out.println("#####################################");
		    System.out.println("[1] Criar Conta");
		    System.out.println("[2] Criar Perfil");
		    System.out.println("[3] Editar Perfil");
		    System.out.println("[4] Adicionar Amigo");
		    System.out.println("[5] Mandar Mensagem");
		    System.out.println("[6] Criar Comunidade");
		    System.out.println("[7] Entrar em uma Comunidade");
		    System.out.println("[8] Informação do Perfil");
		    System.out.println("[9] Remover Conta");
		    System.out.println("[10] Login");
		    System.out.println("[0] Sair");
			input = scan.nextInt();

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
				break;
			case 10:
				login();
				break;
			case 0:
				System.out.println("bye bye!");
				break;
			default:
				System.out.println("Operação invalida");

			}

		}while(input != 0);

	}

	private static void removeAccount() {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta não criada");
			return;
		}
		System.out.println("Excluind conta....");
		String userToRemove = activeUser.getName();
		
		for(String key : accounts.keySet()) {
			Account acc = accounts.get(key);
			List<String> friendList = acc.getProfile().getFriendName();
			if(friendList.contains(userToRemove)) {
				friendList.remove(userToRemove);
			}
			acc.getProfile().setFriendName(friendList);
			
			
			accounts.replace(acc.getName(), acc);
		}
		accounts.remove(userToRemove);
		activeUser = null;
	}

	private static void profileInfo() {
		if (activeUser == null || activeUser.getProfile() == null) {
			System.out.println("Você precisa conectar ou criar seu perfil antes!");
			return;
		}
		System.out.println(activeUser.getProfile().toString());
	}

	private static void searchCommunite() {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta não conectada");
			return;
		}
		System.out.println(communities.keySet());
		System.out.println("Digite o nome da comunidade na qual você deseja entrar: ");
		String communiteName = input.nextLine();
		if (communities.containsKey(communiteName)) {
			activeUser.getProfile().getMyCommunities().add(communiteName);
			communities.get(communiteName).getMembers().add(activeUser.getName());
			System.out.println("Você acaba de entrar na "+ communiteName);
		}else{
			System.out.println("Comunidade inexistente");
		}		
	}

	private static void createComunite() {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Você precisa antes criar uma conta");
			return;
		}
		Community c = new Community();
		System.out.println("Digite o Nome da Comunidade: ");
		String comunitName = input.nextLine();
		c.setName(comunitName);
		System.out.println("Digite a Descrição da Comunidade: ");
		String comunitDesc = input.nextLine();
		c.setDesc(comunitDesc);
		c.setOwner(activeUser.getName());
		activeUser.getProfile().getMyCommunities().add(comunitName);
		communities.put(comunitName, c);
		System.out.println("Comunidade Criada com sucesso!");
	}

	private static void sendMessage() {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Você precisa antes criar uma conta");
			return;
		}
		Message newSend = new Message();
		System.out.println("Digite o nome para quem você quer enviar a mensagem: ");
		String receiverName = input.nextLine();
		if (accounts.containsKey(receiverName)) {
			newSend.setReceiverName(receiverName);
			System.out.println("Digite sua mensagem: ");
			String newMsg = input.nextLine();
			newSend.setMsg(newMsg);
			newSend.setSenderName(activeUser.getName());
			accounts.get(receiverName).getProfile().getMyMenssage().add(newMsg);
		}else {
			System.out.println("Usuario inexistente");
		}		
	}

	private static void sendFriendshipRequest() {
		Scanner input = new Scanner(System.in);
		if (activeUser == null || activeUser.getProfile() == null) {
			System.out.println("Você precisa antes criar uma conta e seu perfil");
			return;
		}
		FriendshipRequest newRequest = new FriendshipRequest();
		System.out.println("Quem você deseja adicionar: ");
		String requested = input.nextLine();
		if (accounts.containsKey(requested)) {
			if (activeUser.getProfile().getFriendName().contains(requested)) {
				System.out.println("Vocês já são amigos!");
			}else {
				try{
					newRequest.sendRequest(activeUser, accounts.get(requested));
					accounts.get(requested).getProfile().getFriendshipRequest().add(newRequest);
				}catch (Exception e) {
					System.out.println("O amigo que você tentou adicionar não tem perfil!");
				}
			}
		} else {
			System.out.println("Pessoa inexistente.");
		}
	}

	private static void editProfile() {
		Scanner intinput = new Scanner(System.in);
		Scanner stringinput = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta não conectada");
			return;
		}
		if (activeUser.getProfile() != null) {
			int input;
			do {
				System.out.println("O que você quer editar?\n [1] Nome\n [2] Idade\n [3] Gênero\n [0] Voltar ao menu principal");
				input = intinput.nextInt();			
				switch(input) {
				case 1:
					System.out.println("Novo User: ");
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
				case 0:
					break;
				default:
					System.out.println("Opção Invalida");
				}
				accounts.replace(activeUser.getName(), activeUser);
			}while(input != 0);
		}else {
			System.out.println("Perfil ainda não foi criado");
		}
	}

	private static void createProfile() {
		Scanner intinput = new Scanner(System.in);
		Scanner stringinput = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta não existente");
			return;
		}
		if (activeUser.getProfile() == null) {
			System.out.println("Digite seu User: ");
			String user = stringinput.nextLine();
			System.out.println("Digite sua idade: ");
			int age = intinput.nextInt();
			System.out.println("Gênero, digite:\n [M] - Masculino\n [F] - Feminino");
			char gen = stringinput.next().charAt(0);
			String sex = null;
			if (gen == 'M' || gen == 'm') {
				sex = "Masculino";
			} else if (gen == 'F' || gen == 'f') {
				sex = "Feminino";
			}
			Profile p = new Profile(user, age, sex);
			activeUser.setProfile(p);
			accounts.replace(activeUser.getName(), activeUser);
			System.out.println("Perfil criado com sucesso!");
		}else {
			System.out.println("Perfil já existente!");
		}
	}

	private static void createAccount() {
		Scanner scan = new Scanner(System.in);
		Account acc = new Account();
		acc = new Account();
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
	
	
	private static void login() {
		Scanner input = new Scanner(System.in);
		System.out.println("Login: ");
		String login = input.nextLine();
		System.out.println("Senha: ");
		String thePsw = input.nextLine();
		if (searchUser(login, thePsw)) {
			System.out.println("Logado");
		} else {
			System.out.println("Login ou Senha invalido");
		}
		
	}
	
	private static boolean searchUser(String login, String senha) {
		for (String key : accounts.keySet()) {
			Account acc = accounts.get(key);
			if (acc.getLogin().equals(login) && acc.getPsw().equals(senha)) {
				activeUser = acc;
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	}

