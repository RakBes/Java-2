/**
 * Java. Level 2; Lesson 3; HomeWork3
 *
 * @coauthor: Раков Валерий;
 * @version dated: 24 июля 2019 года
 */
import java.util.*;

public class MainClass {

    public static void main(String[] args) {
        task1();
        task2();
    }

    private static void task1() {
        Map<String, Integer> hm = new HashMap<>();
        String[] words = {
                "Coffee", "Potato", "Alpha",
                "Cheese", "Beta", "Humster",
                "Dog", "Cat", "Java",
                "Yava", "Kent", "Coffee",
                "Dog", "Beta", "Humster",
                "Cat", "Java", "Yava", "Dog"
        };

        for (int i = 0; i < words.length; i++) {
            if (hm.containsKey(words[i]))
                hm.put(words[i], hm.get(words[i]) + 1);
            else
                hm.put(words[i], 1);
        }
        System.out.println(hm);
    }

    private static void task2() {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("antonyan", "8999123321");
        phoneBook.add("antonyan", "8912155326");
        phoneBook.add("bobov", "8917155552");
        phoneBook.add("bobov", "8913455672");
        phoneBook.add("antonyan", "899999999");
        phoneBook.add("igoryan", "899111111");
        phoneBook.add("bobov", "89923231999");
        phoneBook.add("smetanov", "8888123113");
        phoneBook.add("igoryan", "8324325234");

        System.out.println(phoneBook.get("antonyan"));
        System.out.println(phoneBook.get("igoryan"));
        System.out.println(phoneBook.get("bobov"));
        System.out.println(phoneBook.get("smetanov"));
    }
}

class PhoneBook {
    private Map<String, List<String>> directory_hm = new HashMap<>();
    private List<String> phone_number_list;

    public void add(String surname, String phone_number) {
        if (directory_hm.containsKey(surname)) {
            phone_number_list = directory_hm.get(surname);
            phone_number_list.add(phone_number);
            directory_hm.put(surname, phone_number_list);
        } else {
            phone_number_list = new ArrayList<>();
            phone_number_list.add(phone_number);
            directory_hm.put(surname, phone_number_list);
        }
    }

    public List<String> get(String surname) {
        return directory_hm.get(surname);
    }

}