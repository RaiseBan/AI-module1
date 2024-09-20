import data.UserChoice;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Model;
import services.OntologyConnection;
import services.OntologyQueryService;
import validators.InputValidator;

import java.util.Scanner;

public class Dota2CounterPickApp{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод героев противников
        System.out.println("Введите героев противников через запятую (от 1 до 5):");
        String enemyHeroesInput = scanner.nextLine();
        String[] enemyHeroes = enemyHeroesInput.split(",");

        if (!InputValidator.validateEnemyHeroes(enemyHeroes)) {
            System.out.println("Неверный ввод. Введите от 1 до 5 героев.");
            return;
        }

        // Ввод роли (опционально)
        System.out.println("Выберите роль (Tank, Support, Initiator, Carry) или нажмите Enter, чтобы пропустить:");
        String role = scanner.nextLine().trim();
        if (!InputValidator.validateRole(role)) {
            System.out.println("Неверная роль.");
            return;
        }

        // Ввод класса (опционально)
        System.out.println("Выберите класс (Intelligence, Strength, Agility) или нажмите Enter, чтобы пропустить:");
        String heroClass = scanner.nextLine().trim();
        if (!InputValidator.validateClass(heroClass)) {
            System.out.println("Неверный класс.");
            return;
        }

        // Сформировать выбор пользователя
        UserChoice userChoice = new UserChoice();
        userChoice.setEnemyHeroes(enemyHeroes);
        userChoice.setRole(role.isEmpty() ? null : role);
        userChoice.setHeroClass(heroClass.isEmpty() ? null : heroClass);

        // Подключение к онтологии
        Model model = OntologyConnection.loadOntology("d:\\Users\\user\\Desktop\\lab1-ontologies-owx-REVISION-HEAD\\new_lab2.rdf");
        OntologyQueryService queryService = new OntologyQueryService(model);

        // Выполнение запроса
        ResultSet results = queryService.queryCounterPicks(userChoice);

        // Вывод результатов
        if (results != null && results.hasNext()) {
            System.out.println("Рекомендации:");
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                String hero = solution.get("hero").toString();
                String enemyHero = solution.get("enemyHero").toString();
                System.out.println(hero + " counterPickOf " + enemyHero);
            }
        } else {
            System.out.println("Нет рекомендаций.");
        }
    }
}
