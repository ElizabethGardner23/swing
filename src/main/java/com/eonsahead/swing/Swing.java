package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Swing extends JFrame implements ActionListener {

    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 500;
    private final String FRAME_TITLE = "Swing";
    private final int NUMBER_OF_COLORS = 6;
    private final String BG_COLOR = "Background Color";
    private final String FG_COLOR = "Square Color";

    private final List<Color> bgPalette = new ArrayList<>();
    private final List<Color> fgPalette = new ArrayList<>();
    private final SwingPanel panel;

    public Swing() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        this.panel = new SwingPanel();
        pane.add(panel);

        Random rng = new Random();
//        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
//            int red = 64 + rng.nextInt(128);
//            int green = 64 + rng.nextInt(128);
//            int blue = 64 + rng.nextInt(128);
//            Color color = new Color(red, green, blue);
//            bgPalette.add(color);
//        } // for
//        this.panel.setBackground(bgPalette.get(0));

        Color red = new Color(255,0,0);
        Color orange = new Color(255,165,0);
        Color yellow = new Color(255,255,0);
        Color green = new Color(0,255,0);
        Color blue = new Color(0,0,255);
        Color purple = new Color(160,32,240);
        Color white = new Color(255,255,255);
        Color black = new Color(0,0,0);
        bgPalette.add(red);
        bgPalette.add(orange);
        bgPalette.add(yellow);
        bgPalette.add(green);
        bgPalette.add(blue);
        bgPalette.add(purple);
        //bgPalette.add(black);
        //bgPalette.add(white);
        this.panel.setBackground(bgPalette.get(0));

//        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
//            int r = 32 + rng.nextInt(224);
//            int g = 32 + rng.nextInt(224);
//            int b = 32 + rng.nextInt(224);
//            Color color = new Color(r, g, b);
//            fgPalette.add(color);
//        } // for
//        this.panel.setColor(fgPalette.get(0));

        Color ltred = new Color(240,128,128);
        Color ltorange = new Color(255,160,122);
        Color ltyellow = new Color(255,255,224);
        Color ltgreen = new Color(144,238,144);
        Color ltblue = new Color(173,216,230);
        Color ltpurple = new Color(230,230,250);
        Color ltgray = new Color(200,200,200);
        Color ltbrown = new Color(235,215,200);
        fgPalette.add(ltred);
        fgPalette.add(ltorange);
        fgPalette.add(ltyellow);
        fgPalette.add(ltgreen);
        fgPalette.add(ltblue);
        fgPalette.add(ltpurple);
        //fgPalette.add(ltgray);
        //fgPalette.add(ltbrown);
        this.panel.setColor(fgPalette.get(0));
        
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu bgColorMenu = new JMenu(BG_COLOR);
        menuBar.add(bgColorMenu);

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            String label = BG_COLOR + " " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(this);
            item.setActionCommand(label);
            bgColorMenu.add(item);
        } // for

        JMenu fgColorMenu = new JMenu(FG_COLOR);
        menuBar.add(fgColorMenu);

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            String label = FG_COLOR + " " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(this);
            item.setActionCommand(label);
            fgColorMenu.add(item);
        } // for

        this.setVisible(true);
    } // Swing()

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if (actionCommand.indexOf(BG_COLOR) >= 0) {
            int i = BG_COLOR.length();
            String suffix = actionCommand.substring(i).trim();
            int index = Integer.parseInt(suffix);
            this.panel.setBackground(bgPalette.get(index));
        } // if
        else if (actionCommand.indexOf(FG_COLOR) >= 0) {
            int i = FG_COLOR.length();
            String suffix = actionCommand.substring(i).trim();
            int index = Integer.parseInt(suffix);
            this.panel.setColor(fgPalette.get(index));
        } // if
    } // actionPerformed( ActionEvent )

    public static void main(String[] args) {
        Swing swing = new Swing();
    } // main( String [] )

} // Swing
