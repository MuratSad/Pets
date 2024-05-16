//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDate;
import java.util.*;

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

abstract class Pet extends Animal {
    private List<String> commands;

    public Pet(String name, LocalDate birthDate) {
        super(name, birthDate);
        this.commands = new ArrayList<>();
    }

    public List<String> getCommands() {
        return commands;
    }

    public void learnCommand(String command) {
        commands.add(command);
    }
}

abstract class PackAnimal extends Animal {
    public PackAnimal(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}

class Dog extends Pet {
    public Dog(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Dog";
    }
}

class Cat extends Pet {
    public Cat(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Cat";
    }
}

class Hamster extends Pet {
    public Hamster(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Hamster";
    }
}

class Horse extends PackAnimal {
    public Horse(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Horse";
    }
}

class Camel extends PackAnimal {
    public Camel(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Camel";
    }
}

class Donkey extends PackAnimal {
    public Donkey(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Donkey";
    }
}

public class AnimalRegistry {
    private List<Animal> animals;
    private static int animalCounter = 0;

    public AnimalRegistry() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animalCounter++;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public static int getAnimalCounter() {
        return animalCounter;
    }

    public List<String> getCommands(Animal animal) {
        if (animal instanceof Pet) {
            return ((Pet) animal).getCommands();
        }
        return new ArrayList<>();
    }

    public void teachCommand(Animal animal, String command) {
        if (animal instanceof Pet) {
            ((Pet) animal).learnCommand(command);
        }
    }

    public List<Animal> getAnimalsByBirthDate() {
        animals.sort(Comparator.comparing(Animal::getBirthDate));
        return animals;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnimalRegistry registry = new AnimalRegistry();

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Список команд животного");
            System.out.println("3. Обучение новым командам");
            System.out.println("4. Вывести список животных по дате рождения");
            System.out.println("5. Счетчик животных");
            System.out.println("6. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Введите тип животного (Dog, Cat, Hamster, Horse, Camel, Donkey):");
                    String type = scanner.nextLine();
                    System.out.println("Введите имя животного:");
                    String name = scanner.nextLine();
                    System.out.println("Введите дату рождения животного (гггг-мм-дд):");
                    LocalDate birthDate = LocalDate.parse(scanner.nextLine());

                    Animal animal;
                    switch (type.toLowerCase()) {
                        case "dog":
                            animal = new Dog(name, birthDate);
                            break;
                        case "cat":
                            animal = new Cat(name, birthDate);
                            break;
                        case "hamster":
                            animal = new Hamster(name, birthDate);
                            break;
                        case "horse":
                            animal = new Horse(name, birthDate);
                            break;
                        case "camel":
                            animal = new Camel(name, birthDate);
                            break;
                        case "donkey":
                            animal = new Donkey(name, birthDate);
                            break;
                        default:
                            System.out.println("Неизвестный тип животного.");
                            continue;
                    }

                    registry.addAnimal(animal);
                    System.out.println(type + " добавлен(а) в реестр.");
                    break;
                case 2:
                    System.out.println("Введите имя животного для вывода списка команд:");
                    String searchName = scanner.nextLine();
                    Animal searchAnimal = registry.getAnimals().stream()
                            .filter(a -> a.getName().equalsIgnoreCase(searchName))
                            .findFirst()
                            .orElse(null);
                    if (searchAnimal != null) {
                        System.out.println("Команды " + searchAnimal.getName() + ": " + registry.getCommands(searchAnimal));
                    } else {
                        System.out.println("Животное не найдено.");
                    }
                    break;
                case 3:
                    System.out.println("Введите имя животного для обучения новой команде:");
                    String teachName = scanner.nextLine();
                    Animal teachAnimal = registry.getAnimals().stream()
                            .filter(a -> a.getName().equalsIgnoreCase(teachName))
                            .findFirst()
                            .orElse(null);
                    if (teachAnimal != null && teachAnimal instanceof Pet) {
                        System.out.println("Введите новую команду:");
                        String command = scanner.nextLine();
                        registry.teachCommand(teachAnimal, command);
                        System.out.println("Команда добавлена.");
                    } else {
                        System.out.println("Животное не найдено или не является домашним питомцем.");
                    }
                    break;
                case 4:
                    System.out.println("Список животных по дате рождения:");
                    registry.getAnimalsByBirthDate().forEach(a ->
                            System.out.println(a.getType() + ": " + a.getName() + " - " + a.getBirthDate()));
                    break;
                case 5:
                    System.out.println("Общее количество животных: " + AnimalRegistry.getAnimalCounter());
                    break;
                case 6:
                    System.out.println("Выход.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}