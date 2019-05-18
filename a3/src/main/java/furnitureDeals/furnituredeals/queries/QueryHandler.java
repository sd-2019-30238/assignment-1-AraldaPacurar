package furnitureDeals.furnituredeals.queries;

public interface QueryHandler<T> {

    void handle(T request);
}
