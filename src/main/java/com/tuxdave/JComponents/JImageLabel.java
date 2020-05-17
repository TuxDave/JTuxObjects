package com.tuxdave.JComponents;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class JImageLabel extends JLabel {
    private URL src;
    private ImageIcon image;

    public JImageLabel(URL _src, int _weigth, int _heigth){
        src = _src;
        image = new ImageIcon(src);
        Image temp = image.getImage().getScaledInstance(_weigth, _heigth,0);
        image.setImage(temp);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this,g,0,0);
        this.setMinimumSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        this.setPreferredSize(this.getMinimumSize());
    }

    public void setImage(String _newImageURL, int _weigth, int _heigth) throws MalformedURLException {
        src = new URL(_newImageURL);
        image = new ImageIcon(src);
        Image temp = image.getImage().getScaledInstance(_weigth, _heigth,Image.SCALE_DEFAULT);
        image.setImage(temp);
        update();
    }

    private void update(){
        paintComponent(getGraphics());
    }
}
