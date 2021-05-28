import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;
public class EmployeeInfo extends JFrame implements ActionListener{
    JTable t1;
    JButton b1,b2;

    String[] arr = {"Name", "Age" , "Gender" , "Salary","Job" , "AadharNO.","PhoneNO.","Email"};
    EmployeeInfo(){
        
        
        t1 = new JTable();
        t1.setFont(new Font("Tahoma" , Font.PLAIN , 15));
        t1.setBounds(0,40,1000,500);
        // t1.setBackground(Color.MAGENTA);
        add(t1);
    Font forLabel = new Font("Tahoma", Font.PLAIN, 17);
//label
        JLabel[] jarr =new JLabel[8];
        int i=0,x=20; 
        for(;i<8;i++){ 
            
            jarr[i] = new JLabel(arr[i]);
            jarr[i].setBounds(x,10,85,30);
            x+=125;
            jarr[i].setFont(forLabel);
            add(jarr[i]);
        }

     //buttons
     b1 = new JButton("Load DATA");
     b1.setBounds(350,550,120,30);
     b1.setBackground(Color.BLACK);
     b1.setForeground(Color.WHITE);
     b1.addActionListener(this);
     add(b1);

     b2 = new JButton("BACK");
     b2.setBounds(530,550,120,30);
     b2.setBackground(Color.BLACK);
     b2.setForeground(Color.WHITE);
     b2.addActionListener(this);
     add(b2);

        //main pane        
        setBounds(300,170,1000,650);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try{
                conn c = new conn();
                String query = "select * from employee";
                ResultSet rs =c.s.executeQuery(query);
                t1.setModel(DbUtils.resultSetToTableModel(rs)); //used to convert returned answers from database into table format
              

                //part of rs2xml.jar file from net.proteanit.sql.*



            }catch(Exception e){
                System.out.println("In catch employee");
                e.printStackTrace();
            }
        }else if(ae.getSource() == b2){
            //back
            this.setVisible(false);
            new Reception().setVisible(true);

        }
    }

    public static void main(String[] args){
        new EmployeeInfo();
    }
    
}
