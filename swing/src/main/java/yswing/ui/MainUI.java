package yswing.ui;

import yswing.srv.CommandRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainUI extends JFrame implements ActionListener, KeyListener {

    private CommandRunner commandRunner = new CommandRunner();
    private String result_nothing = "<span style='color: blue;'>None</span>";
    private JTextField input;
    private JTextPane output;


    public MainUI(){
        this.setSize(500,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        initContentUI();
    }

    private void initContentUI(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(commandPanel(), BorderLayout.NORTH);
        panel.add(outputPanel(), BorderLayout.CENTER);

        this.setContentPane(panel);
    }

    private JScrollPane outputPanel() {
        JScrollPane scrollPane = new JScrollPane();
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(this.result_nothing);
        scrollPane.setViewportView(textPane);
        this.output = textPane;
        return scrollPane;
    }

    private JPanel commandPanel() {
        JPanel jInPanel = new JPanel();
        JButton button = new JButton("Execute");
        button.addActionListener(this);

        GridBagLayout bagLayout = new GridBagLayout();
        jInPanel.setLayout(bagLayout);
        GridBagConstraints gridBagConstraints = GridBagConstraintsBuilder.builder()
                .fill(GridBagConstraints.HORIZONTAL).weightX(1).build();

        JTextField textField = new JTextField("A");
        // 注册键盘事件
        textField.addKeyListener(this);

        bagLayout.setConstraints(textField, gridBagConstraints);
        bagLayout.setConstraints(button, new GridBagConstraints());

        jInPanel.add(textField);
        this.input = textField;
        jInPanel.add(button);

        return jInPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        runCmd();
    }

    private void runCmd() {
        String inString = this.input.getText();
        String result = commandRunner.run(inString);
        this.output.setText(result);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER == e.getKeyCode()){
            this.runCmd();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
