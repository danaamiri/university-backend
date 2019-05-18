package com.db.education.common.paging;

public class Paginator {
    private int startIndex;
    private int count;
    private int totalSize;

    public Paginator(int startIndex, int count, int totalSize) {
        setPaging(startIndex, count, totalSize);
        this.totalSize = totalSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPaging(int startIndex, int count, int totalSize) {
        if (startIndex > totalSize - 1) {
            this.startIndex = -1;
            this.count = -1;
            return;
        }
        if(startIndex+count-1 > totalSize){
            this.startIndex = startIndex;
            this.count = totalSize;
            return;
        }
        this.startIndex = startIndex;
        this.count = count;
    }
}
