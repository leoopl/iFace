package start;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import dao.AccountDAO;
import dao.CommunityDAO;
import dao.FriendshipRequest;
import dao.Message;
import dao.ProfileDAO;
import iFace.Account;
import iFace.Community;
import iFace.Profile;

public class Main {
	
	

	public static void main(String[] args) {
		
		AccountDAO activeUser = new AccountDAO();
		
		Scanner scan = new Scanner(System.in);
		
		Account accounts = new Account();
		Profile profiles = new Profile();
		Community communits = new Community();
		HashMap<String, AccountDAO> systemAccounts = accounts.getAccounts();


		int input;
		try {
			do {
				System.out.println();
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
				System.out.println("[11] Logoff");
				System.out.println("[0] Sair");
				input = scan.nextInt();

				switch (input) {
				case 1:
					accounts.createAccount();
					break;
				case 2:
					profiles.createProfile(activeUser, accounts);
					break;
				case 3:
					profiles.editProfile(activeUser);
					break;
				case 4:
					sendFriendshipRequest(accounts, activeUser);
					break;
				case 5:
					sendMessage(accounts, activeUser);
					break;
				case 6:
					communits.createComunite(activeUser);
					break;
				case 7:
					communits.searchCommunite(activeUser);
					break;
				case 8:
					profiles.profileInfo(activeUser);
					break;
				case 9:
					accounts.removeAccount(activeUser);
					break;
				case 10:
					login(activeUser, accounts);
					break;
				case 11:
					activeUser = null;
					break;
				case 0:
					System.out.println("bye bye!");
					break;
				default:
					System.out.println("Operação invalida");

				}

			} while (input != 0);
		} catch (Exception e) {
			System.out.println("Tente outra vez!!!");
		}
		

	}


	private static void sendMessage(Account accounts, AccountDAO activeUser) {
		Scanner input = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Você precisa antes conectar a uma conta");
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
			try {
				accounts.getAccounts().get(receiverName).getProfile().getMyMenssage().put(activeUser.getName(), newMsg);
			} catch (Exception e) {
				System.out.println("A pessoa no qual você tentou enviar a mensagem ainda não criou seu perfil");
			}
		} else {
			System.out.println("Usuario inexistente");
		}
	}

	private static void sendFriendshipRequest(Account accounts, AccountDAO activeUser) {
		Scanner input = new Scanner(System.in);
		if (activeUser == null || activeUser.getProfile() == null) {
			System.out.println("Você precisa antes criar uma conta e seu perfil");
			return;
		}
		FriendshipRequest newRequest = new FriendshipRequest();
		System.out.println("Digite o nome de quem você deseja adicionar: ");
		String requested = input.nextLine();
		if (accounts.containsKey(requested)) {
			if (activeUser.getProfile().getFriendName().contains(requested)) {
				System.out.println("Vocês já são amigos!");
			} else {
				try {
					newRequest.sendRequest(activeUser, accounts.getAccounts().get(requested));
					accounts.getAccounts().get(requested).getProfile().getFriendshipRequest().add(newRequest);
				} catch (Exception e) {
					System.out.println("O amigo que você tentou adicionar não tem perfil!");
				}
			}
		} else {
			System.out.println("Pessoa inexistente.");
		}
	}

	private static void login(AccountDAO activeUser, Account accounts) {
		Scanner input = new Scanner(System.in);
		System.out.println("Login: ");
		String login = input.nextLine();
		System.out.println("Senha: ");
		String thePsw = input.nextLine();
		if (searchUser(login, thePsw, activeUser, accounts)) {
			System.out.println("Logado");
		} else {
			System.out.println("Login ou Senha invalido");
		}

	}

	private static boolean searchUser(String login, String senha, AccountDAO activeUser, Account accounts) {
		Scanner input = new Scanner(System.in);
		for (String key : accounts.getAccounts().keySet()) {
			AccountDAO acc = accounts.getAccounts().get(key);
			if (acc.getLogin().equals(login) && acc.getPsw().equals(senha)) {
				activeUser = acc;

				if (activeUser.getProfile() != null) {
					if (acc.getProfile().getFriendshipRequest().size() > 0) {
						System.out.println(acc.getProfile().getFriendshipRequest());
						System.out.println("deseja aceitar algum pedido? S - N");
						String confirm = input.nextLine();
						if (confirm.toLowerCase().contains("sim")) {
							acceptFrindship(activeUser, accounts);
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	private static void acceptFrindship(AccountDAO activeUser, Account accounts) {
		Scanner input = new Scanner(System.in);
		System.out.println("Digite o nome do amigo, que gostaria aceitar: ");
		String name = input.nextLine();
		boolean exist = false;
		FriendshipRequest aux = null;
		for (FriendshipRequest senderName : activeUser.getProfile().getFriendshipRequest()) {
			if (senderName.getSenderName().equals(name)) {
				activeUser.getProfile().getFriendName().add(name);
				accounts.getAccounts().get(name).getProfile().getFriendName().add(activeUser.getName());
				exist = true;
				aux = senderName;
			}
		}
		if (!exist) {
			System.out.println("nome de usuario não existente na sua lista de convites.");
		} else {
			activeUser.getProfile().getFriendshipRequest().remove(aux);
		}
	}

	private void aceptFriend(AccountDAO activeUser) {
		Scanner input = new Scanner(System.in);
		if (activeUser == null || activeUser.getProfile() == null) {
			System.out.println("Você precisa antes criar uma conta e seu perfil");
			return;
		}

	}

}
