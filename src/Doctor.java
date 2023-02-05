import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Doctor extends User {
    private String Field;
    private ArrayList<Patient> patientList = new ArrayList<Patient>();
    private ArrayList<Patient> archivePatients = new ArrayList<Patient>();

    public Doctor(String name, String lastName, Enum.Sex sex, String userName, String password, String Field) {
        super(Enum.User.Physician, name, lastName, sex, userName, password);
        this.Field = Field;
    }

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(ArrayList<Patient> patientList) {
        this.patientList = patientList;
    }

    public ArrayList<Patient> getArchivePatients() {
        return archivePatients;
    }

    public void setArchivePatients(ArrayList<Patient> archivePatients) {
        this.archivePatients = archivePatients;
    }

    public void addPatient(Patient patient) {
        patientList.add(patient);
        patient.showInfo();
    }

    public void pickPatient(ArrayList<String> docField, ArrayList<String> patientField, ArrayList<Patient> pl) {
        int index = 0;
        for (int i = 0; i < docField.size(); i++) {
            if (this.getField() == docField.get(i))
                index = i;
        }
        System.out.println("> Choose Patient By ID : ");
        for (Patient p : pl)
            if (p.getState() == Enum.State.NotPicked) {
                if (patientField.get(index).contains(p.getDisease()))
                    p.showInfo();
            }
        int id = input.nextInt();

        for (Patient p : pl)
            if (p.getId() == id) {
                this.addPatient(p);
                p.setState(Enum.State.Picked);
            }

    }


    public void searchPatient(ArrayList<Patient> patientList) {
        Scanner input = new Scanner(System.in);
        ArrayList<User> searchUser = new ArrayList<User>();
        System.out.println("> How do you want to search ? 1.ID  2.name/lastname ");
        int choice = input.nextInt();
        if (choice == 1) {
            int id;
            System.out.println("> ID : ");
            id = input.nextInt();
            for (Patient p : patientList)
                if (p.getId() == id)
                    p.showInfo();
        } else {
            System.out.println("> Name/Lastname : ");
            String searchChar = input.next();
            for (Patient p : patientList) {
                if (p.getLastName().toLowerCase().contains(searchChar.toLowerCase()) || p.getName().toLowerCase().contains(searchChar.toLowerCase()))
                    p.showInfo();
            }
        }
    }

    public void dischargePatient(ArrayList<Patient> pList) {
        Scanner input = new Scanner(System.in);
        System.out.println("> List of all of your patients :");
        for (Patient p : patientList)
            p.showInfo();
        System.out.println("> Enter the ID of the patient you want to discharge : ");
        int id = input.nextInt();
        for (Patient p : pList)
            if (p.getId() == id) {
                p.showInfo();
                archivePatients.add(p);
                p.setState(Enum.State.Discharged);
            }
    }

    public void writeMedicine(Patient p) {
        System.out.println("> Doctor : " + p.getName() + " should take 2 cups of chaee nabat :)");
        p.setMedicine("chaee nabat");
    }


}
