package sminer.gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import sminer.data.Master;
import sminer.SMiner;
import sminer.task.bank.CloseBank;
import sminer.task.bank.Deposit;
import sminer.task.bank.OpenBank;
import sminer.task.drop.Drop;
import sminer.task.framework.Task;
import sminer.task.mine.Mine;
import sminer.task.walk.WalkToBank;
import sminer.task.walk.WalkToRock;


public class Gui extends ClientAccessor {

    private List<Task> tasks;

    private JFrame frame = new JFrame("SMiner - by SScripts");

    public static Master loc;

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
                synchronized (tasks) {
                tasks.add(new Deposit(ctx));
                tasks.add(new OpenBank(ctx));
                tasks.add(new CloseBank(ctx));
                tasks.add(new Mine(ctx));
                tasks.add(new WalkToBank(ctx));
                tasks.add(new WalkToRock(ctx));
                tasks.add(new Drop(ctx));
                    tasks.notifyAll();
                }
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