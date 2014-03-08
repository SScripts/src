package sminer.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sminer.data.Master;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import sminer.SMiner;
import sminer.task.bank.CloseBank;
import sminer.task.bank.Deposit;
import sminer.task.bank.OpenBank;
import sminer.task.mine.Mine;
import sminer.task.walk.WalkToBank;
import sminer.task.walk.WalkToRock;


public class Gui extends MethodProvider {

    private JFrame frame = new JFrame("SMiner - by SScripts");
    private JPanel panel = new JPanel();
    private JButton button = new JButton();
    private JLabel label = new JLabel();
    private JComboBox<Master> cb = new JComboBox<Master>(Master.values());

    public static Master loc;

    public Gui(MethodContext ctx) {
        super(ctx);
        init();
    }

    public void init() {

        frame.setBounds(200, 300, 270, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);

        panel.setLayout(null);
        panel.add(button);
        panel.add(label);
        panel.add(cb);

        label.setBounds(10, 10, 230, 50);
        label.setText("Select Ore and Location:");

        cb.setBounds(10, 50, 230, 20);

        button.setText("Start");
        button.setBounds(10, 80, 230, 20);

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
                frame.dispose();
            }

        });

    }
}