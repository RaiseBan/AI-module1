package services;

import data.UserChoice;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;

public class OntologyQueryService {

    private final Model ontologyModel;

    public OntologyQueryService(Model ontologyModel) {
        this.ontologyModel = ontologyModel;
    }

    // Построение и выполнение SPARQL-запроса на основе выбора пользователя
    public ResultSet queryCounterPicks(UserChoice userChoice) {
        String query = buildQuery(userChoice);

        try {
            Query sparqlQuery = QueryFactory.create(query);
            QueryExecution qexec = QueryExecutionFactory.create(sparqlQuery, ontologyModel);
            return qexec.execSelect();
        } catch (QueryParseException e) {
            System.out.println("Ошибка в SPARQL-запросе: " + e.getMessage());
            return null;
        }
    }

    // Построение SPARQL-запроса на основе данных пользователя
    private String buildQuery(UserChoice userChoice) {
        StringBuilder query = new StringBuilder();
        query.append("PREFIX ontology: <http://www.semanticweb.org/user/ontologies/2024/8/untitled-ontology-11#> \n");
        query.append("SELECT ?hero ?enemyHero WHERE { \n");

        // Цикл по всем врагам
        for (String enemy : userChoice.getEnemyHeroes()) {
            query.append("{ ?hero ontology:isCounterPickOf ontology:").append(enemy.trim()).append(" . ");
            query.append("BIND(ontology:").append(enemy.trim()).append(" AS ?enemyHero) \n");

            // Добавляем фильтры по роли, если она указана
            if (userChoice.getRole() != null && !userChoice.getRole().isEmpty()) {
                query.append("?hero ontology:hasRole ontology:").append(userChoice.getRole()).append(" . \n");
            }

            // Добавляем фильтры по классу, если он указан
            if (userChoice.getHeroClass() != null && !userChoice.getHeroClass().isEmpty()) {
                query.append("?hero ontology:hasClass ontology:").append(userChoice.getHeroClass()).append(" . \n");
            }
            query.append("} UNION ");
        }

        // Убираем последний "UNION"
        query.setLength(query.length() - 6);
        query.append("}");
        System.out.println(query.toString());
        return query.toString();
    }
}
