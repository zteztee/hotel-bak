package com.zte.springboot_08_ssmp.domain;

import lombok.Data;

@Data
public class All {
    private Integer id;
    private One one;

    public One getOne() {
        return one;
    }

    public void setOne(One one) {
        this.one = one;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    private Team team;
    private String username;
}
