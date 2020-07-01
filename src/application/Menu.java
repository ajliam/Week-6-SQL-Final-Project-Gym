package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.ClassesDao;
import dao.ClassesScheduledDao;
import dao.GymDao;
import dao.MembershipDao;
import dao.TrainerDao;


public class Menu {
	private GymDao gymDao = new GymDao();
	private TrainerDao trainerDao = new TrainerDao();
	private MembershipDao memberDao = new MembershipDao();
	private ClassesDao classesDao = new ClassesDao();
	private ClassesScheduledDao classesScheduledDao = new ClassesScheduledDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display gym information", 
			"Display fitness classes",
			"Display fitness instructors",
			"Display fitness center members",
			"Display the roster for a fitness class",
			"Add a member to a fitness class",
			"Add a member to the gym",
			"Add a fitness class",
			"Update a member's information",
			"Update a fitness class",
			"Remove a fitness center member",
			"Remove a member from a class",
			"Remove a fitness class");
	
	public void start() {
		
		String selection = "";

		do {
			printMenu();
			selection = scanner.nextLine();
			
//		try {
//			if (selection.equals("1")) {
//				displayGymInfo();
//			} else if (selection.equals("2")) {
//				displayClasses();
//			} else if (selection.equals("3")) {
//				displayTrainers();
//			} else if (selection.equals("4")) {
//				displayMembers();
//			} else if (selection.equals("5")) {
//				displayClassRoster();
//			} else if (selection.equals("6")) {
//				addMembertoClass();
//			} else if (selection.equals("7")) {
//				addMembertoGym();
//			}  else if (selection.equals("8")) {
//				addFitnessClass();
//			}  else if (selection.equals("9")) {
//				updateMember();
//			} else if (selection.equals("10")) {
//				updateClass();
//			} else if (selection.equals("11")) {
//				deleteMember();
//			} else if (selection.equals("12")) {
//				deleteMemberFromClass();
//			}  else if (selection.equals("13")) {
//				deleteClass();
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
			System.out.println("Press enter to continue...");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));
	
	}

	private void printMenu() {
		System.out.println("Select an Option:\n-------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ")" + options.get(i));
		}
	}
	
}
