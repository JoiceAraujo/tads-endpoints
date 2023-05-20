package com.example.servico_restful.Utils;

import java.util.*;

public class Pagination<T> {
    private final List<T> data;
    private final int totalItems;

    public Pagination(List<T> data) {
        this.data = data;
        this.totalItems = data.size();
    }

    public List<T> getItemsByPage(int page, int itemsPerPage) {
        final int initialIndex = 0;
        int totalPages = data.size() / itemsPerPage;

        if(page == 1) {
            return getSublistByIndex(initialIndex, itemsPerPage);
        }

        if(page > totalPages && itemsPerPage < totalItems) {
            int indexFromLastPage = (totalItems - itemsPerPage);
            return data.subList(indexFromLastPage, totalItems);
        }

        if(itemsPerPage > totalItems) {
            return data;
        }

        int startIndex = (page - 1) * itemsPerPage;
        int lastIndex = startIndex + itemsPerPage;
        return getSublistByIndex(startIndex, lastIndex);
    }

    private List<T> getSublistByIndex(int initialIndex, int lastIndex) {
        int startIndex = initialIndex > data.size() ? 0 : initialIndex;

        if(data.size() >= lastIndex && startIndex < data.size()) {
            return data.subList(startIndex, lastIndex);
        } else {
            return data.subList(startIndex, data.size());
        }
    }
}
