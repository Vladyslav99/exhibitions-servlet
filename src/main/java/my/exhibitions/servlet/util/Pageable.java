package my.exhibitions.servlet.util;

import java.util.List;

public class Pageable<T>  {
    private final List<T> items;
    private final int pageAmount;
    private final int currentPage;

    public Pageable(List<T> items, Integer pageAmount, int currentPage) {
        this.items = items;
        this.pageAmount = pageAmount;
        this.currentPage = currentPage;
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getPageAmount() {
        return pageAmount;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
