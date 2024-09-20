package data;

import lombok.Data;

@Data
public class UserChoice {
    private String[] enemyHeroes;  // Список героев противников
    private String role;           // Роль, которую выбрал пользователь (опционально)
    private String heroClass;      // Класс, который выбрал пользователь (опционально)
}
