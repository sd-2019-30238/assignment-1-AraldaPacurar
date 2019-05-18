package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.FurnitureTypeDAO;
import furnitureDeals.furnituredeals.model.forms.FilterForm;
import org.springframework.ui.Model;

public class ProcessReadAllFurniture {

    private int userId;
    private Model model;
    private FilterForm filterForm;
    private FurnitureDAO furnitureDao;
    private FurnitureTypeDAO furnitureTypeDao;

    public ProcessReadAllFurniture(int userId, Model model, FilterForm filterForm, FurnitureDAO furnitureDao, FurnitureTypeDAO furnitureTypeDao) {
        this.userId = userId;
        this.model = model;
        this.filterForm = filterForm;
        this.furnitureDao = furnitureDao;
        this.furnitureTypeDao = furnitureTypeDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public FilterForm getFilterForm() {
        return filterForm;
    }

    public FurnitureDAO getFurnitureDao() {
        return furnitureDao;
    }

    public FurnitureTypeDAO getFurnitureTypeDao() {
        return furnitureTypeDao;
    }
}