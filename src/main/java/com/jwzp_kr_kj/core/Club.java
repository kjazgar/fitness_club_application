package com.jwzp_kr_kj.core;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name="clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public final int id;

    public final String name;
    public final String address;
    @ElementCollection
    public final Map<DayOfTheWeek, OpeningHours> whenOpen;

    public Club() {
        id = 0;
        name = "";
        address = "";
        whenOpen = new HashMap<>();
    }

    public Club(int id, String name, String address, Map<DayOfTheWeek, OpeningHours> whenOpen) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.whenOpen = whenOpen;
    }
}
