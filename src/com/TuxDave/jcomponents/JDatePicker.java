package com.TuxDave.jcomponents;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.ConsoleHandler;

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

        //creation of a generic model based on String
        monthCombo.setModel(new DefaultComboBoxModel<String>());

        //setting the starting year and the dinamically limits fot the choose of the year
        Calendar c = Calendar.getInstance();
        yearSpinner.setValue(c.getTime().getYear()+1900);
        yearSpinner.addChangeListener(new JDataPickerListener());

        //Adding months to the JComboBox
        String[] mesi = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(short i = 0; i < 12; i++){
            monthCombo.addItem(new String(Integer.toString(i+1) + " " + mesi[1]));
        }
    }
    private class JDataPickerListener implements ChangeListener{ //creation of the class that implements the listener
        @Override
        public void stateChanged(ChangeEvent changeEvent) { //implementation of ChangeListener
            if(changeEvent.getSource().equals(yearSpinner)){ //if the listener is called by "daySpinner"
                Calendar c = Calendar.getInstance();
                int min = c.getTime().getYear()+1900-125;
                int now = c.getTime().getYear()+1900;
                if((int)yearSpinner.getValue() < min){
                    yearSpinner.setValue(min);
                }
                if((int)yearSpinner.getValue() > now){
                    yearSpinner.setValue(now);
                }
            }
        }
    }
}
