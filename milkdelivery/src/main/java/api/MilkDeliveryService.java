package api;

import dao.MilkProductDAO;
import dao.OrdersDAO;
import dao.SubscriptionDAO;
import dao.UserDAO;
import manager.AdminManager;
import manager.CustomerManager;
import manager.request_model.SubscriptionRequest;
import model.SubscriptionType;
import model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MilkDeliveryService {
    private AdminManager adminManager;
    private CustomerManager customerManager;
    private UserDAO userDAO;

    public MilkDeliveryService(AdminManager adminManager, CustomerManager customerManager, UserDAO userDAO) {
        this.adminManager = adminManager;
        this.customerManager = customerManager;
        this.userDAO = userDAO;
    }

    public User getUser(String userId) {
        User user = userDAO.getUser(userId);
        if(user == null) {
            throw new RuntimeException("User not part of the system");
        }

        return user;
    }




    public static void main(String[] args) throws Exception {
        UserDAO userDAO = new UserDAO();
        OrdersDAO ordersDAO = new OrdersDAO();
        AdminManager adminManager = new AdminManager(ordersDAO);
        MilkProductDAO milkProductDAO = new MilkProductDAO();
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO(milkProductDAO);
        CustomerManager customerManager = new CustomerManager(milkProductDAO, ordersDAO, subscriptionDAO);
        MilkDeliveryService milkDeliveryService = new MilkDeliveryService(adminManager, customerManager, userDAO);

        System.out.println("Enter Your user Id : ");
        Scanner scanner = new Scanner(System.in);
        String userId = scanner.nextLine();

        User user = milkDeliveryService.getUser(userId);
        int returnCode = 0;
        while (returnCode == 0) {
            switch (user.getRole()) {
                case "Admin" :
                    milkDeliveryService.printAdminMenu();
                    break;
                case "Customer" :
                    milkDeliveryService.printCustomerMenu();
                    break;
            }
            switch (user.getRole()) {
                case "Admin" :
                    returnCode = milkDeliveryService.handleAdminMenu(scanner);
                    break;
                case "Customer" :
                    returnCode = milkDeliveryService.handleCustomerMenu(userId, scanner);
                    break;
            }

        }
    }

    public int handleAdminMenu(Scanner scanner) throws Exception {
        int option = scanner.nextInt();
        int returnCode = 0;
        switch (option) {
            case 1:
                System.out.println("Give the date for orders in dd/mm/yyyy format");

                String dateString = scanner.nextLine();
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                    System.out.println(adminManager.getOnDemandOrder(date));
                } catch (ParseException e) {
                    throw new Exception("Internal Error ", e);
                }

                break;
            case 2:
                System.out.println("Give the type of subscription");

                String typeString = scanner.nextLine();
                SubscriptionType subscriptionType = SubscriptionType.valueOf(typeString);
                System.out.println(adminManager.getSubscriptions(subscriptionType));
                break;
            case 3:
                returnCode = 1;
                break;
            default:
                System.out.println("Invalid Option");
        }
        return returnCode;
    }

    public int handleCustomerMenu(String userId, Scanner scanner) {
        int option = Integer.valueOf(scanner.nextLine());
        int returnCode = 0;
        switch (option) {
            case 1:
                System.out.println(customerManager.getAllMilkProducts(null));
                break;
            case 2:
                System.out.println(customerManager.getAllSubscriptions());
                break;
            case 3:
                break;
            case 4:
                System.out.println("Input the milk product id : ");
                String productId = scanner.nextLine();
                System.out.println("Input the subscription type : ");
                String typeString = scanner.nextLine();
                SubscriptionType subscriptionType = SubscriptionType.valueOf(typeString);
                customerManager.subscribe(new SubscriptionRequest(userId, productId, subscriptionType));
                break;
            case 5:
                System.out.println(customerManager.getAllMilkProducts("price"));
                break;
            case 6:
                System.out.println(customerManager.getAllOrders(userId));
                break;
            case 7:
                returnCode = 1;
                break;
            default:
                System.out.println("Invalid Option");
        }
        return returnCode;
    }
    private void printAdminMenu() {
        System.out.println("Menu : \n" +
                "1. Get All Subscriptions \n " +
                "2. Get all orders for a day \n" +
                "3. Exit \n" +
                "Please choose an option");
    }

    private void printCustomerMenu() {
        System.out.println("Menu : \n" +
                "1. List All Milk Products \n" +
                "2. List All Subscriptions \n" +
                "3. Order Ondemand \n" +
                "4. Subscribe \n" +
                "5. Sort By Price \n" +
                "6. My orders \n" +
                "7. Exit \n" +

                "Please choose an option");
    }
}
