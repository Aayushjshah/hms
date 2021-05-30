import java.awt.Font;
import java.awt.Color;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.Choice;

public class AddCustomer extends JFrame implements ActionListener{
    
    JButton b1,b2;
    JTextField[] tfArr=new JTextField[5];
    JComboBox<String> c1;
    JComboBox<Integer>c2;
    JRadioButton r1,r2;
    Font forLabel = new Font("Serif" , Font.PLAIN ,17);
    Choice cl;
    conn c = new conn();


    AddCustomer(){

        String[] labels = {"ID" ,"Name" , "Number" , "Gender" , "Country" , "Allocated Room No." , "CheckedIn" ,"Deposit"};
        
        int i,x = 40,y =40;
        JLabel[] lArr = new JLabel[8];
        for(i=0;i<8;i++){
            lArr[i]=new JLabel(labels[i]);
            lArr[i].setBounds(20,x,150,30);
            lArr[i].setFont(forLabel);
            x+=55;
            add(lArr[i]);
        }
        String[] t1 ={"Passport" , "Aadhar Card" ,"Driving Liscense", "voterID"};
        c1 = new JComboBox<String>(t1);
        c1.setFont(forLabel);
        c1.setBounds(180,y,150,30);
        c1.setForeground(Color.MAGENTA);
        add(c1);
        y+=55;

        int m=0;
        for(i=1;i<8;i++){
            if(i==3){
                r1 = new JRadioButton("Male");
                r1.setBounds(180,y,75,30);
                r1.setFont(forLabel);
                r1.setBackground(Color.WHITE);
                add(r1);

                r2 = new JRadioButton("Female");
                r2.setBackground(Color.WHITE);
                r2.setFont(forLabel);
                r2.setBounds(275,y,75,30);
                add(r2);
                y+=55;
                continue;
            }if(i==5){


                
                ArrayList<Integer> roomL = new ArrayList<Integer>();
                String query = "Select roomNo from room";
                
                cl =new Choice();
                try{
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()){
                        // System.out.println(rs.getInt(1));
                        roomL.add(rs.getInt(1));
                        cl.add(Integer.toString(rs.getInt(1)));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

                // c2 = new JComboBox<Integer>((Integer[])roomL.toArray());
                cl.setBounds(180,y,75,30);
                cl.setFont(forLabel);
                cl.setForeground(Color.MAGENTA);
                add(cl);
                y+=55;
                continue;
            }
            tfArr[m]=new JTextField();
            tfArr[m].setBounds(180,y,150,30);
            tfArr[m].setFont(forLabel);
            add(tfArr[m]);
            m++;
            y+=55;
        }

//heading
        JLabel head = new JLabel("Add Customer Form");
        
        head.setFont(new Font("Tahoma",Font.PLAIN , 22));
        head.setBounds(450,20,200,30);
        head.setForeground(Color.MAGENTA);
        add(head);

//button
        b1 = new JButton("ADD CUSTOMER");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        b1.setBounds(20,x+20,150,30);
        b1.setFont(new Font("Tahoma",Font.PLAIN , 15));
        add(b1);
    
        b2 = new JButton("Back");
        b2.setBounds(180,x+20,150,30);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.addActionListener(this);
        b2.setFont(new Font("Tahoma",Font.PLAIN , 15));
        add(b2);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel li = new JLabel(i3);
        li.setBounds(370,100,400,400);
        add(li);


        //mainPane    
        setBounds(350,170,800,600);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
           //set room as occupied
            String[] iid =new String[8];
            iid[0]=c1.getSelectedItem().toString();
            iid[1]=tfArr[0].getText();
            int m=0;
            for(int i=1;i<8;i++){

                if(i == 3){
                    if(r1.isSelected()){
                        iid[i] = "Male";
                    }else{
                        iid[i]= "Female";
                    }
                    continue;
                }if(i== 5){
                    iid[i] = cl.getSelectedItem();
                    continue;
                }

                iid[i]=tfArr[m].getText();
                m++;
            }

            
            try{
                String query = "insert into customer values('";
                // + iid[0]
                for(int i=0;i<7;i++){
                    query+= iid[i] + "','";
                }
                query+=iid[7]+"')";
                // System.out.println(query);
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Customer added sucessfully");
                this.setVisible(false);
            }catch(Exception e){e.printStackTrace();}


            
        }else{
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }
    public static void main(String[] args){
        new AddCustomer();
    }
}
