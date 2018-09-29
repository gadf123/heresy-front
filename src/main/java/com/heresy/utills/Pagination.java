package com.heresy.utills;

public class Pagination {
    private int pageSize;
    private int totalContentCount;
    private int currentPage;
    private int totalPage;

    public Pagination(int pageSize, int totalContentCount, int currentPage) {
        this.pageSize = pageSize;
        this.totalContentCount = totalContentCount;
        this.currentPage = currentPage;
        this.totalPage = totalContentCount/pageSize + (totalContentCount%pageSize == 0? 0 : 1);
    }

    public int getOffset(){
        return (this.currentPage-1)*this.pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalContentCount() {
        return totalContentCount;
    }

    public void setTotalContentCount(int totalContentCount) {
        this.totalContentCount = totalContentCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
