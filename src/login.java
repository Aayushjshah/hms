//package hms;
// package hotel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener{
    
    JLabel l1,l2;
    JTextField t1;//,t2;
    JPasswordField t2;
    JButton b1,b2;
    login(){
        //variable declarations abv constructoirs are public!
        setBounds(400,200,600,300);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(300,50,300,200);
        add(l3);

        //add username textfield
        l1 = new JLabel("Username:");
        l1.setFont(new Font("Tahoma" , Font.PLAIN , 20));
        l1.setBounds(20,50,100,30);
        add(l1);

        //textField

        t1 = new JTextField("username");
        t1.setBounds(127,54,150,26);
        t1.setFont(new Font("Tahoma" , Font.PLAIN , 20));
        add(t1);

        l2 = new JLabel("Password");
        // l2.setFont()
        l2.setBounds(20 , 110 , 100 , 30);
        l2.setFont(new Font("Tahoma" , Font.PLAIN , 20));
        add(l2);

        t2 = new JPasswordField();
        t2.setBounds(127, 114 , 150,26);
        t2.setFont(new Font("Tahoma" , Font.PLAIN , 20));
        add(t2);

        //buttons

        b1 = new JButton("Login");
        b1.setBounds(20,180 , 100 , 30);
        b1.setFont(new Font("Tahoma" , Font.BOLD , 22));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);
        

        b2 = new JButton("Cancel");
        b2.setBounds(150,180,130 , 30);
        b2.setFont(new Font("Tahoma" , Font.BOLD , 22));
        b2.setBackground(Color.BLACK);
        b2.addActionListener(this);
        b2.setForeground(Color.WHITE);
        add(b2);



    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String username = t1.getText();
            String password = new String(t2.getPassword());
            
            
            conn n = new conn(); 
            String query = "select * from login where username ='"+username +"' and password = '"+password+"'";
           try{
                ResultSet rs = n.s.executeQuery(query);
                if(rs.next()){
                    //open next pane
                    System.out.println("login successful");
                    this.setVisible(false);
                    new Dashboard().setVisible(true);
                    
                }else{
                    //Give a message
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                    // this.setVisible();
                }
           }catch(Exception e){
               e.printStackTrace();
           }
        }else if(ae.getSource() == b2){
            // new App().setVisible(true);
            // this.setVisible(false);
            System.exit(0);
        }
    }

    // public static void main(String[] args){
    //     new login();
    // }
}
