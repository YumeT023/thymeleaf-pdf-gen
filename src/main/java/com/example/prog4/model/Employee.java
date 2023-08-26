package com.example.prog4.model;

import com.example.prog4.repository.entity.Position;
import com.example.prog4.repository.entity.enums.Csp;
import com.example.prog4.repository.entity.enums.Sex;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class Employee implements Serializable {
    public static final String IMG_PLACEHOLDER = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHkAAAB5CAMAAAAqJH57AAAAMFBMVEXFxcX////CwsLp6en19fXKysrh4eHOzs7x8fHa2trU1NTt7e37+/vm5ubR0dG/v7+Merg7AAACnklEQVRoge2aWXaDMAxFjTAzgf3vtlBCCA2ggaf0x3cD91iWLVkQQiKRSCQSiUQiIYLGBaLvWoum7OoYY92VTfUtOdHw6LN3+rwN/nIKTcw+6cvK1z15+wPvL6Xnumk4Wu9r3Y2bmsoL70zupB5rRjwt28d8FekXHokmEnuo+VA/A15hvZQLxVkWseJGLAZneKEQZ1mLUwvO0zs9ztyqxNNFilKPwgO1ARKHSivOQDe44kStgHaa1OLpJoOItfk180As2hBs0EVG6syeKRBqizgbAGL9mZoBXCamBIOkmKpMbQAKFtv2HdMls8n8b/scBpMZUaJ1ndAKpCM6fcNdgShWpGvCFiDPHFNyA1I72DYa0w5ZyiTCazrRmGBbGjHUq44eSnHEPTKUZkRDsqDc6W6EmXXpjR2WFIorFPiIDapuDPaQXNXSrcbPxITXN+oO2aklq/aZAgr2ugSep52aGYr1jpPm8XC4/fSWbtp50W13aq4Ht68KFMrrqyz6DNcnL3+P9Q5uOv+MsHeDB/tUyUtGRGb4qGsNHqhTTYW2BYwFpvW0DA0QldL4lryfaMbn8/0yrW47N+7NaG6I76lHa6gX7DXTvMcvtXHVZJtTvDPY1LYxxR7T4NU0K/hLbRHf3eQFw1bbRryfqB+0tnn6AdoXLSjWM8p4I/J6RZXfo+WryRm55ipDpdeCIsnovKu2oHjlIXd5RrzTpm9jV8hfmGCxeCRo7LyukHZlqOtrQ/iZ8n5Z/kQ0m4Pn14wsx/DBFobbI9iicN9qdM8RtMCwwrxHUKaxxWKDLxu2z808LRtsXDOyh21NlD8nyam5/oBM3wIFsL/VoEvzBlekvRKMTTGHCrnCVEq31GaT26VQLTDlyu1QscdK/+OdmPjH/ANk+B9NdllviQAAAABJRU5ErkJggg==";
    private String id;
    private String firstName;
    private String lastName;

    private BigDecimal monthlySalary;
    private int age;
    private MultipartFile image;
    private String stringImage;

    private Csp csp;
    private Sex sex;
    private String cin;
    private String cnaps;
    private String address;
    private Integer childrenNumber;
    private String personalEmail;
    private String professionalEmail;
    private String registrationNumber;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate entranceDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate departureDate;

    private List<Position> positions;
    private List<Phone> phones;
}
