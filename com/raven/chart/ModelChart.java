package com.raven.chart;

import java.awt.Color;

public class ModelChart {

    public ModelChart(String ratarata, double[] d, Color color) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public ModelChart(String label, double[] values) {
        this.label = label;
        this.values = values;
    }

    public ModelChart() {
    }

    private String label;
    private double values[];

    public double getMaxValues() {
        double max = 0;
        for (double v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}
