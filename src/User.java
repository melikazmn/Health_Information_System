import java.util.Scanner;

abstract class User {
    private Enum.User usr;
    private String name;
    private String lastName;
    private Enum.Sex sex;
    private String userName;
    private String password;
    private final Integer id;
    private static Integer idMaker = 0;

    Scanner input = new Scanner(System.in);


    public User(Enum.User usr, String name, String lastName, Enum.Sex sex, String userName, String password) {
        this.usr = usr;
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.userName = userName;
        this.password = password;
        idMaker++;
        id = idMaker;
    }

    public Enum.User getUsr() {
        return usr;
    }

    public void setUsr(Enum.User usr) {
        this.usr = usr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Enum.Sex getSex() {
        return sex;
    }

    public void setSex(Enum.Sex sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void changePassword() {
        while (true) {
            System.out.println("> new password :");
            password = input.next();
            if (password.contains("!") || password.contains("@") || password.contains("#") || password.contains("$") || password.contains("%") || password.contains("&") || password.contains("*"))
                break;
            else
                System.out.println("password should contain at least one of !@#$%&*");
        }
    }
}
