package furnitureDeals.furnituredeals.model;

import furnitureDeals.furnituredeals.model.observer.MyObservable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders extends MyObservable {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String furnitureName;

    @NotNull
    private String username;

    @NotNull
    private String status;

    @NotNull
    private String feedback;

    @ManyToOne
    private User user;

    @ManyToOne
    private Furniture furniture;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
    private List<Notification> notifications = new ArrayList<>();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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