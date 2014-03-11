package sminer.gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import sminer.data.Master;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import sminer.SMiner;
import sminer.task.bank.CloseBank;
import sminer.task.bank.Deposit;
import sminer.task.bank.OpenBank;
import sminer.task.drop.Drop;
import sminer.task.mine.Mine;
import sminer.task.walk.WalkToBank;
import sminer.task.walk.WalkToRock;


public class Gui extends MethodProvider {


    private JFrame frame = new JFrame("SMiner - by SScripts");

    public static Master loc;

    public Gui(MethodContext ctx) {
        super(ctx);
        init();
    }


    public void init() {

        frame.setVisible(true);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());

        final JLabel label = new JLabel("Select Location and Ore");
        label.add(centerPanel);

        final JComboBox cb = new JComboBox(Master.values());
        centerPanel.add(cb);

        final JCheckBox ch = new JCheckBox("Dropping?");
        centerPanel.add(ch);



        final JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                loc = (Master) cb.getSelectedItem();
                SMiner.tasks.add(new Deposit(ctx));
                SMiner.tasks.add(new OpenBank(ctx));
                SMiner.tasks.add(new CloseBank(ctx));
                SMiner.tasks.add(new Mine(ctx));
                SMiner.tasks.add(new WalkToBank(ctx));
                SMiner.tasks.add(new WalkToRock(ctx));
                SMiner.tasks.add(new Drop(ctx));
                if (ch.isSelected()) {
                    SMiner.drop = true;
                }
                frame.dispose();
            }

        });

        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(startButton, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.pack();

    }

}