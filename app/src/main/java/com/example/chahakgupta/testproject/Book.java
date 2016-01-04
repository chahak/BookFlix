package com.example.chahakgupta.testproject;

import org.xml.sax.DTDHandler;

public class Book {
	public String code;
    public String name;
    public String publisher;
    public String price;
    public String author;
    public String condition;
    public String status;
    public String image;
    public String seller;
    public String buyer;

    public Book(String code,String name, String hometown, String price, String author,String condition,String status,String image,String seller, String buyer) {
    	this.code = code;
       this.name = name;
       this.publisher = hometown;
       this.price = price;
       this.author = author;
       this.condition = condition;
       this.status = status;
       this.image = image;
       this.seller = seller;
       this.buyer = buyer;
    }
}