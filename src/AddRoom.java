import javax.swing.*;
import java.awt.event.*;
// import java.sql.*;

import java.awt.*;
public class AddRoom extends JFrame implements ActionListener{
    Font forLabel = new Font("Tahoma" , Font.PLAIN , 17);
    JTextField t1,t2;
    JComboBox<String> avail , clean , bed ;
    JButton submit,back;

    AddRoom(){
        JLabel l1 = new JLabel("ADD ROOMS");
        l1.setFont(new Font("Tahoma" , Font.PLAIN , 30));
        l1.setForeground(Color.MAGENTA);
        l1.setBounds(600,20,300,30);
        add(l1);

        

        JLabel l2 = new JLabel("Room Number::");
        JLabel l3 = new JLabel("Availability::");
        JLabel l4 = new JLabel(" Status::");
        JLabel l5 = new JLabel("Price::");
        JLabel l6 = new JLabel("Bed Type::");

        JLabel[] arr ={l2,l3,l4,l5,l6};
        int i=0;
        int x =50; int y=50;

        for(i=0;i<5;i++){
            arr[i].setFont(forLabel);
            arr[i].setBounds(60,x,120,27);
            add(arr[i]);
            x+=60;
        }

      //textfirleds
      
      t1 = new JTextField();
      t1.setFont(forLabel);
      t1.setBounds(200,y,150,27);
      y+= 60;
      add(t1);

      
        
      String[] bedArr ={"SINGLE BED" , "DOUBLE BED"};
      String[] availArr = {"Available" , "Occupied"};
      String[] cleanArr = {"cleaned" , "Dirty"};
        clean = new JComboBox<String>(cleanArr);
        bed = new JComboBox<String>(bedArr);
        avail = new JComboBox<String>(availArr);
        JComboBox[] jc={clean , avail , bed};
        
        for(i=0;i<3;i++){
            jc[i].setFont(forLabel);
            if(i==2){
                t2 = new JTextField();
                t2.setFont(forLabel);
                t2.setBounds(200,y,150,27);
                y+= 60;
                add(t2);
            }
            jc[i].setForeground(Color.MAGENTA);
            jc[i].setBounds(200,y,150,27);
            add(jc[i]);
            y+= 60;
        }
        
        //Image
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 340, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel li = new JLabel(i3);
        li.setBounds(380,70,500,340);
        add(li);
            


//buttons
        submit = new JButton("Submit");
        submit.setFont(forLabel);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setBounds(80,x,100,27);
        add(submit);

        back = new JButton("Back");
        back.setFont(forLabel);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.setBounds(210,x,100,27);
        back.addActionListener(this);
        add(back);


        setBounds(300,200,900,460);
        setLayout(null); 
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        // JPanel contentPane = new JPanel();
        // setContentPane(contentPane);
        // contentPane.setLayout(null);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            // System.out.println("aaaa");

            String[] dbArr =new String[5];
            dbArr[0]=t1.getText();//room no.
            dbArr[1]= avail.getSelectedItem().toString();
            dbArr[2]= clean.getSelectedItem().toString();
            int cl,bt;
            if(dbArr[2] == "cleaned"){
                cl = 1;
            }else{
                cl = 0;
            }
            dbArr[3]= t2.getText();
            dbArr[4]=bed.getSelectedItem().toString();
            if(dbArr[4] == "SINGLE BED"){
                bt = 1;
            }else{
                bt = 2;
            }
            conn c = new conn();
            
            String query = "insert into room values('" + dbArr[0] + "' , '" + dbArr[1] + "' ," + cl + " , '" 
            + dbArr[3] + "' , " + bt + ")";
            System.out.println(query);
            try{
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Room added successfully!");
                this.setVisible(false);

            }catch(Exception e){
                e.printStackTrace();
            }





        }if(ae.getSource() == back){
            this.setVisible(false);
        }
    }
    public static void main(String[] sarr){
        new AddRoom();
    }
    
}
