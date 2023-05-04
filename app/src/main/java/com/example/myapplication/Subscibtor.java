package com.example.myapplication;

public class Subscibtor {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private String StartDate;
    private String EndDate;
    private String PayDate;
    private int payment;


    public Subscibtor(int id ,String name, String startDate, String endDate, String payDate, int payment) {
        this.id=id;
        this.name = name;
        StartDate = startDate;
        EndDate = endDate;
        PayDate = payDate;
        this.payment = payment;
    }

    public Subscibtor(String name, String payDate, int payment) {
        this.name = name;
        PayDate = payDate;
        this.payment = payment;
    }
   // overloading
    public Subscibtor(int id, String name, String payDate, int payment){
        this.id=id;
        this.name=name;
        this.PayDate=payDate;
        this.payment=payment;
    }

    @Override
    public String toString() {
        return "Subscibtor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", EndDate='" + EndDate + '\'' +
                ", PayDate='" + PayDate + '\'' +
                ", payment=" + payment +
                '}';
    }

    public Subscibtor() {
    }

    public Subscibtor(String name, String payDate, String endDate, int payment) {
        this.name = name;
        this.PayDate = payDate;
        this.EndDate = endDate;
        this.payment = payment;
    }

    public Subscibtor(int id, String name, String startDate, String endDate, int payment) {
        this.id=id;
        this.name = name;
        StartDate = startDate;
        EndDate = endDate;
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getPayDate() {
        return PayDate;
    }

    public void setPayDate(String payDate) {
        PayDate = payDate;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
}
