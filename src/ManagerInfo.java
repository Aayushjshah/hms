import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;
import java.awt.Font;
public class ManagerInfo extends JFrame implements ActionListener{
    
    JTable t1;
    JButton b1,b2;
    String[] arr = {"Name", "Age" , "Gender" , "Salary","Job" , "AadharNO.","PhoneNO.","Email"};

    Font forLabel = new Font("Tahoma" , Font.PLAIN ,17);
    ManagerInfo(){
       t1 = new JTable();
       t1.setFont(new Font("Tahoma" , Font.PLAIN , 15));
       t1.setBounds(0,40,1000,500);
       add(t1);
        
        JLabel[] jarr = new JLabel[8];
       //label
       int i=0,x=20;
       for(;i<8;i++){
        jarr[i] = new JLabel(arr[i]);
        jarr[i].setBounds(x,10,85,30);
        x+=125;
        jarr[i].setFont(forLabel);
        add(jarr[i]);
       }

       //buttons
       b1 = new JButton("Load Data");
       b1.setBounds(350,550,120,30);
       b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(530,550,120,30);
        b2.addActionListener(this);
        add(b2);
     
   //main Pane   
        setBounds(300,170,1000,650);
        getContentPane().setBackground(Color.WHITE);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            conn n = new conn();
            String query="Select * from employee where job='Manager'";
            try{
                ResultSet rs = n.s.executeQuery(query);
                t1.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }


    public static void main(String[] args){
        new ManagerInfo();
    }
}
