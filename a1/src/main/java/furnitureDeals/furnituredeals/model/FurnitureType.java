package furnitureDeals.furnituredeals.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FurnitureType {

    @Id
    @GeneratedValue
    int id;

    @NotNull
    String name;

    @OneToMany
    @JoinColumn(name = "furniture_type_id")
    private List<Furniture> furnitures = new ArrayList<>();

    public FurnitureType(String name){
        this.name = name;
    }

    public FurnitureType(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
