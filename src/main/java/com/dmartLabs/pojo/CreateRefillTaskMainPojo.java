package com.dmartLabs.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRefillTaskMainPojo {

    public  String requestId;
    public  String requestedAt;
    public  RefillArticleDetails  articleDetails ;
    public  int  requestedQuantity;
    public  String requesterComments;
     public  String taskType;
}
