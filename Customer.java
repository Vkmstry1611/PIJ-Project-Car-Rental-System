public class Customer {
    private int id;
    private String name;
    private String contact;
    private String license;
    private int age;

    public Customer(String name, String contact, String license, int age) {
        this.name = name;
        this.contact = contact;
        this.license = license;
        this.age = age;
    }

    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getLicense() { return license; }
    public int getAge() { return age; }
}