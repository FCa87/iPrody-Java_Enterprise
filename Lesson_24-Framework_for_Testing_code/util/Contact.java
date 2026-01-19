package util;

public class Contact {
    private final int id;
    private final String name;
    private final String number;
    private final Contact friend;

    public Contact(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.friend = null;
    }
    
    public Contact(int id, String name, String number, Contact friend) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.friend = friend;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Contact getFriend() {
        return friend;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", name=" + name + ", number=" + number + ", friend=" + friend + '}';
    }
    
}
