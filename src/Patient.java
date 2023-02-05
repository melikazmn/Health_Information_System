import java.util.ArrayList;
import java.time.*;


public class Patient extends User {
    private Integer Age;
    private String Disease;
    private Enum.Mode mode;
    private Date dayArrive;
    private Enum.State state;

    private String medicine;

    public Patient(String name, String lastName, Integer age, Enum.Sex sex, String disease, Enum.Mode mode, String userName, String password, Date date) {
        super(Enum.User.Patient, name, lastName, sex, userName, password);
        Age = age;
        Disease = disease;
        this.mode = mode;
        state = Enum.State.NotPicked;
        dayArrive = date;
    }


    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public Enum.Mode getMode() {
        return mode;
    }

    public void setMode(Enum.Mode mode) {
        this.mode = mode;
    }


    public Enum.State getState() {
        return state;
    }

    public void setState(Enum.State state) {
        this.state = state;
    }

    public Date getDayArrive() {
        return dayArrive;
    }

    public void setDayArrive(Date dayArrive) {
        this.dayArrive = dayArrive;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void CheckOut() {
        LocalDate currentdate = LocalDate.now();

        int lYears = currentdate.getYear();
        int lMonths = currentdate.getMonthValue();
        int lDays = currentdate.getDayOfMonth();
        int Days;
        if (lYears == dayArrive.year) {
            if (lMonths == dayArrive.month)
                Days = lDays - dayArrive.day;
            else
                Days = 30 - dayArrive.day + (dayArrive.month - lMonths - 1) * 30 + lDays;
        } else {
            Days = 30 - dayArrive.day + (12 - dayArrive.month - 1) * 30 + (lYears - dayArrive.year) * 365 + (lMonths - 1) * 30 + lDays;
        }

        if (state == Enum.State.Discharged) {
            System.out.print("> You have spent " + Days + " days in HIS . You shoud pay  ");
            if (mode == Enum.Mode.VIP)
                System.out.println(Days * 120 + " $");
            else if (mode == Enum.Mode.Normal)
                System.out.println(Days * 70 + " $");
            else
                System.out.println(Days * 35 + " $");
        } else
            System.out.println("> Can't check out . You are not discharged !");

    }

    public void showInfo() {
        System.out.println(this.getId() + ". " + this.getName() + " " + this.getLastName() +
                " : " + this.getDisease() + " / arrived at HIS on " + this.dayArrive.year + "/" + this.dayArrive.month + "/" + this.dayArrive.day + " .");
    }


}
