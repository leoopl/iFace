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
		    System.out.println("[8] Informa��o do Perfil");
		    System.out.println("[9] Remover Conta");
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
			case 0:
				System.out.println("bye bye!");
				break;
			default:
				System.out.println("Opera��o invalida");

			}

		}while(input != 0);

	}

	private static void removeAccount() {
		//FALTA ACABAR
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta n�o criada");
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
			//remover msg e friendshipRequest
			accounts.remove(userToRemove);
			activeUser = null;
		}
	}

	private static void profileInfo() {
		if (activeUser == null) {
			System.out.println("Conta n�o conectada");
			return;
		}
		System.out.println(activeUser.getProfile().toString());
	}

	private static void searchCommunite() {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta n�o conectada");
			return;
		}
		System.out.println(communities.keySet());
		System.out.println("Digite o nome da comunidade na qual voc� deseja entrar: ");
		String communiteName = input.nextLine();
		if (communities.containsKey(communiteName)) {
			activeUser.getProfile().setMyCommunities(communiteName);//ARRUMAR O SET DE COMUNIDADE
			Community.setMembers(activeUser.getName());//N�O � ASSIM, PRECISO ADD PELA HASH????
			System.out.println("Voc� acaba de entrar na "+ communiteName);
		}else{
			System.out.println("Comunidade inexistente");
		}		
	}

	private static void createComunite() {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Voc� precisa antes criar uma conta");
			return;
		}
		Community c = new Community();
		System.out.println("Digite o Nome da Comunidade: ");
		String comunitName = input.nextLine();
		c.setName(comunitName);
		System.out.println("Digite a Descri��o da Comunidade: ");
		String comunitDesc = input.nextLine();
		c.setDesc(comunitDesc);
		c.setOwner(activeUser.getName());
		communities.put(comunitName, c);		
	}

	private static void sendMessage() {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Voc� precisa antes criar uma conta");
			return;
		}
		Message newSend = new Message();
		System.out.println("Digite o nome para quem voc� quer enviar a mensagem: ");
		String receiverName = input.nextLine();
		if (accounts.containsKey(receiverName)) {
			newSend.setReceiverName(receiverName);
			System.out.println("Digite sua mensagem: ");
			String newMsg = input.nextLine();
			newSend.setMsg(newMsg);
			newSend.setSenderName(activeUser.getName());
			//PROCURO NAS ACCOUNTS, ENTRO NO PERFIL E ADD A MSG
			///PRECISO ADICIONAR A MSG AO ENVIADO
		}else {
			System.out.println("Usuario inexistente");
		}		
	}

	private static void sendFriendshipRequest() {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Voc� precisa antes criar uma conta");
			return;
		}
		FriendshipRequest newRequest = new FriendshipRequest();
		System.out.println("Quem voc� deseja adicionar: ");
		String requested = input.nextLine();
		//PRECISO COLOCAR A CONDI��O SE JA TENHO COMO AMIGO????
		if (accounts.containsKey(requested)) {
			newRequest.sendRequest(activeUser.getProfile(), accounts.get(requested).getProfile());
		} else {
			System.out.println("Pessoa inexistente.");
		}
	}

	private static void editProfile() {
		Scanner intinput = new Scanner(System.in);
		Scanner stringinput = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta n�o conectada");
			return;
		}
		if (activeUser.getProfile() != null) {
			int input;
			do {
				System.out.println("O que voc� quer editar?\n [1] Nome\n [2] Idade\n [3] G�nero\n [0] Voltar ao menu principal");
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
					System.out.println("Op��o Invalida");
				}
				accounts.replace(activeUser.getName(), activeUser);
			}while(input != 0);
		}else {
			System.out.println("Perfil ainda n�o foi criado");
		}
	}

	private static void createProfile() {
		Scanner intinput = new Scanner(System.in);
		Scanner stringinput = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta n�o existente");
			return;
		}
		if (activeUser.getProfile() == null) {
			System.out.println("Digite seu User: ");
			String user = stringinput.nextLine();
			System.out.println("Digite sua idade: ");
			int age = intinput.nextInt();
			System.out.println("G�nero, digite:\n [M] - Masculino\n [F] - Feminino");
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
			System.out.println("Perfil j� existente!");
		}
	}

	private static void createAccount() {
		Scanner scan = new Scanner(System.in);
		//Account acc = new Account();
		activeUser = new Account();
		System.out.println("Digite seu Login: ");
		String theLogin = scan.nextLine();
		activeUser.setLogin(theLogin);
		System.out.println("Digite sua Senha: ");
		String thePsw = scan.nextLine();
		activeUser.setPsw(thePsw);
		System.out.println("Digite seu Nome: ");
		String theName = scan.nextLine();
		activeUser.setName(theName);

		accounts.put(theName, activeUser);

		}
	}

