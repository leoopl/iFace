package iFace;

import java.util.HashMap;
import java.util.Scanner;

import dao.CommunityDAO;
import dao.AccountDAO;
import dao.ProfileDAO;
import start.Main;

public class Community {
	
	private static HashMap<String, CommunityDAO> communities;
	
	public Community() {
		communities = new HashMap<>();
	}
	
	public void searchCommunite(AccountDAO activeUser) {
		Scanner input = new Scanner(System.in);
		if (activeUser == null || activeUser.getProfile() == null) {
			System.out.println("Conta n�o conectada ou Perfil n�o criado");
			return;
		}
		System.out.println(communities.keySet());
		System.out.println("Digite o nome da comunidade na qual voc� deseja entrar: ");
		String communiteName = input.nextLine();
		if (communities.containsKey(communiteName)) {
			if(communities.get(communiteName).getMembers().contains(activeUser.getName())) {
				System.out.println("voc�s ja esta na comunidade.");
			} else {
				activeUser.getProfile().getMyCommunities().add(communiteName);
				communities.get(communiteName).getMembers().add(activeUser.getName());
				System.out.println("Voc� acaba de entrar na " + communiteName);
			}
		} else {
			System.out.println("Comunidade inexistente");
		}
	}

	public void createComunite(AccountDAO activeUser) {
		Scanner input = new Scanner(System.in);
		if (activeUser == null || activeUser.getProfile() == null) {
			System.out.println("Conta n�o logada ou Perfil n�o criado");
			return;
		}
		CommunityDAO c = new CommunityDAO();
		System.out.println("Digite o Nome da Comunidade: ");
		String comunitName = input.nextLine();
		c.setName(comunitName);
		System.out.println("Digite a Descri��o da Comunidade: ");
		String comunitDesc = input.nextLine();
		c.setDesc(comunitDesc);
		c.setOwner(activeUser.getName());
		activeUser.getProfile().getMyCommunities().add(comunitName);
		communities.put(comunitName, c);
		communities.get(comunitName).getMembers().add(activeUser.getName());
		System.out.println("Comunidade Criada com sucesso!");
	}

}
