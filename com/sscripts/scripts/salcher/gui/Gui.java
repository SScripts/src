package com.sscripts.scripts.salcher.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;



import com.sscripts.scripts.salcher.SAlcher;
import com.sscripts.scripts.salcher.task.Task;
import com.sscripts.scripts.salcher.task.alching.Alch;
import com.sscripts.scripts.salcher.task.alching.Stop;


public class Gui extends ClientAccessor {

    private List<Task> tasks;

    private JFrame frame = new JFrame("SAlcher - by SScripts - V1.0");



    public Gui(ClientContext ctx, List<Task> tasks) {
        super(ctx);
        this.tasks = tasks;
        init();
    }

    public void init() {

        frame.setVisible(true);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());

        final JLabel l = new JLabel("Make sure Alching is on 1 in your Actionbar!");
        centerPanel.add(l);
        
        final JLabel l1 = new JLabel("Enter Item ID you want to Alch");
        centerPanel.add(l1);
        
		NumberFormat format = NumberFormat.getIntegerInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
        final JFormattedTextField text = new JFormattedTextField(formatter); 
        text.add(centerPanel);

        final JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
            	if (text.getValue() != null){
            		SAlcher.alchID = (int) text.getValue();
            	}
            	tasks.add(new Alch(ctx));
            	tasks.add(new Stop(ctx));
          
                frame.dispose();
            }
        });
            
        mainPanel.add(text, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(startButton, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.pack();

    }

}
