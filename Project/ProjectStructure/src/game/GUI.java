package game;

import game.entities.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/*Extremt enkelt game.Gui för att kunna komma igång.
Snygga gärna till/gör ett eget. Men tänk på att gör GUI:s INTE är ett kursmoment - så fastna inte här!
 */

public class GUI extends JFrame {

    private final Game game;

    private JPanel panel;
    private JTextArea showRoom;
    private JTextArea showPersons;
    private JTextField input;
    private JTextArea inventory;
    private JButton button;

    public GUI(Game game) {
        this.game = game;
        this.setTitle("game.Game");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpElements();
        setUpPanel();
        this.add(panel);
        this.setVisible(true);
        this.setResizable(false);
    }

    //Här kan man updatera respektive fält:
    public void setShowRoom(String roomDescription) {
        this.showRoom.setText(roomDescription);
    }

    public void setShowPersons(Person person) {
        this.showPersons.setText(person.toString());
    }

    public void setShowInventory(Inventory i) {
        this.inventory.setText(i.toString());
    }


    //Add person to room
    public void setPerson(Person p) {
        this.showPersons.setText(p.toString());
    }

    private void setUpPanel() {
        this.panel.add(showPersons);
        this.panel.add(showRoom);
        this.panel.add(input);
        this.panel.add(inventory);
        this.panel.add(button);
    }

    private void setUpElements() {
        this.panel = new JPanel(new GridLayout(4, 3));
        this.showRoom = new JTextArea("game.Room: ");
        this.showPersons = new JTextArea("Persons");
        this.inventory = new JTextArea("game.Inventory");
        this.input = new JTextField("Give command");
        this.showPersons.setEditable(false);
        this.showRoom.setEditable(false);
        this.inventory.setEditable(false);

        ActionListener inputListener = e -> this.game.addCommand(input.getText());

        input.addActionListener(inputListener);

        this.button = new JButton("commit");
        this.button.addActionListener(inputListener);

    }


}









