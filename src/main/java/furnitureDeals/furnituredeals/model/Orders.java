package furnitureDeals.furnituredeals.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String furnitureName;

    @NotNull
    private String username;

    @ManyToOne
    private User user;

    @ManyToOne
    private Furniture furniture;

    public Orders(){

    }

    public int getId() {
        return id;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}