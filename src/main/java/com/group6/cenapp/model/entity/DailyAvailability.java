package com.group6.cenapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "daily_availability")
public class DailyAvailability {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id_availability;
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek day_of_week;
    private boolean open;
    @Column(name = "open_hour")
    private String open_hour;
    @Column(name = "close_hour")
    private String close_hour;

}
