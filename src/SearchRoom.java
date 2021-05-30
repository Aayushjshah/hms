import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;
import javax.swing.table.*;

public class SearchRoom extends JFrame implements ActionListener{
    

    JButton b1,b2;
    JCheckBox cb1;
    Font forLabel = new Font("Tahoma" , Font.PLAIN , 17);
    JTable t1;
    JComboBox<String> c1;
    DefaultTableModel dtm;

    SearchRoom(){
        

        JLabel head = new JLabel("Search For Room");
        head.setFont(new Font("Serif" , Font.PLAIN , 28));
        head.setBounds(370,20,300,30);
        head.setForeground(Color.RED);
        add(head);
        

        JLabel l1 = new JLabel("Room Bed Type");
        l1.setBounds(60,80,150,30);
        l1.setFont(forLabel);
        add(l1);
        String[] carr = {"SingleBed" , "DoubleBed" , "All"};
        c1 = new JComboBox<String>(carr);
        c1.setBounds(230,80,150,30);
        c1.setFont(forLabel);
        add(c1);

        cb1 = new JCheckBox("Only Display Available");
        cb1.setBackground(Color.WHITE);
        cb1.setFont(forLabel);
        cb1.setBounds(650,80,200,30);
        add(cb1);


        JLabel[] arr = new JLabel[5];
        String[] tarr ={"Room Number" , "Availability" , "Cleaning Status" ,"Price" , "Bed Type"};


        int i=0;
        int y=30;
        for(;i<5;i++){
            arr[i] = new JLabel(tarr[i]);
            arr[i].setFont(forLabel);
            arr[i].setBounds(y,130,150,30);
            add(arr[i]);
            y+=200;
        }

        String[] ss={"a" ,"b" , "c" , "d" , "e"};
        dtm = new DefaultTableModel(ss,0);
        t1 = new JTable(dtm);
        t1.setBounds(0,160 , 1000 , 400);
        t1.setFont(forLabel);
        
        add(t1);


        b1 = new JButton("Submit");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setFont(forLabel);
        b1.addActionListener(this);
        b1.setBounds(250,650,150,30);
        add(b1);


        b2 = new JButton("Back");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setFont(forLabel);
        b2.setBounds(550, 650,150,30);
        b2.addActionListener(this);
        add(b2);

        



        //mainPane
        setBounds(300,60,1000,750);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            dtm.setRowCount(0);
            conn c = new conn();
            // SingleBed" , "DoubleBed" , "All"
            String query = "select * from room";
            if(c1.getSelectedItem()=="SingleBed"){
                query ="select * from room where bedtype=1";
                if(cb1.isSelected()){
                    query += " and available='Available'";
                }
            }else if(c1.getSelectedItem()=="DoubleBed"){
                query = "select * from room where bedtype=2";
                if(cb1.isSelected()){
                    query += " and available='Available'";
                }
            }else{
                if(cb1.isSelected()){
                    query += " where available='Available'";
                }
            }

            // System.out.println(query);
            
            try{
            
                String[] addRow = new String[5];
                ResultSet rs = c.s.executeQuery(query);
               while(rs.next()){
                    addRow[0]=rs.getString(1);
                    addRow[1]=rs.getString(2);
                    addRow[2]="Cleaned";
                    if(rs.getInt(3) == 0){
                        addRow[2]="Dirty";
                    }
                    addRow[3]=rs.getString(4);
                    addRow[4]="Single Bed";
                    if(rs.getInt(5) == 2){
                        addRow[4] ="Double Bed";
                    }
                    // t1.setModel(DbUtils.resultSetToTableModel(rs));
                    // System.out.println(addRow[3]);
                    dtm.addRow(addRow);
               }
            }catch(Exception e){
                e.printStackTrace();
            }


        }else{
            //back
            new Reception().setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args){
        new SearchRoom().setVisible(true);
    }
}
