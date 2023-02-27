import com.google.gson.Gson;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

public class DBmongo {
    public String collectionName = "StudentList";
    MongoClient mongoClient = MongoClients.create();
    Scanner input = new Scanner(System.in);
    MongoDatabase database = mongoClient.getDatabase("studentRegister");
    MongoCollection<Document> collection = database.getCollection(collectionName);
    Gson gson = new Gson();

    public void saveHashMap(ArrayList<Document> documents) {
        {

            collection = database.getCollection(collectionName);
            collection.insertMany(documents);


        }
    }


    public void saveRoomCheck(ArrayList<Document> Check) {
        collection = database.getCollection("Room check");
        collection.insertMany(Check);

    }

    public ArrayList<Customer> loadHashMap() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        if (collection.countDocuments() <=0) {
            System.out.println("Collection Emplty, Cannot Load");
        }
        else {
            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    customers.add(gson.fromJson(cursor.next().toJson(), Customer.class));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                cursor.close();
            }
        }

        return customers;
    }
}
