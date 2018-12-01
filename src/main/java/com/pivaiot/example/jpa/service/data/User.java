package com.pivaiot.example.jpa.service.data;

import com.pivaiot.common.lang.data.BaseIdAndTimeData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseIdAndTimeData {

    private String username;

    private String intro;

    private Date createdTime;

    private Date updatedTime;

}
