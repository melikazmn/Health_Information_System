import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    Scanner input = new Scanner(System.in);

    public Admin(String name, String lastName, Enum.Sex sex, String userName, String password) {
        super(Enum.User.Admin, name, lastName, sex, userName, password);
    }

    public User addPhysician(ArrayList<String> docField) {
        System.out.println("> name :");
        String name = input.next();
        System.out.println("> last name :");
        String lastName = input.next();
        System.out.println("> gender :1. Female 2. Male");
        int sChoice = input.nextInt();

        Enum.Sex sx;
        if (sChoice == 1) {
            sx = Enum.Sex.Female;
        } else
            sx = Enum.Sex.Male;

        System.out.println("> username :");
        String username = input.next();
        System.out.println("> password :");
        String password = input.next();

        System.out.println("> Choose one of the fields");
        for (int i = 0; i < docField.size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(docField.get(i));
        }
        int fChoice = input.nextInt();
        return new Doctor(name, lastName, sx, username, password, docField.get(fChoice - 1));
    }

    public User addNurse() {
        System.out.println("> name :");
        String name = input.next();
        System.out.println("> last name :");
        String lastName = input.next();
        System.out.println("> gender :1. Female 2. Male");
        int sChoice = input.nextInt();

        Enum.Sex sx;
        if (sChoice == 1) {
            sx = Enum.Sex.Female;
        } else
            sx = Enum.Sex.Male;

        System.out.println("> username :");
        String username = input.next();
        System.out.println("> password :");
        String password = input.next();
        System.out.println("> record :");

        input.nextLine();
        String record = input.nextLine();
        return new Nurse(name, lastName, sx, record, username, password);
    }

    public User addPatient() {
        Enum.Mode mode;

        System.out.println("> name :");
        String name = input.next();
        System.out.println("> last name :");
        String lastName = input.next();
        System.out.println("> gender :1. Female 2. Male");
        int sChoice = input.nextInt();

        Enum.Sex sx;
        if (sChoice == 1) {
            sx = Enum.Sex.Female;
        } else
            sx = Enum.Sex.Male;
        System.out.println("> username :");
        String username = input.next();
        System.out.println("> password :");
        String password = input.next();
        System.out.println("> disease :");

        input.nextLine();
        String disease = input.nextLine();
        System.out.println("> Mode :1. VIP  2. Normal  3.Insurance");
        int mChoice = input.nextInt();
        if (mChoice == 1)
            mode = Enum.Mode.VIP;
        else if (mChoice == 2)
            mode = Enum.Mode.Normal;
        else
            mode = Enum.Mode.Insurance;

        System.out.println("> age :");
        int age = input.nextInt();

        Date date = new Date(0, 0, 0);
        System.out.println("> Enter The Arrival Day : ");
        System.out.println("> Day :");
        date.day = input.nextInt();
        System.out.println("> Month :");
        date.month = input.nextInt();
        System.out.println("> Year :");
        date.year = input.nextInt();

        return new Patient(name, lastName, age, sx, disease, mode, username, password, date);
    }

    public ArrayList<User> deleteUser(ArrayList<User> userList, ArrayList<Doctor> docList, ArrayList<Nurse> nurseList, ArrayList<Patient> patientList) {
        showUser(userList);
        System.out.println("> Enter the ID you want to delete.");
        int id = input.nextInt();

        for (int i = 0; i < userList.size(); i++)
            if (userList.get(i).getId() == id)
                userList.remove(i);
        for (int i = 0; i < docList.size(); i++)
            if (docList.get(i).getId() == id)
                docList.remove(i);
        for (int i = 0; i < nurseList.size(); i++)
            if (nurseList.get(i).getId() == id)
                nurseList.remove(i);
        for (int i = 0; i < patientList.size(); i++)
            if (patientList.get(i).getId() == id)
                patientList.remove(i);
        return userList;
    }

    public void showUser(ArrayList<User> userList) {
        StringBuilder strBuilder = new StringBuilder();
        System.out.println("ID. Name LastName.\n");
        for (int i = 0; i < userList.size(); i++) {
            strBuilder.append(userList.get(i).getId());
            strBuilder.append(".");
            strBuilder.append(userList.get(i).getName());
            strBuilder.append(" ");
            strBuilder.append(userList.get(i).getLastName());
            strBuilder.append("\n");
        }
        System.out.println(strBuilder.toString());
    }

    public ArrayList<User> searchUser(ArrayList<User> userList) {
        System.out.println("> Enter the last name you are looking for.");
        String ln = input.next();
        ArrayList<User> searchUser = new ArrayList<User>();
        for (User us : userList) {
            if (us.getLastName().toLowerCase().contains(ln.toLowerCase()))
                searchUser.add(us);
        }
        return searchUser;
    }


}
