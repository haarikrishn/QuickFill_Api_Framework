package com.dmartLabs.pojo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OthersTaskMainPojo {


    public  String requestId;
    public  String requestedAt;
    public String requesterComments;
    public  String taskType;
    public OthersArticlesPojo articleDetails;
}
