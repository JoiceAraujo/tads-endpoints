package com.example.servico_restful;

import java.util.*;

public class Pagination {
    private List data;
    private int page;
    private int itemsPerPage;
    private int totalPages;
    private int totalItems;

    Pagination(List data) {
        this.data = data;
        this.totalItems = data.size();
    }

    public List getItemsByPage(int page, int itemsPerPage) {
        this.page = page;
        this.itemsPerPage = itemsPerPage;
        this.totalPages = data.size() / itemsPerPage;

        if(page == 1) {
            return data.subList(0, itemsPerPage);
        }

        if(page > totalPages && itemsPerPage < totalItems) {
            int indexFromLastPage = (totalItems - itemsPerPage);
            return data.subList(indexFromLastPage, totalItems);
        }

        if(itemsPerPage > totalItems) {
            return data;
        }

        int startIndex = (this.page - 1) * itemsPerPage;
        int lastIndex = startIndex + itemsPerPage;
        return data.subList(startIndex, lastIndex);
    }

    public List getFirstPage() {
        final int defaultItemsPerPage = 5;

        return data.subList(0, defaultItemsPerPage);
    }
}
