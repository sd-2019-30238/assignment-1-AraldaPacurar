package furnitureDeals.furnituredeals.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Furniture {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, message = "Furniture name must not be empty!")
    private String name;

    @NotNull
    @Size(min = 3, message = "Desctiption must not be empty!")
    private String description;

    @OneToMany
    @JoinColumn(name = "furniture_id")
    private List<Orders> orders = new ArrayList<>();

    public Furniture(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Furniture() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
