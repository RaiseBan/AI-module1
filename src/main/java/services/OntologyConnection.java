package services;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class OntologyConnection {

    // Подключение к онтологии
    public static Model loadOntology(String ontologyFilePath) {
        Model model = ModelFactory.createOntologyModel();
        FileManager.get().readModel(model, ontologyFilePath);
        return model;
    }
}
