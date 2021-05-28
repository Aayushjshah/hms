import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
public class AddDriver extends JFrame implements ActionListener{
    Font forLabel = new Font("Tahoma" , Font.PLAIN , 17);
    JTextField t1,t2,t3,t4,t5;
    JComboBox<String> c1,c2;
    JButton add,cancel;
    JTextField[]  tArr = {t1,t2,t3,t4,t5};
    AddDriver(){
    
        JLabel l1,l2,l3,l4,l5,l6,l7;

        l1=new JLabel("name:");
        l2= new JLabel("Age:");
        l3=new JLabel("Gender");
        l4 = new JLabel("Car Company:");
        l5= new JLabel("Car Model");
        l6 = new JLabel("Available");
        l7= new JLabel("Location");
        JLabel[] arr={l1,l2,l3,l4,l5,l6,l7};
        int i=0,j=0;
        int x,y;
        x=50 ; y=50;
        for(;i<7;i++){
            arr[i].setFont(forLabel);
            arr[i].setBounds(60,x,120,30);
            add(arr[i]);
            x+=60;
            if(j<5){
                if(i==2){
                    String[] forC1 = {"Male", "Female"};
                    c1= new JComboBox<String>(forC1);
                    c1.setFont(forLabel);
                    c1.setForeground(Color.MAGENTA);
                    c1.setBounds(200,y,150,30);
                    add(c1);
                    y+=60;
                    continue;
                }
                else if(i==5){
                    String[] forC2={"Yes","No"};
                    c2 = new JComboBox<String>(forC2);
                    c2.setForeground(Color.MAGENTA);
                    c2.setFont(forLabel);
                    c2.setBounds(200,y,150,30);
                    add(c2);
                    y+=60;
                    continue;
                }else{
                    tArr[j]=new JTextField();
                    tArr[j].setBounds(200,y,150,30);
                    y+=60;
                    tArr[j].setFont(forLabel);
                    add(tArr[j]);
                    j++;
                }
            }
        }

//Image

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 =i1.getImage().getScaledInstance(550,390,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel li = new JLabel(i3);
        li.setBounds(400,100,550,390);
        add(li);

        //Heading
        JLabel head = new JLabel("ADD DRIVERS");
        head.setFont(new Font("Tahoma" , Font.PLAIN , 26));
        head.setForeground(Color.MAGENTA);
        head.setBounds(550,20,300,30);
        add(head);
//buttons

        add = new JButton("Add Driver");
        add.setFont(forLabel);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.addActionListener(this);
        add.setBounds(50,x,150,30);
        add(add);

        cancel = new JButton("Cancel");
        cancel.setFont(forLabel);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setBounds(220,x,150,30);
        add(cancel);


        setBounds(250,200,1000,550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            
        String[] db=new String[5];
        int i=0;
        for(; i<5 ; i++){
            db[i]=tArr[i].getText();    
            // System.out.println(db[i]);
        }
        int gen=0,avail=0;
        if( c1.getSelectedItem().toString() == "Male"){
            gen = 1;
        }
        if(c2.getSelectedItem().toString()== "Yes"){ 
            avail=1;
        };
        
            
        //db part    
        String query = "insert into driver values('" + db[0] + "' , '" + db[1] + "' , "+ gen + ", '" + db[2] +
        "' , '"+db[3]+"' , " + avail + ",'" + db[4] + "')";
        conn c = new conn();
        try{
            c.s.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }

            JOptionPane.showMessageDialog(null,"Driver added sucessfully!");
            this.setVisible(false);
        }else if (ae.getSource() == cancel){
            this.setVisible(false);
        }
    }

    public static void main(String[] args){
        new AddDriver();
    }
}
