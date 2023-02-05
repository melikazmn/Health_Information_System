import java.util.ArrayList;

public class Nurse extends User {
    private String Record;

    public Nurse(String name, String lastName, Enum.Sex sex, String record, String userName, String password) {
        super(Enum.User.Nurse, name, lastName, sex, userName, password);
        Record = record;
    }

    public String getRecord() {
        return Record;
    }

    public void setRecord(String record) {
        Record = record;
    }

    public void checkNotPickedPatients(ArrayList<Patient> patientList) {
        System.out.println("> These Patients Are Not Picked : ");
        for (Patient p : patientList) {
            if (p.getState() == Enum.State.NotPicked)
                p.showInfo();
        }
    }

    public void dischargePatients(ArrayList<Patient> patientList) {
        System.out.println("> These Patients Have Permission To Discharge : ");
        for (Patient p : patientList) {
            if (p.getState() == Enum.State.Discharged)
                p.showInfo();
        }
    }

    public void checkIn(ArrayList<Patient> pl) {

        Date date1 = new Date(0, 0, 0);
        Date date2 = new Date(0, 0, 0);
        System.out.println("> Date 1 Year : ");
        date1.year = input.nextInt();
        System.out.println("> Date 1 Month : ");
        date1.month = input.nextInt();
        System.out.println("> Date 1 Day : ");
        date1.day = input.nextInt();

        System.out.println("> Date 2 Year : ");
        date2.year = input.nextInt();
        System.out.println("> Date 2 Month : ");
        date2.month = input.nextInt();
        System.out.println("> Date 2 Day : ");
        date2.day = input.nextInt();

        for (Patient p : pl) {
            if (date1.year == date2.year) {
                if (date1.month == date2.month) {
                    if (p.getDayArrive().year == date1.year && p.getDayArrive().month == date1.month && date1.day < p.getDayArrive().day && p.getDayArrive().day < date2.day)
                        p.showInfo();
                } else {
                    if (date1.month == p.getDayArrive().month && p.getDayArrive().day > date1.day)
                        p.showInfo();
                    else if (date2.month == p.getDayArrive().month && p.getDayArrive().day < date2.day)
                        p.showInfo();
                    else if (date1.month < p.getDayArrive().month && date2.month > p.getDayArrive().month)
                        p.showInfo();
                }
            } else {
                if (p.getDayArrive().year == date1.year && date1.month < p.getDayArrive().month)
                    p.showInfo();
                else if (p.getDayArrive().year == date1.year && date1.month == p.getDayArrive().month && p.getDayArrive().day > date1.day)
                    p.showInfo();
                else if (p.getDayArrive().year == date2.year && date2.month > p.getDayArrive().month)
                    p.showInfo();
                else if (p.getDayArrive().year == date2.year && date2.month == p.getDayArrive().month && p.getDayArrive().day < date1.day)
                    p.showInfo();
            }

        }

    }

    public void getPrescription(ArrayList<Patient> pl, ArrayList<Doctor> drl) {
        System.out.println("> Choose a patient by ID to send a message to his/her doctor : ");
        for (Patient p : pl) {
            p.showInfo();
        }
        int id = input.nextInt();
        for (Doctor dr : drl)
            for (Patient p : dr.getPatientList())
                if (p.getId() == id) {
                    System.out.println("> massege sent to dr " + dr.getLastName() + " to get medicine for " + p.getName() + " .");
                    dr.writeMedicine(p);
                }

    }

}
