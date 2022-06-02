package beans;

public enum Category {
    Food(1),
    Electricity(2),
    Restaurant(3),
    Vacation(4);

    private int id;
    Category(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
