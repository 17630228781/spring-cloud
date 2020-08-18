package com.jk.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class BookBean implements Serializable {

    private Integer id;

    private String name;

    private Double price;

    private String details;

    private Integer typeId;

    private String timestamp;

    private String typeName;

}
