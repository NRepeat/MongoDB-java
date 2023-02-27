import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {

            Scanner input = new Scanner(System.in);
            Admin admin = new Admin();

            int choice = 10;

            while (choice != 0) {
                System.out.println(
                        "Choose the option (enter number):\n 1. Add Reservation \n 2. Add Check \n 3. Delete \n 4. View \n 5. Save \n 6. Load \n 7. Edit \n 0. Exit\n");
                choice = Integer.parseInt(input.next());
                switch (choice) {
                    case 1:

                        System.out.println("Enter the Customert's Name");
                        String name = input.next();
                        System.out.println("Enter the Customert's ID");
                        Integer iDInteger = Integer.parseInt(input.next());
                        System.out.println("Enter the Customert's rooms seperated by comma");
                        String roomString = input.next();
                        String[] room = roomString.split(",");
                        admin.add(iDInteger, name, room);
                        break;
                    case 7:
                        System.out.println("Enter the Customert's ID");
                        Integer editIDInteger = Integer.parseInt(input.next());
                        admin.edit(editIDInteger);
                        break;
                    case 3:
                        System.out.println("Enter the Customert's ID");
                        Integer deleteIDInteger = Integer.parseInt(input.next());
                        admin.delete(deleteIDInteger);
                        break;
                    case 4:
                        admin.view();
                        break;
                    case 5:
                        System.out.println("Saving");
                        try {
                            admin.save();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        System.out.println("Loading");
                        try {
                            admin.load();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        System.out.println("Enter the Check date");
                        String check_date = input.next();
                        System.out.println("Enter the Check ID");
                        Integer check_id = Integer.parseInt(input.next());
                        System.out.println("Enter the Total Sum");
                        String price = input.next();
                        String[] prices = price.split(",");
                        admin.check(check_id , check_date, prices);
                        break;
                    case 0:
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
            }

            if (choice == 0) {
                input.close();
                System.exit(0);
            }

        }

    }

