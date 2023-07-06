package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class UnitText{
    private health healthh;
    private user userr;

    @Before
    public void setUp() {
        healthh = new health();
        userr= new user();
    }

    @Test
    public void testGetSetDate() {
        String date = "2023-07-06";
        healthh.setDate(date);
        assertEquals(date, healthh.getDate());
    }

    @Test
    public void testGetSetTime() {
        String time = "10:30 AM";
        healthh.setTime(time);
        assertEquals(time, healthh.getTime());
    }

    @Test
    public void testGetSetSys() {
        String sys = "120";
        healthh.setSys(sys);
        assertEquals(sys, healthh.getSys());
    }

    @Test
    public void testGetSetDis() {
        String dis = "80";
        healthh.setDis(dis);
        assertEquals(dis, healthh.getDis());
    }

    @Test
    public void testGetSetBp() {
        String bp = "120/80";
        healthh.setBp(bp);
        assertEquals(bp, healthh.getBp());
    }


    @Test
    public void testGetSetName() {
        String name = "Deya";
        userr.setName(name);
        assertEquals(name, userr.getName());
    }

    @Test
    public void testGetSetEmail() {
        String email = "lamisa.deya2001@gmail.com";
        userr.setEmail(email);
        assertEquals(email, userr.getEmail());
    }



}
