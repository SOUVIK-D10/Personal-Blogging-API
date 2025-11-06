package com.SkyLightLabs.api.DTO;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class DateDTO {
    private Date startDate;
    private Date endDate;
    public DateDTO(Date startDate,Date endDate){
        this.startDate=startDate;
        this.endDate=endDate;
    }
    public DateDTO(){}
    public Date getEndDate() {
        return endDate;
    }
    public Date getStartDate() {
        return startDate;
    }
}
