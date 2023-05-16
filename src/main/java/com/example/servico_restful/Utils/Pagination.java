package com.example.servico_restful.Utils;

import java.util.*;

public class Pagination {
    private List data;
    private int page;
    private int itemsPerPage;
    private int totalPages;
    private int totalItems;

    public Pagination(List data) {
        this.data = data;
        this.totalItems = data.size();
    }

    public List getItemsByPage(int page, int itemsPerPage) {
        final int initialIndex = 0;
        this.page = page;
        this.itemsPerPage = itemsPerPage;
        this.totalPages = data.size() / itemsPerPage;

        if(page == 1) {
            return  getSublistByIndex(initialIndex, itemsPerPage);
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
        return getSublistByIndex(startIndex, lastIndex);
    }

    public List getFirstPage() {
        final int initialIndex = 0;
        final int defaultItemsPerPage = 10;

        return getSublistByIndex(initialIndex, defaultItemsPerPage);
    }

    private List getSublistByIndex(int initialIndex, int lastIndex) {
        int startIndex = initialIndex > data.size() ? 0 : initialIndex;

        if(data.size() >= lastIndex && startIndex < data.size()) {
            return data.subList(startIndex, lastIndex);
        } else {
            return data.subList(startIndex, data.size());
        }
    }
}
