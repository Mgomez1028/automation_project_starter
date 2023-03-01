package equifax.automation.utils;

import com.github.javafaker.Faker;

public class RandomUtil {
     private static final Faker faker = new Faker();

     public static String getRandomFileName(){
          return String.format("%s_%s_%s", faker.number().numberBetween(999,100000),
          faker.number().numberBetween(999,100000),
          faker.number().numberBetween(999,100000));
     }
}
