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
import entity.Classes;
import entity.Trainer;


public class Menu {
	private GymDao gymDao = new GymDao();
	private TrainerDao trainerDao = new TrainerDao();
//	private MembershipDao memberDao = new MembershipDao();
	private ClassesDao classesDao = new ClassesDao();
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
	//				displayGymInfo();
				} else if (selection.equals("2")) {
					displayClasses();
				} else if (selection.equals("3")) {
					displayTrainers();
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
				}  else if (selection.contentEquals("9")) {
						addFitnessInstructor();
	//			}  else if (selection.equals("10")) {
	//				updateMember();
	//			} else if (selection.equals("11")) {
	//				updateClass();
				}  else if (selection.contentEquals("12")) {
						updateFitnessInstructor();
	//			} else if (selection.equals("13")) {
	//				deleteMember();
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
								+ trainer.getLastName() + "   Gym ID: " + trainer.getGym_ID() + "   Gym Name: ");
		}
		System.out.println();
	}
	
	private void displayClasses() throws SQLException {
		List<Classes> listClasses = classesDao.Classes();
		System.out.println("\nClasses:");
		for (Classes listClass : listClasses) {
			System.out.println("ID: " + listClass.getClassId() + ")   Name: " + listClass.getClassName() + "   Date: " + listClass.getClassDate() + "   Time: " + listClass.getStartTime() + "   Length: " + listClass.getClassLegnth() +
			//					"\nTrainer: " +  classesDao.trainerName(listClass.getTrainerId())   + "   Gym: "  + "\n");
			                  "\nTrainer: " +  trainerDao.trainerName(listClass.getTrainerId())   + "   Gym: "  + "\n");
			
		}
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
	
}
