// package hms;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
public class App extends JFrame implements ActionListener{
    JButton b1;
    App()
    {
        // setSize(400,400);
        setBounds(120,200,1366,565);
        // setLocation(300,300);
        // you can use setBounds instead of both abv!
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        
        JLabel l1 = new JLabel(i1);
        l1.setBounds(0,0,1366,565);
        add(l1);

    // Adding TEXT ON IMAGE

        JLabel j2 = new JLabel("HOTEL MANAGEMENT SYSTEM");
        j2.setForeground(Color.WHITE);  //awt.Color
        j2.setFont(new Font("serif" , Font.PLAIN , 40));
        j2.setBounds(40,470 , 800,50);
        l1.add(j2);
        
        
       
        
        //button to the next page

        b1 = new JButton("Next");
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Tahoma" , Font.PLAIN , 32));
        b1.setBounds(880 , 450 , 300 , 42);
        b1.addActionListener(this);
        l1.add(b1);

//stick to end
        setLayout(null);
        setVisible(true);
    //adding blinking
        while(true){
            j2.setVisible(false);
            try{
                Thread.sleep(500);
            }catch(Exception e){}

            j2.setVisible(true);
            try{
                Thread.sleep(500);
            }catch(Exception e){}
        }
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            new login().setVisible(true);
            this.setVisible(false);
        }
    }
    
    public static void main(String[] args){
        System.out.println("Hello, World!");
        new App();
    }
}
