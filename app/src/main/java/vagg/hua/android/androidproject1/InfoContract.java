package vagg.hua.android.androidproject1;

public class InfoContract {

    private String fname;
    private String lname;
    private int age;

    public InfoContract (String fname, String lname, int age) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }
}
