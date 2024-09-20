package validators;

public class InputValidator {

    // Валидация списка героев противников (должно быть от 1 до 5)
    public static boolean validateEnemyHeroes(String[] enemyHeroes) {
        return enemyHeroes.length >= 1 && enemyHeroes.length <= 5;
    }

    // Валидация роли (если указана, должна быть допустимой)
    public static boolean validateRole(String role) {
        // Если роль не указана (пустая строка), считаем это корректным
        return role == null || role.isEmpty() || role.matches("(Tank|Support|Initiator|Carry)");
    }

    // Валидация класса героя (если указан, должен быть допустимым)
    public static boolean validateClass(String heroClass) {
        // Если класс не указан (пустая строка), считаем это корректным
        return heroClass == null || heroClass.isEmpty() || heroClass.matches("(Intelligence|Strength|Agility)");
    }
}
