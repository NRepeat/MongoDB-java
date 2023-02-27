import com.google.gson.Gson;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Admin  {

    HashMap<Integer, Customer> customerRegister = new HashMap<Integer, Customer>();
    HashMap<Integer, Check> roomCheck = new HashMap<Integer,Check>();
    Scanner input = new Scanner(System.in);
    Gson gson = new Gson();
    DBmongo dbManager = new DBmongo();

    public void add(Integer ID, String Name, String[] room) {
        Customer tempCustomer = new Customer(Name, ID, room);
        customerRegister.put(ID, tempCustomer);
    }


    public void edit(Integer ID) {
        Customer tempCustomer = customerRegister.get(ID);
        if (tempCustomer == null) {
            System.out.println("ID not present");
        } else {
            System.out.println(tempCustomer.toString());
            System.out.println("What do you want to edit?\n 1. Name \n 2. Room");
            int choice = Integer.parseInt(input.next());
            switch (choice) {
                case 1:
                    System.out.println("Enter new Name:\n");
                    String newName = input.next();
                    tempCustomer.setCustomerName(newName);
                    customerRegister.put(ID, tempCustomer);
                    break;
                case 2:
                    String[] roomTemp = tempCustomer.getReservation_room();
                    for (int i = 0; i < roomTemp.length; i++) {
                        System.out.println(i+"."+roomTemp[i]);
                    }
                    System.out.println("Enter which Room you want to change");
                    int change = Integer.parseInt(input.next());
                    System.out.println("Enter new Room");
                    String roomString = input.next();
                    roomTemp[change] = roomString;
                    tempCustomer.getReservation_room();
                    customerRegister.put(ID, tempCustomer);
                    break;
                default:
                    System.out.println("Wrong Input");
                    break;
            }
        }
    }

    public void delete(Integer ID) {
        Customer tempCustomer = customerRegister.remove(ID);
        if (tempCustomer == null) {
            System.out.println("ID not present, Student not Found");
        } else {
            System.out.println(tempCustomer.toString() + "deleted");
        }
    }

    public void save() throws IOException {
        System.out.println("1.Room reservation2.Room repayment\n");
        int choice = Integer.parseInt(input.next());
        switch (choice) {

            case 1:
                ArrayList<Document> documents = new ArrayList<Document>();
                for (Customer customer : customerRegister.values()) {
                    documents.add(Document.parse(gson.toJson(customer)));
                }
                dbManager.saveHashMap(documents);
                break;
            case 2:
                documents = new ArrayList<Document>();
                for (Check check : roomCheck.values()) {
                    documents.add(Document.parse(gson.toJson(check)));
                }
                dbManager.saveRoomCheck(documents);

                break;
            default:
                System.out.println("Wrong Input");
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        System.out.println("1. Database");
        int choice = Integer.parseInt(input.next());
        switch (choice) {

            case 1:
                ArrayList<Customer> customers = dbManager.loadHashMap();
                if (customers != null) {
                    customerRegister = new HashMap<Integer, Customer>();
                    for (Customer customer : customers) {
                        customerRegister.put(customer.getCustomerId(), customer);
                    }
                }
                break;
            default:
                System.out.println("Wrong Input");
                break;
        }

    }

    public void view() {
        if (customerRegister.isEmpty()) {
            System.out.println("No values inserted");
        } else {
            for (Customer customer : customerRegister.values()) {
                System.out.println(customer.toString());
            }
        }

    }

    public void check(Integer check_id, String check_date, String[] price) {
        Check tempCheck = new Check(check_date, check_id, price);
        roomCheck.put(check_id, tempCheck);
    }
}

