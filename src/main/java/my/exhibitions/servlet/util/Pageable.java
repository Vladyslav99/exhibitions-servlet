package my.exhibitions.servlet.util;

import java.util.List;

public class Pageable<T> {
    private List<T> items;
    private int pageAmount;
    private int currentPage;

    public Pageable(List<T> items, Integer pageAmount, int currentPage) {
        this.items = items;
        this.pageAmount = pageAmount;
        this.currentPage = currentPage;
    }

    public Pageable() {

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
