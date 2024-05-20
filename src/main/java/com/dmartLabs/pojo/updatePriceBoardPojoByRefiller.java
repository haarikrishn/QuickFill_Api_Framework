package com.dmartLabs.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class updatePriceBoardPojoByRefiller {

    public  String refillId;
    public  List<priceBoardSubpojo> priceBoards;
    public  String status;
    public  String refillerComments;
}
