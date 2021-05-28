import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;
import javax.swing.table.*;
public class RoomDisplay  extends JFrame implements ActionListener{
    JTable t1;
    String[] initHeading = {"RoomNO" , "Available" , "Clean" , "Price" , "BedType"};
     DefaultTableModel dtm;   
    JButton b1,b2;
    Font forLabel = new Font("Tahoma",Font.PLAIN , 17);
    RoomDisplay(){
        
        dtm = new DefaultTableModel(initHeading,0);
        t1 = new JTable(dtm);
        // String[] item={"A","B","C","D"};
        // dtm.addRow(item);
        t1.setFont(new Font("Serif" ,Font.PLAIN , 15));
        t1.setBounds(0,50,800,450);
        add(t1);


        b1 = new JButton("Load Data");
        b1.setFont(forLabel);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);
        b1.setBounds(230,530,120,30);
        
        
        b2 = new JButton("Back");
        b2.setFont(forLabel);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.addActionListener(this);
        add(b2);
        b2.setBounds(430,530,120,30);

        //main pane
        setBounds(300,170 , 800,600);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){

            conn c = new conn();
            String sql = "select * from room";
            try{
                ResultSet rs = c.s.executeQuery(sql);
                // t1.setModel(DbUtils.resultSetToTableModel(rs));
               dtm.addRow(initHeading);
               
                while(rs.next()){
                    String[] addRow = new String[5];
                    // System.out.println(rs.getInt(1));
                    addRow[0]=Integer.toString(rs.getInt(1));
                    addRow[1]=rs.getString(2);
                    if(rs.getInt(3) == 1){
                        addRow[2]="Cleaned";
                    }else{addRow[2] = "Dirty";}
                    addRow[3] = rs.getString(4);
                    if(rs.getInt(5) == 1){
                        addRow[4]="SingleBed";
                    }else{addRow[4] = "DoubleBed";}
                    

                    dtm.addRow(addRow);
                }
            }catch(Exception e){
                e.printStackTrace();
            }


        }else{
            this.setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args){
        new RoomDisplay();
    }
    
}
