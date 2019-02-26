package iFace;

import java.util.Scanner;

import dao.AccountDAO;
import dao.ProfileDAO;
import start.Main;

public class Profile {
	
	public void profileInfo(AccountDAO activeUser) {
		if (activeUser == null || activeUser.getProfile() == null) {
			System.out.println("Você precisa conectar ou criar seu perfil antes!");
			return;
		}
		System.out.println(activeUser.getProfile().toString());
	}
	
	public void editProfile(AccountDAO activeUser) {
		Scanner intinput = new Scanner(System.in);
		Scanner stringinput = new Scanner(System.in);
		if (activeUser == null) {
			System.out.println("Conta não conectada");
			return;
		}
		if (activeUser.getProfile() != null) {
			int input;
			do {
				System.out.println(
						"O que você quer editar?\n [1] Nome\n [2] Idade\n [3] Gênero\n [0] Voltar ao menu principal");
				input = intinput.nextInt();
				switch (input) {
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
				
				
			} while (input != 0);
		} else {
			System.out.println("Perfil ainda não foi criado");
		}
	}

	public void createProfile(AccountDAO activeUser, Account accounts) {
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
			ProfileDAO p = new ProfileDAO(user, age, sex);
			activeUser.setProfile(p);
			accounts.getAccounts().replace(activeUser.getName(), activeUser);
			System.out.println("Perfil criado com sucesso!");
		} else {
			System.out.println("Perfil já existente!");
		}
	}


}
