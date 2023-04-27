import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClassFromLottery {
    static int id = 1;
    static List<Toy> list1 = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберете:\n1 - добавить игрушку\n2 - начать лотерею\n" +
                    "3 - получить игрушку\n4 - посмотреть добавленные игрушки\n" +
                    "0 - выйти из программы");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                addToy();
            } else if (choice.equals("2")) {
                winToy((ArrayList<Toy>) list1);
            } else if (choice.equals("3")) {
                System.out.println("Выигранные игрушки:");
                System.out.println(winList);
                getWinningToy(winList);
            } else if (choice.equals("4")) {
                lookAddingToys(list1);
            } else if (choice.equals("0"))
                break;
        }
    }

    private static void lookAddingToys(List<Toy> list1) {
        for (Toy toys:list1) {
            System.out.println(toys.toyInfo());
        }
    }


    private static List<Toy> addToy() {
        Scanner inputName = new Scanner(System.in);
        System.out.println("Введите игрушку(мяч, кот, конструктор и т.п.): ");
        String name = inputName.nextLine();
        Scanner inputQuantity = new Scanner(System.in);
        System.out.println("Введите количество игрушек: ");
        int quantity = inputQuantity.nextInt();
        Scanner inputChance = new Scanner(System.in);
        System.out.println("Введите частоту выпадения игрушки(в % от 100): ");
        String chance = inputChance.nextLine();

        Toy toy = new Toy();
        toy.id = id++;
        toy.name = name;
        toy.quantity = quantity;
        toy.chance = chance;

        list1.add(toy);
        return list1;
    }

    static List<String> winList = new ArrayList<>();

    private static String winToy(ArrayList<Toy> inputList) {
        boolean found = false;
        for (Toy toys : inputList) {
            double random = Math.random() * 100;
            if (random < toys.getChance()) {
                found = true;
                if (toys.quantity <= 0) {
                    System.out.println("Попробуйте еще раз");
                } else {
                    winList.add(toys.getName());
                    toys.quantity = toys.getQuantity() - 1;
                    System.out.printf("Вы выиграли %s\n", toys.name);
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Вы не выиграли");
        }
        return winList.toString();
    }

    static void getWinningToy(List<String> winLst) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите желаемую игрушку: ");
        String getToy = sc.nextLine();
        for (String toys : winLst) {
            if (toys.equals(getToy)) {
                winLst.remove(toys);
                System.out.println(winLst);
                try (FileWriter writer = new FileWriter("src/main/java/" +
                        "winning_toys.txt", true)) {
                    writer.write(toys + "\n");
                } catch (Exception ex) {
                    System.out.println("Ошибка записи файла");
                }
                break;
            }
        }
    }
}

