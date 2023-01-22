import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
public class GUI extends JFrame{

    JLabel labe2=new JLabel("username:");
    JLabel labe=new JLabel("password:");
    JButton btn2=new JButton("Login");
    JButton btn3=new JButton("Logout");
    JTextField inputField1=new JTextField(20);
    JPasswordField inputField2=new JPasswordField(20);
    JPanel btnP1 = new JPanel();
    public GUI(String s,int w,int h){
        init(s);
        ii();
        GUITools.center(this);
        setSize(w, h);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void init(String s) {
        setTitle(s);
        GUITools.setTitleImage(this, ".\\title.png");
        btnP1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(btnP1);
        btnP1.setLayout(null);
        labe.setForeground(Color.blue);
        labe.setBounds(60, 139, 70, 15);
        btnP1.add(labe);
        inputField1.setBounds(140, 84, 164, 28);
        btnP1.add(inputField1);
        inputField2.setBounds(140, 133, 164, 28);
        btnP1.add(inputField2);
        labe2.setForeground(Color.blue);
        labe2.setBounds(60, 90, 70, 15);
        btnP1.add(labe2);
        btn2.setBounds(125, 186, 87, 28);
        btnP1.add(btn2);
        btn3.setBounds(240, 186, 87, 28);
        btnP1.add(btn3);
        JLabel label_2 = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        label_2.setFont(new Font("Dialog", Font.BOLD, 15));
        label_2.setForeground(Color.BLACK);
        label_2.setBounds(80, 40, 280, 34);
        btnP1.add(label_2);
    }
    public void ii() {
        btn2.addActionListener(e->{
            String content=inputField1.getText();
            String content2=String.valueOf(inputField2.getPassword());
            if((content!=null&&!content.trim().equals(""))&&(content2!=null&&!content2.trim().equals(""))) {
                if(!new Password().Pa(content, content2)) {
                    JOptionPane.showMessageDialog(null,"Account password error","message reminding",JOptionPane.ERROR_MESSAGE);
                    inputField2.setText("");
                }else {
                    this.dispose();
                    File f=new File("employee.txt");
                    if(!f.exists()) {
                        try {
                            f.createNewFile();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }else {
                        try {
                            FileRead.read();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    new GUI2(this,true);
                }
            }else {
                JOptionPane.showMessageDialog(null,"Invalid user","message reminding",JOptionPane.ERROR_MESSAGE);
            }
        });
        btn3.addActionListener(e->{
            System.exit(0);
        });
    }

}

class GUITools {

    static Toolkit kit = Toolkit.getDefaultToolkit();

    public static void center(Component c) {
        int x = (kit.getScreenSize().width - c.getWidth()) / 2;
        int y = (kit.getScreenSize().height - c.getHeight()) / 2;
        c.setLocation(x, y);
    }
    public static void setTitleImage(JFrame frame,String titleIconPath) {
        frame.setIconImage(kit.createImage(titleIconPath));
    }
}



class GUI2 extends JDialog{

    JScrollPane tablePane = new JScrollPane();
    JTable table = new JTable();
    JPanel panel=new JPanel();
    JLabel label=new JLabel("Enter the id：");
    JTextField inputField=new JTextField(15);
    JButton btn=new JButton("search");
    JButton btn2=new JButton("refresh");
    JPanel pane2=new JPanel();
    JButton btny1,btny2,btny3,btny4,btny5;
    JLabel labe2=new JLabel("XX Limited Company");
    JPanel pane3=new JPanel();
    JPanel pane=new JPanel();
    JPanel panei=new JPanel();
    JPanel panej=new JPanel();
    public GUI2(Frame owner, boolean modal) {
        super(owner, modal);
        init2();
        addComponent();
        jj();
        queryStudent();
        setVisible(true);
    }
    private void init2() {
        this.setTitle("EMPLOYEE MANAGEMENT SYSTEM!");
        this.setSize(600,600);
        GUITools.center(this);
        this.setResizable(false);
    }
    private void addComponent() {
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setEnabled(false);
        tablePane.setBounds(50, 50, 500, 100);
        tablePane.setViewportView(table);
        pane3.add(tablePane);
        panel.add(label);
        panel.add(inputField);
        panel.add(btn);
        panel.add(btn2);
        btny1=new JButton("Delete ");
        btny2=new JButton("Modify ");
        btny3=new JButton("Add    ");
        btny4=new JButton("Use help");
        btny5=new JButton("Exit system");
        pane2.setLayout(new BorderLayout());  //给按钮的总面板布局
        panei.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
        panej.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
        panei.add(btny1);
        panei.add(labe2);
        panei.add(btny2);
        panej.add(btny3);
        panej.add(btny4);
        panej.add(btny5);
        pane2.add(panei,BorderLayout.PAGE_START);
        pane2.add(panej,BorderLayout.CENTER);
        pane.setLayout(new BorderLayout());
        pane.add(panel,BorderLayout.PAGE_START);
        pane.add(pane3,BorderLayout.CENTER);
        pane.add(pane2,BorderLayout.PAGE_END);
        this.add(pane);
    }
    public void jj() {
        btn.addActionListener(e->{
            String content=inputField.getText();
            if(content!=null&&!content.trim().equals("")) {
                String h=content.replace(" ","");
                String[][] tbody = new String[1][7];
                for (int i = 0; i < chief.data.size(); i++) {
                    if(h.equals(chief.data.get(i).getId())) {
                        tbody[0][0]=chief.data.get(i).getId();
                        tbody[0][1]=chief.data.get(i).getName();
                        tbody[0][2]=chief.data.get(i).getPosition();
                        tbody[0][3]=chief.data.get(i).getPosition()+"";
                        tbody[0][4]=chief.data.get(i).getWage();
                        tbody[0][5]=chief.data.get(i).getAllowances();
                        tbody[0][6]=chief.data.get(i).getSalary();
                        String[] thead = {"id","name","position","contact","wage","allowance","salary"};
                        TableModel dataModel = new DefaultTableModel(tbody, thead);
                        table.setModel(dataModel);
                        inputField.setText("");
                        break;

                    }else {
                        if(i==(chief.data.size()-1)) {
                            JOptionPane.showMessageDialog(null,"The employee does not exist","message reminding",JOptionPane.ERROR_MESSAGE);
                            inputField.setText("");

                        }
                    }
                }

            }
        });
        btn2.addActionListener(e->{
            queryStudent();
        });
        btny1.addActionListener(e->{
            aee("Delete employee","cancel",1);
        });
        btny2.addActionListener(e->{
            ass("Modify employee","modify",2);
        });
        btny3.addActionListener(e->{
            ass("Add employee","add",3);
        });
        btny4.addActionListener(e->{
            aee("Help document","Exit",4);
        });
        btny5.addActionListener(e->{
            try {
                FileSave.save();
            } catch (IOException e1) {

                e1.printStackTrace();
            }
            System.exit(0);
        });
    }

    public void aee(String u,String s,int g) {
        JDialog j=new JDialog(this);
        j.setTitle(u);
        j.setSize(400,320);
        GUITools.center(j);
        j.setResizable(false);
        JLabel la1=new JLabel("    id");
        JLabel la2=new JLabel("1. Enter the original employee ID and modified information.");
        JLabel la3=new JLabel("2. Enter the information of the employees to be added.");
        JLabel la4=new JLabel("*Exit the system can automatically save employee information*");
        JTextField in1=new JTextField(15);
        JPanel btnP1 = new JPanel();
        JPanel btnP2 = new JPanel();
        JButton btn=new JButton("delete");
        JButton btn2=new JButton(s);
        JPanel btnP3 = new JPanel();
        btnP3.setLayout(new BorderLayout());
        if(g==4) {
            btnP1.setLayout(new BorderLayout());
            //btnP1.add(la2);
            btnP1.add(la2,BorderLayout.PAGE_START);
            btnP1.add(la3,BorderLayout.CENTER);
            btnP1.add(la4,BorderLayout.PAGE_END);
            btnP2.add(btn2);
            btnP3.add(btnP1,BorderLayout.PAGE_START);
            btnP3.add(btnP2,BorderLayout.CENTER);
            j.add(btnP3);
            j.setVisible(true);

            btn2.addActionListener(e->{
                j.dispose();
            });

        }

        if(g==1) {

            btnP1.add(la1);
            btnP1.add(in1);
            btnP2.add(btn);
            btnP2.add(btn2);
            btnP3.add(btnP1,BorderLayout.PAGE_START);
            btnP3.add(btnP2,BorderLayout.CENTER);
            j.add(btnP3);
            j.setVisible(true);

            btn.addActionListener(e->{
                String con1=in1.getText();
                String h=con1.replace(" ","");
                if(chief.data.size()==0) {
                    JOptionPane.showMessageDialog(null,"No employees currently.","warning",JOptionPane.ERROR_MESSAGE);
                    in1.setText("");
                }
                for (int i = 0; i < chief.data.size(); i++) {
                    if(h.equals(chief.data.get(i).getId())) {
                        in1.setText("");
                        chief.data.remove(i);
                        queryStudent();
                        break;

                    }
                    if(i==(chief.data.size()-1)) {
                        in1.setText("");
                        JOptionPane.showMessageDialog(null,"The employee does not exist","warning",JOptionPane.ERROR_MESSAGE);

                    }
                }
            });

            btn2.addActionListener(e->{
                j.dispose();

            });
        }

    }
    public void ass(String p,String w,int g) {
        JDialog j=new JDialog(this);
        j.setTitle(p);
        j.setSize(400,320);
        GUITools.center(j);
        j.setResizable(false);
        JLabel la1=new JLabel("       id");
        JLabel la2=new JLabel("     name");
        JLabel la3=new JLabel(" position");
        JLabel la4=new JLabel("  contact");
        JLabel la5=new JLabel("     wage");
        JLabel la6=new JLabel("allowance");
        JLabel la7=new JLabel("   salary");
        JTextField in1=new JTextField(15);
        JTextField in2=new JTextField(15);
        JTextField in3=new JTextField(15);
        JTextField in4=new JTextField(15);
        JTextField in5=new JTextField(15);
        JTextField in6=new JTextField(15);
        JTextField in7=new JTextField(15);
        JButton btn=new JButton(w);
        JButton btn2=new JButton("cancel");
        JPanel btnP1 = new JPanel();
        JPanel btnP2 = new JPanel();
        JPanel btnP3 = new JPanel();
        JPanel btnP4 = new JPanel();
        JPanel btnP5 = new JPanel();
        JPanel btnP6 = new JPanel();
        JPanel btnP7 = new JPanel();
        JPanel btnP8 = new JPanel();
        JPanel btnP9 = new JPanel();
        JPanel btnP10 = new JPanel();
        JPanel btnP11 = new JPanel();
        JPanel btnP12 = new JPanel();
        btnP8.setLayout(new BorderLayout());
        btnP9.setLayout(new BorderLayout());
        btnP10.setLayout(new BorderLayout());

        btnP1.add(la1);
        btnP1.add(in1);
        btnP2.add(la2);
        btnP2.add(in2);
        btnP3.add(la3);
        btnP3.add(in3);
        btnP4.add(la4);
        btnP4.add(in4);
        btnP5.add(la5);
        btnP5.add(in5);
        btnP6.add(la6);
        btnP6.add(in6);
        btnP7.add(la7);
        btnP7.add(in7);
        btnP12.add(btn);
        btnP12.add(btn2);

        btnP8.add(btnP1,BorderLayout.PAGE_START);
        btnP8.add(btnP2,BorderLayout.CENTER);
        btnP8.add(btnP3,BorderLayout.PAGE_END);

        btnP9.add(btnP4,BorderLayout.PAGE_START);
        btnP9.add(btnP5,BorderLayout.CENTER);

        btnP10.add(btnP6,BorderLayout.PAGE_START);
        btnP10.add(btnP7,BorderLayout.CENTER);
        btnP10.add(btnP12,BorderLayout.PAGE_END);


        j.setLayout(new BorderLayout());
        j.add(btnP8,BorderLayout.PAGE_START);
        j.add(btnP9,BorderLayout.CENTER);
        j.add(btnP10,BorderLayout.PAGE_END);
        j.setVisible(true);
        btn.addActionListener(e->{
            String con1=in1.getText();
            String con2=in2.getText();
            String con3=in3.getText();
            String con4=in4.getText();
            String con5=in5.getText();
            String con6=in6.getText();
            String con7=in7.getText();
            String h=con1.replace(" ","");
            int y=chief.data.size();
            if(y==0&&g==3) {
                chief.data.add(new Employee(con1,con2,con3,con4,con5,con6,con7));
                queryStudent();
                in1.setText("");
                in2.setText("");
                in3.setText("");
                in4.setText("");
                in5.setText("");
                in6.setText("");
                in7.setText("");
            }
            if(y==0&&g==2) {
                JOptionPane.showMessageDialog(null,"No employees currently.","warning",JOptionPane.ERROR_MESSAGE);
            }
            for (int i = 0; i < y; i++) {
                if(h.equals(chief.data.get(i).getId())&&g==2) {
                    chief.data.set(i,new Employee(con1,con2,con3,con4,con5,con6,con7));
                    queryStudent();
                    in1.setText("");
                    in2.setText("");
                    in3.setText("");
                    in4.setText("");
                    in5.setText("");
                    in6.setText("");
                    in7.setText("");
                    break;
                }
                if(h.equals(chief.data.get(i).getId())&&g==3) {
                    in1.setText("");
                    JOptionPane.showMessageDialog(null,"The employee with the ID already exists","message reminding",JOptionPane.ERROR_MESSAGE);
                    break;

                }
                if(i==(chief.data.size()-1)&&g==3) {
                    chief.data.add(new Employee(con1,con2,con3,con4,con5,con6,con7));
                    queryStudent();
                    in1.setText("");
                    in2.setText("");
                    in3.setText("");
                    in4.setText("");
                    in5.setText("");
                    in6.setText("");
                    in7.setText("");

                }
                if(i==(chief.data.size()-1)&&g==2) {
                    in1.setText("");
                    JOptionPane.showMessageDialog(null,"The employee does not exist","warning",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btn2.addActionListener(e->{
            j.dispose();

        });

    }

    public void queryStudent() {

        String[] thead = {"id","name","position","contact","wage","allowance","salary"};
        ArrayList<Employee> dataList =chief.data;
        String[][] tbody = list2Array(dataList);

        TableModel dataModel = new DefaultTableModel(tbody, thead);
        table.setModel(dataModel);
    }

    public String[][] list2Array(ArrayList<Employee> list){
        String[][] tbody = new String[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            Employee staff = list.get(i);
            tbody[i][0] = staff.getId();
            tbody[i][1] = staff.getName();
            tbody[i][2] = staff.getPosition();
            tbody[i][3] = staff.getPosition()+"";
            tbody[i][4] = staff.getWage();
            tbody[i][5] = staff.getAllowances();
            tbody[i][6] = staff.getSalary();
        }
        return tbody;
    }
}
