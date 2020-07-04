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
import entity.ClassesScheduled;
import entity.Trainer;
import entity.Gym;
import entity.Membership;

public class Menu {
	private GymDao gymDao = new GymDao();
	private TrainerDao trainerDao = new TrainerDao();

	private MembershipDao membershipDao = new MembershipDao();
	private ClassesDao classesDao = new ClassesDao();
	private ClassesScheduledDao classesScheduledDao = new ClassesScheduledDao();

	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display gym information", 
			"Display fitness classes",
			"Display fitness instructors",
			"Display fitness center members by gym",
			"Display all fitness center members",
			"Display the roster for a fitness class",
			"Add a new gym",
			"Add a member to a fitness class",
			"Add a member to the gym",
			"Add a fitness class",
			"Add a fitness instructor",
			"Update a gym's information",
			"Update a member's information",
			"Update a fitness class",
			"Update a fitness instructor\n",
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
			System.out.println();
			
			try {
				if (selection.equals("1")) {
					displayGymInfo();
				} else if (selection.equals("2")) {
					displayClasses();
				} else if (selection.equals("3")) {
					displayTrainers();
				} else if (selection.equals("4")) {
					displayMembers();
				} else if (selection.equals("5")) {
					displayAllMembers();
//				} else if (selection.equals("6")) {
//					displayClassRoster();
				} else if (selection.equals("7")) {
					addNewGym(); 
//				} else if (selection.equals("8")) {
//					addMembertoClass();
				} else if (selection.equals("9")) {
					addMemberToGym();
				}  else if (selection.equals("10")) {
					addFitnessClass();
				}  else if (selection.contentEquals("11")) {
						addFitnessInstructor();
				} else if (selection.equals("12")) {
					updateGymInfo();
				}  else if (selection.equals("13")) {
					updateMember();
				} else if (selection.equals("14")) {
					updateClass();
				}  else if (selection.contentEquals("15")) {
						updateFitnessInstructor();
				} else if (selection.equals("16")) {
					removeMember();
//				} else if (selection.equals("17")) {
//					deleteMemberFromClass();
				}  else if (selection.equals("18")) {
					deleteClass();
				}  else if (selection.equals("19")) {
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
								+ trainer.getLastName() + "   Gym: " + gymDao.getGymNameByID(trainer.getGym_ID()));
		}
		System.out.println();
	}
	private void displayClassRoster() throws SQLException {
		displayClasses();
		System.out.println("\nDisplay Schedule By Class\n");
		System.out.print("Enter class ID: ");
		int classID = Integer.parseInt(scanner.nextLine());
		System.out.println("\n" + classesDao.getClassNameByID(classID) + "\n");
		List<ClassesScheduled> listSchedule =  classesScheduledDao.ClassScheduledByID(classID);
	
		for (ClassesScheduled eachClass : listSchedule) {
			System.out.println("ScheduleID: " + eachClass.getScheduleID() + "\n     ClassID: " + eachClass.getClassID() + "   Class: " + classesDao.getClassNameByID(eachClass.getClassID())
			+ "\n     MemberID: " + eachClass.getMemberID() + "   Member: " + membershipDao.memberName(eachClass.getMemberID()) + "\n");
		}
	}
	
	
	private void displayAllScheduledClasses() throws SQLException {
		List<ClassesScheduled> listSchedule = classesScheduledDao.ClassScheduled();
		System.out.println("\nAll Scheduled Classes:\n");
		
		for (ClassesScheduled eachClass : listSchedule) {
			System.out.println("ScheduleID: " + eachClass.getScheduleID() + "\n     ClassID: " + eachClass.getClassID() + "   Class: " + classesDao.getClassNameByID(eachClass.getClassID())
							+ "\n     MemberID: " + eachClass.getMemberID() + "   Member: " + membershipDao.memberName(eachClass.getMemberID()) + "\n");
		}
		System.out.println();
	}
	
	private void addMembertoClass() throws SQLException {
		displayClasses();
		displayMembers();
		System.out.println("\nAdd A Member To A Fitness Class\n");
		System.out.print("Enter the member ID: ");
		int memberID = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the class ID: ");
		int classID = Integer.parseInt(scanner.nextLine());
		classesScheduledDao.addNewSchedule(memberID, classID);
	}

	private void deleteMemberFromClass() throws SQLException {
		displayMembers();
		System.out.println("\nRemove A Member From A Class:\n");
		System.out.print("Enter the Member ID: ");
		int memberID = Integer.parseInt(scanner.nextLine());
		
		List<ClassesScheduled> listSchedule =  classesScheduledDao.ClassScheduledByMemberID(memberID);
		
		System.out.println("\nScheduled Classes:\n");
		for (ClassesScheduled eachClass : listSchedule) {
			System.out.println("ScheduleID: " + eachClass.getScheduleID() + "\n     ClassID: " + eachClass.getClassID() + "   Class: " + classesDao.getClassNameByID(eachClass.getClassID())
			+ "\n     MemberID: " + eachClass.getMemberID() + "   Member: " + membershipDao.memberName(eachClass.getMemberID()) + "\n");
		}
		System.out.print("Enter the schedule ID: ");
		int scheduleID = Integer.parseInt(scanner.nextLine());
		trainerDao.deleteTrainerById(scheduleID);
		classesScheduledDao.deleteScheduleById(scheduleID);
		
		List<ClassesScheduled> listSchedule2 =  classesScheduledDao.ClassScheduledByMemberID(memberID);
		System.out.println("\nScheduled Classes:\n");
		for (ClassesScheduled eachClass : listSchedule2) {
			System.out.println("ScheduleID: " + eachClass.getScheduleID() + "\n     ClassID: " + eachClass.getClassID() + "   Class: " + classesDao.getClassNameByID(eachClass.getClassID())
			+ "\n     MemberID: " + eachClass.getMemberID() + "   Member: " + membershipDao.memberName(eachClass.getMemberID()) + "\n");
		}
		
		
	}
	
	private void displayClasses() throws SQLException {
		List<Classes> listClasses = classesDao.Classes();
		System.out.println("\nClasses:");
		for (Classes listClass : listClasses) {
			System.out.println("ID: " + listClass.getClassId() + ")   Name: " + listClass.getClassName() + "   Date: " + listClass.getClassDate() + "   Time: " 
								+ listClass.getStartTime() + "   Length: " + listClass.getClassLegnth() + "\nTrainer: " 
								+  trainerDao.trainerName(listClass.getTrainerId())   + "   Gym: " + gymDao.getGymNameByID(listClass.getGymId()) + "\n");
		}
	}
	
	private void updateClass() throws SQLException {
		displayGymInfo();
		displayTrainers();
		displayClasses();
		System.out.println("\nUpdate a Fitness Class:");
		System.out.print("Enter Class's ID: ");
		int classID = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter the class name: ");
		String className = scanner.nextLine();
		System.out.print("Enter the class date (2020-01-31): ");
		String classDate = scanner.nextLine();
		System.out.print("Enter the class start time: ");
		String startTime = scanner.nextLine();
		System.out.print("Enter the class length (minutes): ");
		int length = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter the trainer's ID: ");
		int trainerID = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter the gym's ID: ");
		int gymID  = Integer.parseInt(scanner.nextLine());
		classesDao.updateClass(classID, className, classDate, gymID, trainerID, startTime, length);
	}

	private void addFitnessClass() throws SQLException {
		displayGymInfo();
		displayTrainers();
		displayClasses();
		System.out.print("\nAdd a Fitness Class:");
		System.out.print("Enter the class name: ");
		String className = scanner.nextLine();
		System.out.print("Enter the class date (2020-01-31): ");
		String classDate = scanner.nextLine();
		System.out.print("Enter the class start time: ");
		String startTime = scanner.nextLine();
		System.out.print("Enter the class length (minutes): ");
		int length = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter the trainer's ID: ");
		int trainerID = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter the gym's ID: ");
		int gymID  = Integer.parseInt(scanner.nextLine());
		classesDao.addNewClass(className, classDate, gymID, trainerID, startTime, length);
	}
	
	private void deleteClass() throws SQLException {
		displayClasses();
		System.out.println("Remove A Fitness Class:");
		System.out.print("Enter the class ID: ");
		int classID = Integer.parseInt(scanner.nextLine());
		classesScheduledDao.deleteScheduleByClassId(classID);
		classesDao.deleteClassByID(classID);
		displayClasses();
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
	
	private void updateGymInfo() throws SQLException {
		System.out.println("Update Gym Information");
		displayGymInfo();
		System.out.println("\nEnter gym's ID: ");
		int gymId = Integer.parseInt(scanner.nextLine());
		Gym gym = gymDao.getGymByID(gymId);
		System.out.println("\tYou are updating Gym ID: "  + gym.getGymId() + "\n\tName: " + gym.getGymName() + "\n\tPhone number: " + gym.getPhoneNumber() +
			"\n\tAddress: " + gym.getAddress() + " " + gym.getCity() + " " + gym.getState() + " " + gym.getZipCode()); 
		System.out.println("\nEnter gym name: ");
		String gymName = scanner.nextLine();
		System.out.println("Enter gym phone number: ");
		String phoneNumber = scanner.nextLine();
		System.out.println("Enter gym street address: ");
		String address = scanner.nextLine();
		System.out.println("Enter gym's city: ");
		String city = scanner.nextLine();
		System.out.println("Enter gym's state : ");
		String state = scanner.nextLine();
		System.out.println("Enter gym's zip code : ");
		int zipCode = Integer.parseInt(scanner.nextLine());
		gymDao.updateGymByID(gymName, phoneNumber, address, city, state, zipCode, gymId);

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
		int gymId = Integer.parseInt(scanner.nextLine());
		trainerDao.updateTrainer(instructorID, firstName, lastName, gymId);
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
			System.out.println("\nGym ID: " + gym.getGymId() + "\nName: " + gym.getGymName() + "\nPhone Number: " + gym.getPhoneNumber() + "\nAddress: " + gym.getAddress() + " " + gym.getCity() + ", " + gym.getState() + " " + gym.getZipCode());
		
		}
	
	}
	
	private void displayMembers() throws SQLException {
		displayGymInfo();
		System.out.print("Enter gym ID for a list of members: ");
		int id = Integer.parseInt(scanner.nextLine());
		Gym gym = gymDao.getGymByID(id);
		for (Membership member: gym.getMembers()) {
			System.out.println("\tMember ID: "  + member.getMemberId() + ") "+ " Name: " + member.getFirstName() + " " + member.getLastName() +
			 " Phone number: " + member.getPhoneNumber() + " Birth date: " + member.getBirthDate());	
		}
	}
	
	private void displayAllMembers() throws SQLException {
		List<Membership> members = membershipDao.getAllMembers();
		System.out.print("Gym members: ");
		for (Membership member: members) {
			System.out.println("\n\tMember ID: "  + member.getMemberId() + ") "+ " Name: " + member.getFirstName() + " " + member.getLastName() +
			 " Phone number: " + member.getPhoneNumber() + " Birth date: " + member.getBirthDate());	
		}
	}
	
	private void addNewGym() throws SQLException {
		System.out.println("Add a Gym");
		System.out.print("Enter gym name: ");
		String gymName = scanner.nextLine();
		System.out.print("Enter gym's phone number: ");
		String phoneNumber = scanner.nextLine();
		System.out.print("Enter gym street address: ");
		String address = scanner.nextLine();
		System.out.print("Enter gym's city: ");
		String city = scanner.nextLine();
		System.out.print("Enter gym's state: ");
		String state = scanner.nextLine();
		System.out.print("Enter gym's zip code: ");
		int zipCode = Integer.parseInt(scanner.nextLine());
		gymDao.createNewGym(gymName, phoneNumber, address, city, state, zipCode);
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
