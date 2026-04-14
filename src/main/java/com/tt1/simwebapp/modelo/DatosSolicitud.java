package com.tt1.simwebapp.modelo;

import java.util.Map;

public class DatosSolicitud
{
    private final Map<Integer, Integer> nums;

    public DatosSolicitud(Map<Integer, Integer> nums)
    {
        this.nums = nums;
    }

    public Map<Integer, Integer> getNums()
    {
        return nums;
    }
}
