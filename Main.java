package lesson7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("Красная", "Lada");

        String carFromJSON = objectMapper.writeValueAsString(car);
        System.out.println(carFromJSON);

        Car car1 = objectMapper.readValue(carFromJSON, Car.class);
        System.out.println(car1);


        List<Car> carList = new ArrayList<>(Arrays.asList(new Car("White", "Muscovite"),                                                                                          new Car("Green", "Zil" )));

        System.out.println(carList);

        String carsAsJSON = objectMapper.writeValueAsString(carList);
        System.out.println(carsAsJSON);

        List<Car> carsFromJSON = objectMapper.readValue(carsAsJSON, new TypeReference<ArrayList<Car>>(){});
        System.out.println(carsFromJSON);


        String jsonCarAfterUpdate = "{\"color\":\"Красная\",\"type\":\"Lada\",\"year\":\"2022\"}";
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Car carAfterUpdate = objectMapper.readValue(jsonCarAfterUpdate, Car.class);
        System.out.println(carAfterUpdate);

        String jsonCarAfterRefactoring = "{\"color\":\"Красная\",\"model\":\"Lada\"}";
        Car carAfterRefactoring = objectMapper.readValue(jsonCarAfterRefactoring, Car.class);
        System.out.println(carAfterRefactoring);

        Car carWithSead = new Car("Yelloe", "BMW");
        carWithSead.setSeat(new Seat(7));

        String carWithSeadJSON = objectMapper.writeValueAsString(carWithSead);
        System.out.println(carWithSeadJSON);

        Car carWithSeadFromJSON = objectMapper.readValue(carWithSeadJSON, Car.class);
        System.out.println(carWithSeadFromJSON);




    }

}
