import com.graduation.voting.service.MealService;
import com.graduation.voting.service.UserService;
import com.graduation.voting.web.controller.AdminController;
import com.graduation.voting.web.controller.AdminMealController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static com.graduation.voting.MealTestData.*;
import static com.graduation.voting.RestaurantTestData.RESTAURANT1_ID;

public class SpringMain {

    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext(
                "spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-mvc.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            System.out.println("\n_______________________________");
            UserService userService = appCtx.getBean(UserService.class);
//            userService.create(new User(null, "newUser", "new_email_1_@mail.ru", "password", Role.ROLE_ADMIN));
//            userService.create(new User(null, "new2User", "new_email_2_@mail.ru", "password", Role.ROLE_ADMIN, Role.ROLE_USER));
            System.out.println("\nuserService.getAll(): \n" + userService.getAll());

            AdminController adminController = appCtx.getBean(AdminController.class);
            System.out.println(adminController.getAll());

            MealService mealService = appCtx.getBean(MealService.class);

            System.out.println("\n_______________________________");

            AdminMealController mealController = appCtx.getBean(AdminMealController.class);
            mealController.update(getUpdated(), RESTAURANT1_ID, MEAL1_ID);
            System.out.println(mealController.get(MEAL1_ID, RESTAURANT1_ID));
            mealController.save(getNew(), RESTAURANT1_ID);
//            System.out.println("\n_______________________________");
//            RestaurantService restaurantService = appCtx.getBean(RestaurantService.class);
//            restaurantService.create(new Restaurant(null, "New_Restaurant_1"));
//            restaurantService.create(new Restaurant(null, "New_Restaurant_2"));
//            System.out.println("\nrestaurantService.getAll(): \n" + restaurantService.getAll());
//
//            SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken(
//                            new AuthorizedUser(USER), null, USER.getRoles()));
//
//            VoteRestController voteController = appCtx.getBean(VoteRestController.class);
//            System.out.println(voteController.getAll());
        }
    }
}
