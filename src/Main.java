//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        abstract class Animal {
            private String name;
            private LocalDate birthDate;

            public Animal(String name, LocalDate birthDate) {
                this.name = name;
                this.birthDate = birthDate;
            }

            public String getName() {
                return name;
            }

            public LocalDate getBirthDate() {
                return birthDate;
            }

            public abstract String getType();
        }
    }

}