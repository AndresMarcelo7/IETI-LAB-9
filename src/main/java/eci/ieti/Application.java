package eci.ieti;

import eci.ieti.configuration.AppConfiguration;
import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;

import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        
        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));
        
        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");
        
        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        	.forEach(System.out::println);
   
        System.out.println();

        todoRepository.deleteAll();


        todoRepository.save(new Todo( "description 0" , 1, new Date() , "Alice@mail.com", "Done"));
        todoRepository.save(new Todo( "description 1" , 2, new Date()  , "AliceSmith@mail.com", "Done"));
        todoRepository.save(new Todo( "description 2" , 5, new Date()  , "felipemarcelo@mail.com", "pending"));
        todoRepository.save(new Todo( "description 3" , 4, new Date()  , "BobMarley@mail.com", "pending"));
        todoRepository.save(new Todo( "description 4" , 5, new Date() , "Jerson@mail.com", "Done"));
        todoRepository.save(new Todo( "description 5" , 6, new Date()  , "Jimmy@mail.com", "in progress"));
        todoRepository.save(new Todo( "description 6" , 7, new Date()  , "Freddy@mail.com", "pending"));
        todoRepository.save(new Todo( "description 7" , 8, new Date() , "Mercury@mail.com", "pending"));
        todoRepository.save(new Todo( "description 8" , 9, new Date()  , "Michael@mail.com", "Done"));
        todoRepository.save(new Todo( "description 9" , 10, new Date()  , "Lolo@mail.com", "in progress"));
        todoRepository.save(new Todo("description 10I have more than 30 chars :D",10,new Date() ,"felipemarcelo@mail.com","New"));
        todoRepository.save(new Todo("description 11",9,new Date() ,"Nexus@mail.com","New"));
        todoRepository.save(new Todo("description 12",8,new Date() ,"Vim@mail.com","New"));
        todoRepository.save(new Todo("description 13",7,new Date() ,"Jimmy@mail.com","New"));
        todoRepository.save(new Todo("description 14",6,new Date() ,"Scarlett@mail.com","New"));
        todoRepository.save(new Todo("description 15 I have more than 30 chars :D",5,new Date() ,"Franco@mail.com","New"));
        todoRepository.save(new Todo("description 16",10,new Date() ,"felipemarcelo@mail.com","New"));
        todoRepository.save(new Todo("description 17",9,new Date() ,"sara@mail.com","New"));
        todoRepository.save(new Todo("description 18",8,new Date() ,"lola@mail.com","New"));
        todoRepository.save(new Todo("description 19",7,new Date() ,"frida@mail.com","New"));
        todoRepository.save(new Todo("description 20",6,new Date() ,"cosby@mail.com","New"));
        todoRepository.save(new Todo("description 21",5,new Date() ,"felipemarcelo@mail.com","New"));
        todoRepository.save(new Todo("description 22",8,new Date() ,"jenkins@mail.com","New"));
        todoRepository.save(new Todo("description 23",7,new Date() ,"circle@mail.com","New"));
        todoRepository.save(new Todo("description 24",6,new Date() ,"java@mail.com","New"));
        todoRepository.save(new Todo("description 25 I have more than 30 chars :D",8,new Date() ,"felipemarcelo@mail.com","New"));


        System.out.println("Paginated search of Todos by criteria:");
        System.out.println("-------------------------------");

        todoRepository.findByResponsible("felipemarcelo@mail.com", PageRequest.of(0, 2)).stream()
                .forEach(System.out::println);

        userRepository.deleteAll();

        userRepository.save(new User("1234","Anfemaru","felipemarcelo156@gmail.com"));
        userRepository.save(new User("5678","Marcelo","marcelo@gmail.com"));
        userRepository.save(new User("5678","Shaine","Shaine@gmail.com"));
        userRepository.save(new User("5678","Vin","Vin@gmail.com"));
        userRepository.save(new User("5678","Deymar","Deymar@gmail.com"));
        userRepository.save(new User("5678","Juan","Juan@gmail.com"));
        userRepository.save(new User("5678","Carlos","Carlos@gmail.com"));
        userRepository.save(new User("5678","Maria","Maria@gmail.com"));
        userRepository.save(new User("5678","Scarlett","Scarlett@gmail.com"));
        userRepository.save(new User("5678","Mevi","Mevi@gmail.com"));

        System.out.println("----------------- Queries! -----------------");
        Query query = new Query();
        query.addCriteria(Criteria.where("dueDate").lte(new Date()));

        List<Todo> todos = mongoOperation.find(query,Todo.class);
        for (Todo t: todos){
            System.out.println(t);
        }
        System.out.println("-----------------2---------------------");
        Query query2 = new Query();
        query2.addCriteria(Criteria.where("responsible").is("felipemarcelo@mail.com").andOperator(Criteria.where("priority").gte(5)));
        List<Todo> todos2 = mongoOperation.find(query2,Todo.class);
        for (Todo t: todos2){
            System.out.println(t);
        }

        System.out.println("-----------------4---------------------");
        Query query4 = new Query();
        query4.addCriteria(Criteria.where("description").regex("[a-z,A-Z,0-9,' ']{30,}"));
        List<Todo> todos4 = mongoOperation.find(query4,Todo.class);
        for (Todo t: todos4){
            System.out.println(t);
        }


        System.out.println("-------------Queries from interface!-----------------");
        System.out.println("-----------------1-----------------------");
        List<Todo> todosi1 = todoRepository.findAllByDueDateBefore(new Date());
        for (Todo t: todosi1){
            System.out.println(t);
        }
        System.out.println("---------------------2-----------------------");
        List<Todo> todosi2 = todoRepository.findAllByResponsibleEqualsAndPriorityGreaterThanEqual("felipemarcelo@mail.com",5);
        for (Todo t: todosi2){
            System.out.println(t);
        }

        System.out.println("Query #3 Cant be implemented from interface");

        System.out.println("--------------------4--------------------");
        List<Todo> todosi4 = todoRepository.findAllByDescriptionMatchesRegex("[a-z,A-Z,0-9,' ']{30,}");
        for (Todo t: todosi4){
            System.out.println(t);
        }
    }



}