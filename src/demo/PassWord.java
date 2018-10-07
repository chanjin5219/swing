package demo;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PassWord extends JFrame implements ActionListener {
    //��ʼ�������
    private JPanel jp=new JPanel();
    //��ʼ����¼���
    private JPanel jp_1=new JPanel();
    //��ʼ��ע�����
    private JPanel jp_2=new JPanel();

    /*          ��¼����                    */
    //��¼�����ǩ
    private JLabel[] jlArray=new JLabel[]{
            new JLabel("�û���"),new JLabel("��    ��"),new JLabel(""),new JLabel("�����˺ţ���ע��...")
    };
    //��¼���水ť
    private JButton[] jbArray=new JButton[]{
            new JButton("��¼"),new JButton("���"),new JButton("ע��")
    };
    //��¼�������ֿ�
    private JTextField jtxtName=new JTextField("");
    private JPasswordField jtxtPassword=new JPasswordField("");

    /*         ע�����                  */
    //ע������ǩ
    private JLabel[] jlArray_2=new JLabel[]{
            new JLabel("�� �� ��"),new JLabel("��      ��"),new JLabel("ȷ������"),new JLabel("22222")
    };
    //ע����水ť
    private JButton[] jbArray_2=new JButton[]{
            new JButton("ע��"),new JButton("���"),new JButton("����")
    };
    //ע��������ֿ�
    private JTextField jtxtName_2=new JTextField("");
    private JPasswordField jtxtPassword_2_1=new JPasswordField("");
    private JPasswordField jtxtPassword_2_2=new JPasswordField("");
    //�������ݿ�����
    private Connection con=null;

    //���캯����������
    public PassWord(){
        conDatabase();
        //���ڲ���Ϊnull
        this.setLayout(null);                               
        //ע��Ϊ��Ƭ����
        jp.setLayout(new CardLayout());                     
        //����������С
        jp.setBounds(10,10,370,250);                        
        //������¼����
        jp_1=signIn(jp_1);                                  
        //����ע�����
        jp_2=register(jp_2);                                
        //�Ѹ��ֽ�����뿨Ƭ����
        jp.add(jp_1,"sign");                                
        jp.add(jp_2,"register");                    
        //��������������벼��
        this.add(jp);                                       

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100, 100, 400, 275);
        this.setTitle("��¼");
        this.setVisible(true);
    }

    public JPanel signIn(JPanel jp_1){
        //��¼����Ĭ�ϲ���
        jp_1.setLayout(null);                               
        //���ð�ť��������
        for(int i=0;i<2;i++){                               
            jlArray[i].setBounds(55, 30+i*50, 90, 26);
            jbArray[i].setBounds(85+i*130, 150, 90, 26);
            jp_1.add(jlArray[i]);
            jp_1.add(jbArray[i]);
            jbArray[i].addActionListener(this);
        }
        //���������м���
        jtxtName.setBounds(135, 30, 180, 30);               
        jp_1.add(jtxtName);
        jtxtName.addActionListener(this);

        //��������м���
        jtxtPassword.setBounds(135, 80, 180, 30);
        jp_1.add(jtxtPassword);
        jtxtPassword.addActionListener(this);

        //��ʼ����¼��ʾ
        jlArray[2].setBounds(10, 180, 300, 30);
        jp_1.add(jlArray[2]);

        //��ʼ��ע����ʾ
        jlArray[3].setBounds(170, 195, 300, 30);
        jp_1.add(jlArray[3]);

        //��ʼ��ע�ᰴť��������
        jbArray[2].setBounds(300, 195, 60, 30);
        jp_1.add(jbArray[2]);
        jbArray[2].addActionListener(this);
        //���ص�¼����
        return jp_1;
    }

    public JPanel register(JPanel jp_2){
        //ע�����ΪĬ�ϲ���
        jp_2.setLayout(null);
        //��ʼ���û����ȱ�ǩ
        for(int i=0;i<3;i++){
            jlArray_2[i].setBounds(55, 20+i*40, 90, 26);
            jp_2.add(jlArray_2[i]);
        }
        //��ʼ����ť
        for(int i=0;i<2;i++){
            jbArray_2[i].setBounds(85+i*130, 150, 90, 26);
            jp_2.add(jbArray_2[i]);
            jbArray_2[i].addActionListener(this);
        }
        //��ʼ�������򲢼���
        jtxtName_2.setBounds(135, 20, 180, 30);
        jp_2.add(jtxtName_2);
        jtxtName_2.addActionListener(this);

        //��ʼ������򲢼���
        jtxtPassword_2_1.setBounds(135, 60, 180, 30);
        jp_2.add(jtxtPassword_2_1);
        jtxtPassword_2_1.addActionListener(this);

        //��ʼ��ȷ������򲢼���
        jtxtPassword_2_2.setBounds(135, 100, 180, 30);
        jp_2.add(jtxtPassword_2_2);
        jtxtPassword_2_2.addActionListener(this);

        //��ʼ��ע����ʾ
        jlArray_2[3].setBounds(40, 195, 300, 30);
        jp_2.add(jlArray_2[3]);

        //��ʼ�����ؼ�������
        jbArray_2[2].setBounds(300, 195, 60, 30);
        jp_2.add(jbArray_2[2]);
        jbArray_2[2].addActionListener(this);

        return jp_2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //��ȡ��Ƭ���ֹ���������
        CardLayout cl=(CardLayout)jp.getLayout();
        //������һ�����ֿ�
        if(e.getSource()==jtxtName){
            jtxtPassword.requestFocus();
        //���
        }else if(e.getSource()==jbArray[1]){
            jlArray[2].setText("");
            jtxtPassword.setText("");
            jtxtName.setText("");
            jtxtName.requestFocus();
        //��¼�ж�
        }else if(e.getSource()==jbArray[0]){
                //����û�����Ϊ��
                if(!jtxtName.getText().equals("")){
                    System.out.println(""); 
                    System.out.println("������֤����..."); 
                //�����ж�����ķ���ֵ
                String str=Verification(jtxtName.getText(),String.valueOf(jtxtPassword.getPassword()));
                //��½�ɹ�
                if(str.equals("PasswordTrue")){
                    //String.valueOf �������ǽ������������ǿ��ת��ΪString����
                    jlArray[2].setText("��ϲ���¼�ɹ�������");
                //�������
                }else if(str.equals("PasswordFalse")){
                    jlArray[2].setText("�Բ���������󣡣���");
                    jtxtPassword.setText("");
                    jtxtPassword.requestFocus();
                //�û���������
                }else if(str.equals("NameNull")){
                    jlArray[2].setText("�Բ����û��������ڣ�����");
                    jtxtName.requestFocus();
                //δ֪����
                }else{
                    jlArray[2].setText("δ֪���󣡣���");
                }
            }
            /*                            */        
            /*          ע��ҳ��                                  */    
            /*                            */    
        //ת��ע�����
        }else if(e.getSource()==jbArray[2]){
            System.out.println(""); 
            System.out.println("/************����ע�����************/"); 
            //��Ƭʽ����תΪע�����
            cl.show(jp,"register");
            //���ѡ�򣬲�רע���û�����
            jlArray_2[3].setText("");
            jtxtPassword_2_1.setText("");
            jtxtPassword_2_2.setText("");
            jtxtName_2.setText("");
            jtxtName_2.requestFocus();
        //���
        }else if(e.getSource()==jbArray_2[1]){
            jlArray_2[3].setText("");
            jtxtPassword_2_1.setText("");
            jtxtPassword_2_2.setText("");
            jtxtName_2.setText("");
            jtxtName_2.requestFocus();
        //���ص���¼����
        }else if(e.getSource()==jbArray_2[2]){
            System.out.println(""); 
            System.out.println("/************�����¼����************/"); 
            //��Ƭʽ����תΪ��¼����
            cl.show(jp, "sign");
            //���ѡ�򣬲�רע���û���
            jlArray[2].setText("");
            jtxtPassword.setText("");
            jtxtName.setText("");
            jtxtName.requestFocus();
        //������һ��ѡ��
        }else if(e.getSource()==jtxtName_2){
            jtxtPassword_2_1.requestFocus();
        }else if(e.getSource()==jtxtPassword_2_1){
            jtxtPassword_2_2.requestFocus();
        //���ע�����
        }else if(e.getSource()==jbArray_2[0]){
                //�û�����Ϊ��
                if(!jtxtName_2.getText().equals("")){
                    //������������벻һ��
                    if(!String.valueOf(jtxtPassword_2_1.getPassword()).equals
                        (String.valueOf(jtxtPassword_2_2.getPassword()))){
                    jlArray_2[3].setText("�������벻һ����");
                    jtxtPassword_2_1.setText("");
                    jtxtPassword_2_2.setText("");
                    jtxtPassword_2_1.requestFocus();
                    System.out.println(""); 
                    System.out.println("���벻��ͬ��ֹ����..."); 
                }else{
                    System.out.println(""); 
                    System.out.println("������֤�û����Ƿ�Ϸ�..."); 
                    //���ע�ắ���ķ���ֵ
                    String str=signUser(jtxtName_2.getText(),String.valueOf(jtxtPassword_2_1.getPassword()));
                    //�û����Ѵ���
                    if(str.equals("exist")){
                        jlArray_2[3].setText("�û����Ѵ��ڣ�");
                        jtxtName_2.requestFocus();
                    //ע��ɹ�
                    }else if(str.equals("signed")){
                        jlArray_2[3].setText("ע��ɹ�������");
                    //����δ֪����δ�ɹ�
                    }else if(str.equals("false")){
                        jlArray_2[3].setText("δע�ᣬ����δ֪����");
                    //����δ֪����
                    }else{
                        jlArray_2[3].setText("����δ֪����");
                    }
                }       
            }
        }
    }

    public void conDatabase(){                                              //�������ݿ�
        try {
            //������������ʱ��Ҫ��֤����mysql-connector-java�����
            Class.forName("com.mysql.jdbc.Driver");
            //��ʼ�����ݿ�ĵ�ַ����ʱ�����������Լ������ݿ�����
            String url = "jdbc:mysql://localhost:3306/test" ;
            //�������ݿ���û���������,һ��Ĭ���û�������root
            String username = "root" ;   
            String password = "123456" ;  
            //�������ݿ�
            con=DriverManager.getConnection(url , username , password ) ; 
            System.out.println("���ݿ���������ɹ���"); 
        } catch (ClassNotFoundException e) {
            System.out.println("�Ҳ������������� ����������ʧ�ܣ�"); 
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("���ݿ���������ʧ�ܣ�"); 
            e.printStackTrace();
            System.exit(1);
        }
    }


    /*�����õ��ǣ�����Ҫ��֤���ߴ�����û��������봫�ݸ�������
     * Ȼ��������������֤�󣬷��ز�ͬ���ַ������ж��û��Ƿ�Ϸ�*/

    public String Verification(String name,String password){
        //�ַ�������ֵ
        String reStr="";
        //��ʼ����ǣ������Ϊ0���ʾ�û��������ӣ���Ϊ0��Ϊ�û�����
        int mark=0;
        try {
            //����SignIn��
            String sql = "select * from user ";
            PreparedStatement preStmt =con.prepareStatement(sql); 
            //ִ�в�ѯ����
            ResultSet rs = preStmt.executeQuery(); 
            //���α�����
            while(rs.next()){
                //����ȡ���ı�ÿ��Ԫ�ص������봫�ݵ����ֶԱȣ������Ƿ�����û�
                if(rs.getString(1).equals(name)){
                    mark=1;
                    //������ȷ
                    if(rs.getString(2).equals(password)){
                        reStr="PasswordTrue";
                        System.out.println("������ȷ");
                    //���벻��ȷ
                    }else{
                        reStr="PasswordFalse";
                        System.out.println("�������");
                    }
                }
            }
            //�û���������
            if(mark==0){
                reStr="NameNull";
                System.out.println("�û���������");
            }
            //�ر�������
            rs.close();
            preStmt.close();
        } catch (SQLException e) {
            System.out.println("���ݿ��ѯ����ʧ�ܣ�"); 
            e.printStackTrace();
        }

        return reStr;
    }

    public String signUser(String name,String password){
        //������ȡ�û������ֵı�
        ArrayList<String> namelist=new ArrayList<>();
        //�ַ�����ֵ
        String str="";
        //�û��Ƿ���ڵı��ֵ���ж��Ƿ����ı��
        int i=0,mark=0;
        try {
            //����SignIn��
            String sql = "select * from user ";
            PreparedStatement preStmt;
            preStmt = con.prepareStatement(sql);
            ResultSet rs = preStmt.executeQuery(); 
            //��ȡ���е��û���
            while(rs.next()){
                namelist.add(rs.getString(1));
            }
            rs.close();
            //�����ݿ�����û�������Ҫע����û������жԱ�
            for(String s:namelist){
                if(s.equals(name)){
                    str="exist";
                    System.out.println("�û��Ѵ���");
                    mark=1;
                }
            }
            //������Ϊ0�����ʾ�û���������
            if(mark==0){
                //��Ҫ����SignIn���е�Name��PasswordԪ��
                String sql2="insert into user(name,pwd) values(?,?)";
                PreparedStatement preStmt2 =con.prepareStatement(sql2); 
                //����NameԪ��
                preStmt2.setString(1,name);
                //����PasswordԪ��
                preStmt2.setString(2, password);
                //��ȡ����ֵ,iΪ1,���ʾ�ɹ���Ϊ0���ʾʧ��
                i=preStmt2.executeUpdate(); 
                //�ر�����
                preStmt2.close();
                if(i==1){
                    str="signed";
                    System.out.println("�ɹ����룡");
                }else{
                    str="false";
                    System.out.println("δ���룬����δ֪����");
                }
            }
            rs.close();
            preStmt.close();
        } catch (SQLException e) {
            System.out.println("���ݿ��������ʧ�ܣ�"); 
            e.printStackTrace();
        } 
        //�����ַ���
        return str;
    }

    public static void main(String[] args) {
        new PassWord();
    }

}

