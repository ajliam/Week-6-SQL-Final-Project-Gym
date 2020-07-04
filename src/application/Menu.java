package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//import dao.ClassesDao;
//import dao.ClassesScheduledDao;
import dao.GymDao;
import dao.MembershipDao;
import dao.TrainerDao;
import entity.Trainer;
import entity.Gym;
import entity.Membership;



public class Menu {
	private GymDao gymDao = new GymDao();
	private TrainerDao trainerDao = new TrainerDao();
	private MembershipDao membershipDao = new MembershipDao();
//	private ClassesDao classesDao = new ClassesDao();
//	private ClassesScheduledDao classesScheduledDao = new ClassesScheduledDao();

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
			"Add a fitness instructor",
			"Update a member's information",
			"Update a fitness class",
			"Update a fitness instructor",
			"Remove a fitness center member",
			"Remove a member from a class",
			"Remove a fitness class",
			"Remove a fitness instructor");
	
	public void start() {
		
		String selection = "";

		do {
			printMenu();
			System.out.print("\nPlease enter option: ");
			selection = scanner.nextLine();
	
			try {
				if (selection.equals("1")) {
					displayGymInfo();
				} else if (selection.equals("2")) {
	//				displayClasses();
				} else if (selection.equals("3")) {
					displayTrainers();
				} else if (selection.equals("4")) {
					displayMembers();
	//			} else if (selection.equals("5")) {
	//				displayClassRoster();
	//			} else if (selection.equals("6")) {
	//				addMembertoClass();
				} else if (selection.equals("7")) {
					addMemberToGym();
	//			}  else if (selection.equals("8")) {
	//				addFitnessClass();
				}  else if (selection.contentEquals("9")) {
						addFitnessInstructor();
				}  else if (selection.equals("10")) {
					updateMember();
	//			} else if (selection.equals("11")) {
	//				updateClass();
				}  else if (selection.contentEquals("12")) {
						updateFitnessInstructor();
				} else if (selection.equals("13")) {
					removeMember();
	//			} else if (selection.equals("14")) {
	//				deleteMemberFromClass();
	//			}  else if (selection.equals("15")) {
	//				deleteClass();
				}  else if (selection.equals("16")) {
					removeFitnessInstructor();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
				System.out.println("Press enter to continue...");
				selection =  scanner.nextLine();
				

		} while (!selection.equals("-1"));
	
	}

	

	private void printMenu() {
		System.out.println("Select an Option:\n-------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ")" + options.get(i));
		}
	}
	
	private void displayTrainers() throws SQLException {
		List<Trainer> listTrainers = trainerDao.Trainers();
		System.out.println("\nTrainers:");
		for (Trainer trainer : listTrainers) {
			System.out.println("ID: " + trainer.getTrainerId() + ")   Name: " + trainer.getFirstName() + " " 
								+ trainer.getLastName() + "   Gym ID: " + trainer.getGym_ID() + "   Gym Name: "   );
		}
		System.out.println();
	}
	
	private void addFitnessInstructor() throws SQLException {
		System.out.println("Add Fitness Instructor");
		System.out.print("Enter trainer's first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter trainer's last name: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter gym code: ");
		int gymCode = scanner.nextInt();
		trainerDao.addNewTrainer(firstName, lastName, gymCode);
	}
	
	private void updateFitnessInstructor() throws SQLException {
		displayTrainers();
		System.out.println("\nUpdate A Fitness Instructor:");
		System.out.print("Enter instructor's ID: ");
		int instructorID = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter instructor's first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter instructor's last name: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter assigned gym ID: ");
		int gymID = Integer.parseInt(scanner.nextLine());
		trainerDao.updateTrainer(instructorID, firstName, lastName, gymID);
	}
	

	private void removeFitnessInstructor() throws SQLException {
		displayTrainers();
		System.out.println("Remove A Fitness Instructor:");
		System.out.print("Enter the instructor's ID: ");
		int instructorID = Integer.parseInt(scanner.nextLine());
		trainerDao.deleteTrainerById(instructorID);
		displayTrainers();
	}

	private void displayGymInfo() throws SQLException {
		List<Gym> gyms = gymDao.getGymInfo();
		for(Gym gym: gyms) {
			System.out.println("Gym ID: " + gym.getGymId() + "\nName: " + gym.getGymName() + "\nAddress: " + gym.getAddress() + " " + gym.getCity() + ", " + gym.getState() + " " + gym.getZipCode());
		}
	}
	
	private void displayMembers() throws SQLException {
		System.out.print("Enter gym ID for a list of members: ");
		int id = Integer.parseInt(scanner.nextLine());
		Gym gym = gymDao.getGymByID(id);
		for (Membership member: gym.getMembers()) {
			System.out.println("\tMember ID: "  + member.getMemberId() + ") "+ " Name: " + member.getFirstName() + " " + member.getLastName() +
			 " Phone number: " + member.getPhoneNumber() + " Birth date: " + member.getBirthDate());
		}
		
	}
	
	private void addMemberToGym() throws SQLException {
		System.out.print("Enter First Name of new member: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter Last Name of new member: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter Phone Number for new member: ");
		String phoneNumber = scanner.nextLine();
		System.out.print("Enter Birth Date for new member: ");
		String birthDate = scanner.nextLine();
		System.out.print("Enter Gym ID for new member: ");
		int gymId = Integer.parseInt(scanner.nextLine());
		membershipDao.createNewMember(firstName, lastName, phoneNumber, birthDate, gymId);
	}
	
	private void updateMember() throws SQLException {
		displayMembers();
		System.out.println("\nUpdate a Member:");
	//	System.out.print("Which member do you want to update? Enter their member ID: ");
		System.out.print("Enter members's ID: ");
		int memberId = Integer.parseInt(scanner.nextLine());
		Membership member = membershipDao.getMemberByID(memberId);
		System.out.println("\tYou are updating: Member Id: "  + member.getMemberId() + " - Name: " + member.getFirstName() + " " + member.getLastName() + ", " +
				 " Phone number: " + member.getPhoneNumber() + ", " + " Birth date: " + member.getBirthDate());
		System.out.print("Enter member's first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter member's last name: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter member's phone number: ");
		String phoneNumber = scanner.nextLine();
		System.out.println("Enter member's birth date: ");
		String birthDate = scanner.nextLine();
		membershipDao.updateMember(firstName, lastName, phoneNumber, birthDate, memberId);

	}
	
	private void removeMember() throws SQLException {
	displayMembers();
	System.out.println("--- Remove a member --- ");
	System.out.print("Enter the members's ID: ");
	int memberId = Integer.parseInt(scanner.nextLine());
	membershipDao.deleteMemberById(memberId);
}
	
}
