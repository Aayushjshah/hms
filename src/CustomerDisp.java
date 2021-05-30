import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

// import java.
public class CustomerDisp extends JFrame implements ActionListener{
    
    JTable t1;
    JButton b1,b2;

    
    
    CustomerDisp(){

        JLabel[] head = new JLabel[8];
        String[] hL = {"Document Type" , "Number" ,"Name" , "Gender" , "Country" , "Room Number" , "Status" , "Deposit"};
        int i=0;
        int x=0;
        Font forLabel = new Font("Tahoma" , Font.PLAIN , 17);

        head[i] = new JLabel(hL[i]);
            head[i].setBounds(x,10,145,30);
            head[i].setFont(forLabel);
            add(head[i]);
            x+=135;
        
        
        for(i=1;i<8;i++){
            head[i] = new JLabel(hL[i]);
            head[i].setBounds(x,10,115,30);
            head[i].setFont(forLabel);
            add(head[i]);
            x+=125;
        }
        t1 = new JTable();
        t1.setFont(new Font("Tahoma" , Font.PLAIN ,15));
        t1.setBounds(0,40,1000,500);
        add(t1);
        
        b1 =new JButton("LOAD DATA");
        b1.setBounds(350,550,120,30);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        b1.setBackground(Color.BLACK);
        add(b1);


        b2 = new JButton("BACK");
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        b2.setBackground(Color.BLACK);
        b2.setBounds(550,550,120,30);
        add(b2);

        //mainPane
        setBounds(300,170,1000,650);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            
            conn c = new conn();
            String query = "select * from customer";
            
            try{
                ResultSet rs = c.s.executeQuery(query);
                t1.setModel(DbUtils.resultSetToTableModel(rs));

            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            this.setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args){
        new CustomerDisp();
    }
    
}
