import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int choice;
        Scanner input = new Scanner(System.in);

        //list of users
        ArrayList<User> userList = new ArrayList<User>();
        ArrayList<Doctor> docList = new ArrayList<Doctor>();
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        ArrayList<Nurse> nurseList = new ArrayList<Nurse>();


        //reading from the config.txt and saving the doc fields and patients
        ArrayList<String> docField = new ArrayList<String>();
        ArrayList<String> patientField = new ArrayList<String>();
        try {
            File myObj = new File("D:\\semester 3\\advanced programming\\java\\hw3\\src\\config.txt");
            Scanner myReader = new Scanner(myObj);
            int counter = 0;
            while (myReader.hasNextLine()) {
                if (counter % 2 == 0)
                    docField.add(myReader.nextLine());
                else
                    patientField.add(myReader.nextLine());
                counter++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Admin admin = new Admin("Melika", "Zamani", Enum.Sex.Female, "admin", "admin");
        userList.add(admin);
        Doctor dctor = new Doctor("kiana", "zamani", Enum.Sex.Female, "kiki", "zamzam", docField.get(2));
        userList.add(dctor);
        docList.add(dctor);
        Patient pat1 = new Patient("setia", "kamali", 20, Enum.Sex.Female, "toothache", Enum.Mode.VIP, "seti", "kam", new Date(1,1,2023));
        userList.add(pat1);
        patientList.add(pat1);
        Patient pat2 = new Patient("ehsan", "shams", 19, Enum.Sex.Male, "knee ache", Enum.Mode.Normal, "ehsan", "shams", new Date(7,1,2023));
        userList.add(pat2);
        patientList.add(pat2);
        Nurse nrs = new Nurse("negar", "pey", Enum.Sex.Female, "2 years in a hospital", "neg", "pey");
        userList.add(nrs);
        nurseList.add(nrs);




        System.out.println(">> WELCOME TO HEALTH INFORMATION SYSTEM <<");
        while (true) {
            System.out.println(">> What Do You Want To Do ?\n1. Log in\n2. Exit");
            choice = input.nextInt();
            if (choice == 1)
                logIn(userList, docList, patientList, nurseList, docField, patientField);
            else if (choice == 2)
                break;
        }
    }

    public static void logIn(ArrayList<User> userList, ArrayList<Doctor> docList, ArrayList<Patient> patientList,
                             ArrayList<Nurse> nurseList, ArrayList<String> docField, ArrayList<String> patientField) throws InterruptedException {
        String usName;
        String psWord;
        int attempt = 0;
        boolean isWrongUserName = true;
        boolean ifExit = false;

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("> USERNAME : ");
            usName = input.next();
            System.out.print("> PASSWORD : ");
            psWord = input.next();

            for (User us : userList) {
                if (Objects.equals(us.getUserName(), usName)) {
                    isWrongUserName = false;
                    if (Objects.equals(us.getPassword(), psWord)) {
                        // go to each user menu
                        switch (us.getUsr()) {
                            case Admin:
                                Admin admin = (Admin) us;
                                ifExit = adminMenu(admin, userList, docList, patientList, nurseList, docField);
                                break;
                            case Nurse:
                                Nurse nurse = (Nurse) us;
                                ifExit = nurseMenu(nurse,patientList,docList);
                                break;
                            case Patient:
                                Patient patient = (Patient) us;
                                ifExit = patientMenu(patient);
                                break;
                            case Physician:
                                Doctor physician = (Doctor) us;
                                ifExit = physicianMenu(physician, patientList, docField, patientField);
                                break;
                        }
                        break;
                    }// end of switch case on the menus
                    System.out.println("> Wrong Password !");
                    attempt++;
                    if (attempt == 3) {
                        System.out.println("> You Have Attempted To Enter More than 2 Times. Your Account Has Been Baned For 2 Minutes!");
                        attempt = 0;
                        Thread.sleep(120000);
                    }
                }
            }//end of the for
            if (isWrongUserName == true)
                System.out.println("> Wrong Username !");
            if (ifExit == true) {
                isWrongUserName = true;
                break;
            }
        }//end While
    }


    public static boolean adminMenu(Admin admin, ArrayList<User> userList, ArrayList<Doctor> docList, ArrayList<Patient> patientList, ArrayList<Nurse> nurseList, ArrayList<String> docField) {

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(">> WELCOME TO ADMIN PANEL. WHAT DO YOU WANT TO DO?");
            System.out.println("1. List Of All Users");
            System.out.println("2. Search User");
            System.out.println("3. Adding Physician");
            System.out.println("4. Adding Nurse");
            System.out.println("5. Adding Patient");
            System.out.println("6. Delete User");
            System.out.println("7. Change Password");
            System.out.println("8. Exit");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    admin.showUser(userList);
                    break;
                case 2:
                    admin.showUser(admin.searchUser(userList));
                    break;
                case 3:
                    userList.add(admin.addPhysician(docField));
                    docList.add((Doctor) userList.get(userList.size() - 1));
                    break;
                case 4:
                    userList.add(admin.addNurse());
                    nurseList.add((Nurse) userList.get(userList.size() - 1));
                    break;
                case 5:
                    userList.add(admin.addPatient());
                    patientList.add((Patient) userList.get(userList.size() - 1));
                    break;
                case 6:
                    admin.deleteUser(userList, docList, nurseList, patientList);
                    break;
                case 7:
                    admin.changePassword();
                    break;
                case 8:
                    return true;
            }
        }//end of the while
    }


    public static boolean physicianMenu(Doctor doctor, ArrayList<Patient> patientList, ArrayList<String> docField, ArrayList<String> patientField) {
        int choice;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(">> WELCOME TO PHYSICIAN PANEL. WHAT DO YOU WANT TO DO?");
            System.out.println("1. Pick Patient");
            System.out.println("2. List of All Patients");
            System.out.println("3. View Patient Info");
            System.out.println("4. Write Medicine");
            System.out.println("5. Discharge Patient");
            System.out.println("6. Change password");
            System.out.println("7. Exit");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    doctor.pickPatient(docField, patientField, patientList);
                    break;
                case 2:
                    System.out.println("> List of all patients :");
                    for (Patient p : patientList)
                        p.showInfo();
                    break;
                case 3:
                    doctor.searchPatient(patientList);
                    break;
                case 4:
                    System.out.println("> No massage from a nurse .");
                    break;
                case 5:
                    doctor.dischargePatient(patientList);
                    break;
                case 6:
                    doctor.changePassword();
                    break;
                case 7:
                    return true;

            }
        }//end of while

    }

    public static boolean nurseMenu(Nurse nurse, ArrayList<Patient> patientList,ArrayList<Doctor> docList) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(">> WELCOME TO NURSE PANEL. WHAT DO YOU WANT TO DO?");
            System.out.println("1. Check the Patient State");
            System.out.println("2. Change password");
            System.out.println("3. Exit");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("> Choose one :");
                    System.out.println("1. Check the Patients with No Doctor Assigned");
                    System.out.println("2. Checked In");
                    System.out.println("3. Get Prescription");
                    System.out.println("4. Discharge Patient");
                    int secondChoice = input.nextInt();
                    switch (secondChoice) {
                        case 1:
                            nurse.checkNotPickedPatients(patientList);
                            break;
                        case 2:
                            nurse.checkIn(patientList);
                            break;
                        case 3:
                            nurse.getPrescription(patientList,docList);
                            break;
                        case 4:
                            nurse.dischargePatients(patientList);
                            break;
                    }
                    break;
                case 2:
                    nurse.changePassword();
                    break;
                case 3:
                    return true;

            }
        }//end of while

    }

    public static boolean patientMenu(Patient patient) {

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(">> WELCOME TO PATIENT PANEL. WHAT DO YOU WANT TO DO?");
            System.out.println("1. Check out");
            System.out.println("2. Change password");
            System.out.println("3. Exit");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    patient.CheckOut();
                    break;
                case 2:
                    patient.changePassword();
                    break;
                case 3:
                    return true;

            }
        }//end of while

    }

}

