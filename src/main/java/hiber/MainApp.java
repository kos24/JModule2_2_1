package hiber;

import hiber.config.AppConfig;
import hiber.dao.CarDao;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarDao car = context.getBean(CarDao.class);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
//      Car car1 = new Car("Jeep", 5);
      user1.setUserCar(new Car("BMW", 3));
      userService.add(user1);
//      car.add(new Car("Toyota",1));

//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getUserCar());
         System.out.println();
      }
      List<User> usersByCar = userService.getUserByCar("BMW",3);
      for (User userByCar : usersByCar) {
         System.out.println(userByCar);
      }

      List<Car> cars = car.listCars();
      for (Car car2 : cars) {
         System.out.println("Id = "+car2.getId());
         System.out.println("Model = "+car2.getModel());
         System.out.println();
      }





      context.close();
   }
}
