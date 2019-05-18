package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.model.Furniture;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

public class ProcessAddFurniture {

    private int userId;
    private Model model;
    private Errors errors;
    private Furniture newFurniture;
    private FurnitureDAO furnitureDao;

    public ProcessAddFurniture(int userId, Model model, Errors errors, Furniture newFurniture, FurnitureDAO furnitureDao) {
        this.userId = userId;
        this.model = model;
        this.errors = errors;
        this.newFurniture = newFurniture;
        this.furnitureDao = furnitureDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public Errors getErrors() {
        return errors;
    }

    public Furniture getNewFurniture() {
        return newFurniture;
    }

    public FurnitureDAO getFurnitureDao() {
        return furnitureDao;
    }
}
