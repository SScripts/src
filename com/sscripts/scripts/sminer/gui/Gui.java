package sminer.gui;


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
    private JPanel panel = new JPanel();
    private JButton button = new JButton();
    private JLabel label = new JLabel();
    private JComboBox<Master> cb = new JComboBox<Master>(Master.values());
    private JRadioButton ch = new JRadioButton();

    public static Master loc;

    public Gui(MethodContext ctx) {
        super(ctx);
        init();
    }

    public void init() {

        frame.setBounds(200, 300, 270, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);

        panel.setLayout(null);
        panel.add(button);
        panel.add(label);
        panel.add(cb);
        panel.add(ch);

        label.setBounds(10, 10, 230, 50);
        label.setText("Select Ore and Location:");

        cb.setBounds(10, 50, 230, 20);

        ch.setBounds(10, 80, 230, 20);
        ch.setText("Dropping?");

        button.setText("Start");
        button.setBounds(10, 120, 230, 20);

        button.addActionListener(new ActionListener() {

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

    }
}