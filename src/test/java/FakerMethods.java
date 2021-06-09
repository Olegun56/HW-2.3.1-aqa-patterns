import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FakerMethods {

    private final Faker faker = new Faker(new Locale("ru"));

    public String localDate(int earlyDate) {
        String inputDate;
        LocalDate date = LocalDate.now().plusDays(earlyDate);
        inputDate = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(date);
        return inputDate;


    }

    public String name() {
        return faker.name().fullName();
    }

    public String phoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public String city() {
        return faker.address().cityName();
    }
}
