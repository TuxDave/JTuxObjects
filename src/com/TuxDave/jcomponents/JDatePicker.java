package com.TuxDave.jcomponents;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class JDatePicker extends JPanel {
    private JPanel bodyPanel;
    private JPanel monthPanel;
    private JPanel dayPanel;
    private JPanel yearPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JSpinner daySpinner;
    private JComboBox<String> monthCombo;
    private JSpinner yearSpinner;
    {
        //inizializing all the needed
        //adding listeners
        daySpinner.addChangeListener(new JDataPickerListener());
        yearSpinner.addChangeListener(new JDataPickerListener());
        monthCombo.addActionListener(new JDataPickerListener());

        //creation of a generic model based on String
        monthCombo.setModel(new DefaultComboBoxModel<String>());
        //and add the months
        String[] mesi = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(short i = 0; i < 12; i++){
            monthCombo.addItem(new String(Integer.toString(i+1) + " " + mesi[i]));
        }

        //set the starting year
        Calendar c = Calendar.getInstance();
        yearSpinner.setValue(c.getTime().getYear()+1900);
    }

    private class JDataPickerListener implements ChangeListener, ActionListener{ //creation of the class that implements the listeners
        @Override
        public void stateChanged(ChangeEvent changeEvent) { //implementation of ChangeListener
            if(changeEvent.getSource().equals(yearSpinner)){ //if the listener is called by "yearSpinner"
                Calendar c = Calendar.getInstance();
                int min = c.getTime().getYear()+1900-125;
                int now = c.getTime().getYear()+1900;
                if((int)yearSpinner.getValue() < min){
                    yearSpinner.setValue(min);
                }
                if((int)yearSpinner.getValue() > now){
                    yearSpinner.setValue(now);
                }
                JDataPickerListener controller = new JDataPickerListener(); //check if the day is ok
                controller.stateChanged(new ChangeEvent(daySpinner));
            }
            if(changeEvent.getSource().equals(daySpinner)){ // condiction about daySpinner
                int day = (int)(daySpinner.getValue());
                if(day < 0){
                    daySpinner.setValue(0);
                }if(day > 28){
                    int[] giorni = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
                    int mese = monthCombo.getSelectedIndex();
                    int anno = (int)(yearSpinner.getValue());
                    boolean bis = anno % 4 == 0;
                    if(day > giorni[mese] + (mese == 1 && bis ? 1 : 0)){
                        daySpinner.setValue(day - 1);
                    }
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource().equals(monthCombo)){
                JDataPickerListener controller = new JDataPickerListener(); //check if the day is ok
                controller.stateChanged(new ChangeEvent(daySpinner));
            }
        }
    }
}
