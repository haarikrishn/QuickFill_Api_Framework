package com.dmartLabs.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class priceBoardMainPojo {

    public  String requestId;
    public List<priceBoardSubpojo> priceBoards;
    public  String requestedAt;
    public PriceBoardArticleDetailsPojo articleDetails;
    public  String requesterComments;
    public  String taskType;





}
