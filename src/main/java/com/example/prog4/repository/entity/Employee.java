package com.example.prog4.repository.entity;

import com.example.prog4.model.enums.AgeCalculationMode;
import com.example.prog4.repository.entity.enums.Csp;
import com.example.prog4.repository.entity.enums.Sex;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.time.Period;
import java.time.temporal.TemporalUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.annotations.ColumnTransformer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "\"employee\"")
@Slf4j
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String cin;

    @Transient
    private int age;

    private BigDecimal monthlySalary;
    private String cnaps;
    private String image;
    private String address;
    private String lastName;
    private String firstName;
    private String personalEmail;
    private String professionalEmail;
    private String registrationNumber;

    private LocalDate birthDate;
    private LocalDate entranceDate;
    private LocalDate departureDate;

    private Integer childrenNumber;

    @Enumerated(EnumType.STRING)
    @ColumnTransformer(read = "CAST(sex AS varchar)", write = "CAST(? AS sex)")
    private Sex sex;
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(read = "CAST(csp AS varchar)", write = "CAST(? AS csp)")
    private Csp csp;

    @ManyToMany
    @JoinTable(
            name = "have_position",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private List<Position> positions;
    @OneToMany
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private List<Phone> phones;

    public int calculateAge(AgeCalculationMode mode, Integer delay) {
        var now = LocalDate.now();
        return switch (mode) {
            case BIRTHDAY -> Period.between(birthDate, now).getYears();
            case YEAR_ONLY -> now.getYear() - birthDate.getYear();
            default -> Period.between(birthDate.minusDays(delay), now).getYears();
        };
    }
}
