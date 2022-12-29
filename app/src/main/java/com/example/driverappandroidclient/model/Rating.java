package com.example.driverappandroidclient.model;

public class Rating {


    private Long id;
    private int value;
    private Long adviceId;

    public Rating(Long id, int value, Long adviceId) {
        this.id = id;
        this.value = value;
        this.adviceId = adviceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Long getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Long adviceId) {
        this.adviceId = adviceId;
    }

}
