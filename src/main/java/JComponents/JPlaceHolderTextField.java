package JComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JPlaceHolderTextField extends JTextField{
    public String placeHolder = "";
    private Color originalColor;

    {
        addFocusListener(new JPlaceHolderTextFieldListener()); //adds the focus listener
    }

    public void setPlaceHolder(String s){
        placeHolder = s;
        setPlaceHolderVisible(true);
    }
    public String getPlaceHolder(){
        return placeHolder;
    }
    public void setPlaceHolderVisible(boolean b){
        if(b){
            originalColor = getForeground();
            setText(placeHolder);
            setForeground(Color.GRAY);
        }else{
            setText("");
            setForeground(originalColor);
        }
    }

    @Override
    public String getText() {
        if(super.getText().equals(placeHolder)){
            return "";
        }else{
            return super.getText();
        }
    }

    private static class JPlaceHolderTextFieldListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent focusEvent) {
            JPlaceHolderTextField self = (JPlaceHolderTextField)focusEvent.getSource();
            if(self.getText().equals(self.getPlaceHolder()) || self.getText().equals("")){
                self.setPlaceHolderVisible(false);
            }
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            JPlaceHolderTextField self = (JPlaceHolderTextField)focusEvent.getSource();
            if(self.getText().equals("")){
                self.setPlaceHolderVisible(true);
            }
        }
    }
}
