package Gui;
import Attributes.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EditProduct extends JFrame implements ActionListener {
    JTextField name,id,pic,price;
    JButton enter;
    String cat;
    int index;
    EditProduct(String category,int i){
        index=i;
        cat=category;
        setTitle(cat);
        ImageIcon mainlogo=new ImageIcon("images\\mainlogo.png");
        setIconImage(mainlogo.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(500,500);
        setLayout(null);
        setLocationRelativeTo(null);

        ArrayList<Products> P=Products.ReadFile(cat);

        JPanel head=new JPanel(null);
        head.setBounds(0,0,500,100);
        head.setBackground(new Color(82, 190, 128));
        JLabel heading=new JLabel(P.get(i).getName()+" Details");
        heading.setFont(new Font("Gotham",Font.BOLD,25));
        heading.setBounds(15,25,200,50);

        JButton main =new JButton("Admin Menu");
        main.setForeground(Color.white);
        main.setBackground(new Color(99, 57, 116));
        main.setBounds(350,50,110,40);
        main.setFocusable(false);
        main.setFont(new Font("Tahoma",Font.BOLD,13));
        main.addActionListener(E -> {new AdminMenu();dispose();});

        head.add(heading);
        head.add(main);

        JPanel body=new JPanel(null);
        body.setBounds(0,100,500,400);
        body.setBackground(new Color(33, 47, 61));

        JLabel n=new JLabel("Name:");
        n.setForeground(Color.white);
        n.setFont(new Font("Roomey",Font.PLAIN,14));
        n.setBounds(30,30,100,40);
        name=new JTextField();
        name.setBounds(100,40,90,23);
        name.setFont(new Font("helvetica",Font.PLAIN,14));
        name.setBorder(BorderFactory.createEtchedBorder());
        name.setText(P.get(i).getName());

        JLabel Id=new JLabel("ID:");
        Id.setForeground(Color.white);
        Id.setFont(new Font("Roomey",Font.PLAIN,14));
        Id.setBounds(220,30,90,40);
        id=new JTextField();
        id.setBounds(260,40,90,23);
        id.setFont(new Font("helvetica",Font.PLAIN,14));
        id.setBorder(BorderFactory.createEtchedBorder());
        id.setText(P.get(i).getId());

        JLabel p=new JLabel("Price:");
        p.setForeground(Color.white);
        p.setFont(new Font("Roomey",Font.PLAIN,14));
        p.setBounds(30,90,100,40);
        price=new JTextField();
        price.setBounds(100,100,70,23);
        price.setFont(new Font("helvetica",Font.PLAIN,14));
        price.setBorder(BorderFactory.createEtchedBorder());
        price.setText(String.valueOf(P.get(i).getPrice()));

        JLabel c=new JLabel("Category:");
        c.setForeground(Color.white);
        c.setFont(new Font("Roomey",Font.PLAIN,14));
        c.setBounds(30,145,100,40);
        JLabel C=new JLabel(P.get(i).getCategory());
        C.setFont(new Font("Helvetica",Font.BOLD,14));
        C.setBounds(120,155,180,25);
        C.setForeground(Color.white);

        JLabel s=new JLabel("Quantity:");
        s.setForeground(Color.white);
        s.setFont(new Font("Roomey",Font.PLAIN,14));
        s.setBounds(190,90,100,40);
        JLabel stock=new JLabel(String.valueOf(P.get(i).getStock()));
        stock.setBounds(270,100,70,23);
        stock.setFont(new Font("helvetica",Font.PLAIN,14));
        stock.setForeground(Color.white);

        JLabel img=new JLabel("Image name:");
        img.setForeground(Color.white);
        img.setFont(new Font("Roomey",Font.PLAIN,14));
        img.setBounds(30,195,100,40);
        pic=new JTextField();
        pic.setBounds(130,205,150,23);
        pic.setFont(new Font("helvetica",Font.PLAIN,14));
        pic.setBorder(BorderFactory.createEtchedBorder());
        pic.setText(P.get(i).getImg());

        JLabel d=new JLabel("Date of Expiry:");
        d.setForeground(Color.white);
        d.setFont(new Font("Roomey",Font.PLAIN,14));
        d.setBounds(30,250,100,40);
        JLabel date=new JLabel(P.get(i).getDoe().getDay()+"/"+P.get(i).getDoe().getMonth()+"/"+P.get(i).getDoe().getYear());
        date.setBounds(140,260,100,23);
        date.setFont(new Font("Roomey",Font.BOLD,14));
        date.setForeground(Color.white);

        JButton back =new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(new Color(203, 67, 53));
        back.setBounds(405,315,60,30);
        back.setFocusable(false);
        back.setFont(new Font("Tahoma",Font.PLAIN,12));
        back.addActionListener(E -> {new ShowProducts(cat);dispose();});

        enter=new JButton("Update");
        enter.setForeground(Color.black);
        enter.setBackground(new Color(82, 190, 128));
        enter.setFont(new Font("roomey",Font.BOLD,16));
        enter.setBounds(200,305,100,40);
        enter.setFocusable(false);
        enter.addActionListener(this);

        body.add(n);
        body.add(name);
        body.add(Id);
        body.add(C);
        body.add(id);
        body.add(price);
        body.add(p);
        body.add(c);
        body.add(pic);
        body.add(img);
        body.add(s);
        body.add(stock);
        body.add(d);
        body.add(date);
        body.add(back);
        body.add(enter);

        setVisible(true);
        add(body);
        add(head);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==enter){
            if(!name.getText().equals("")){
                if(!id.getText().equals("")){
                    if(!price.getText().equals("")){
                        try{
                            int p=Integer.parseInt(price.getText());
                            if(!pic.getText().equals("")){
                                String img;
                                File f=new File("Images\\"+pic.getText()+".png");
                                if(f.exists()){
                                    img="Images\\"+pic.getText()+".png";
                                }
                                else{
                                    img="Images\\default.png";
                                    JOptionPane.showMessageDialog(null,"Default Image inserted!!","Not found",JOptionPane.ERROR_MESSAGE);
                                }
                                int opt=JOptionPane.showConfirmDialog(null,"Are u sure you want to Update?",name.getText(),JOptionPane.YES_NO_OPTION);
                                if(opt==0) {
                                    ArrayList<Products> P=Products.ReadFile(cat);
                                    P.get(index).setId(id.getText());
                                    P.get(index).setImg(img);
                                    P.get(index).setName(name.getText());
                                    P.get(index).setPrice(p);
                                    try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\"+cat, false))){
                                        outStream.writeObject(P);
                                    }
                                    catch(IOException exp){ }
                                    JOptionPane.showMessageDialog(null,"Updated Sucessfully!!","You Did it",JOptionPane.INFORMATION_MESSAGE);
                                    new ShowProducts(cat);
                                    dispose();
                                }
                            }
                            else
                                JOptionPane.showMessageDialog(null,"Enter name of Image!!","Error 101",JOptionPane.ERROR_MESSAGE);
                        }
                        catch (NumberFormatException N){
                            JOptionPane.showMessageDialog(null,"Price needs to be numbers!!","Dumb 101",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Enter Price and Quantity!!","Error 101",JOptionPane.ERROR_MESSAGE);

                }
                else
                    JOptionPane.showMessageDialog(null,"Enter Product Id!!","Error 101",JOptionPane.ERROR_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null,"Enter a name!!","Error 101",JOptionPane.ERROR_MESSAGE);
        }

    }
}
