public class Toy {
    int id;
    String name;
    int quantity;
    String chance;

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s", id, name,quantity,chance);
    }


    public double getChance() {
        return Double.parseDouble(chance);
    }

    public String getName() {
        return name;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public String toyInfo() {
        System.out.printf("ID: %d\nИгрушка: %s\nКоличество: %d\n" +
                "Шанс на выигрыш: %s\n", id, name,quantity,chance);
        return "";
    }
}
