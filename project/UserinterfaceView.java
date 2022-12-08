package lesson7.project;

import lesson1.Wall;

import java.io.IOException;
import java.util.Scanner;

public class UserinterfaceView {

    private Controller controller = new Controller();

    public void raunInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите название города");
            String city = scanner.nextLine();

            System.out.println("Введите 1 для получения прогноза на 1 день;" + "Введите 5 для получения прогноза на 5 дней" + "Введите 0 для выхода. ");

            String command = scanner.nextLine();

            if ("0".equals(command)) break;
            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
