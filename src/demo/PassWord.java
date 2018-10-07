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
    //初始化主面板
    private JPanel jp=new JPanel();
    //初始化登录面板
    private JPanel jp_1=new JPanel();
    //初始化注册面板
    private JPanel jp_2=new JPanel();

    /*          登录界面                    */
    //登录界面标签
    private JLabel[] jlArray=new JLabel[]{
            new JLabel("用户名"),new JLabel("密    码"),new JLabel(""),new JLabel("若无账号，请注册...")
    };
    //登录界面按钮
    private JButton[] jbArray=new JButton[]{
            new JButton("登录"),new JButton("清空"),new JButton("注册")
    };
    //登录界面文字框
    private JTextField jtxtName=new JTextField("");
    private JPasswordField jtxtPassword=new JPasswordField("");

    /*         注册界面                  */
    //注册界面标签
    private JLabel[] jlArray_2=new JLabel[]{
            new JLabel("用 户 名"),new JLabel("密      码"),new JLabel("确认密码"),new JLabel("22222")
    };
    //注册界面按钮
    private JButton[] jbArray_2=new JButton[]{
            new JButton("注册"),new JButton("清空"),new JButton("返回")
    };
    //注册界面文字框
    private JTextField jtxtName_2=new JTextField("");
    private JPasswordField jtxtPassword_2_1=new JPasswordField("");
    private JPasswordField jtxtPassword_2_2=new JPasswordField("");
    //建立数据库连接
    private Connection con=null;

    //构造函数，主程序
    public PassWord(){
        conDatabase();
        //窗口布局为null
        this.setLayout(null);                               
        //注册为卡片布局
        jp.setLayout(new CardLayout());                     
        //设置容器大小
        jp.setBounds(10,10,370,250);                        
        //创建登录界面
        jp_1=signIn(jp_1);                                  
        //创建注册界面
        jp_2=register(jp_2);                                
        //把各种界面加入卡片布局
        jp.add(jp_1,"sign");                                
        jp.add(jp_2,"register");                    
        //把最外层容器加入布局
        this.add(jp);                                       

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100, 100, 400, 275);
        this.setTitle("登录");
        this.setVisible(true);
    }

    public JPanel signIn(JPanel jp_1){
        //登录界面默认布局
        jp_1.setLayout(null);                               
        //设置按钮，并监听
        for(int i=0;i<2;i++){                               
            jlArray[i].setBounds(55, 30+i*50, 90, 26);
            jbArray[i].setBounds(85+i*130, 150, 90, 26);
            jp_1.add(jlArray[i]);
            jp_1.add(jbArray[i]);
            jbArray[i].addActionListener(this);
        }
        //对姓名进行监听
        jtxtName.setBounds(135, 30, 180, 30);               
        jp_1.add(jtxtName);
        jtxtName.addActionListener(this);

        //对密码进行监听
        jtxtPassword.setBounds(135, 80, 180, 30);
        jp_1.add(jtxtPassword);
        jtxtPassword.addActionListener(this);

        //初始化登录提示
        jlArray[2].setBounds(10, 180, 300, 30);
        jp_1.add(jlArray[2]);

        //初始化注册提示
        jlArray[3].setBounds(170, 195, 300, 30);
        jp_1.add(jlArray[3]);

        //初始化注册按钮，并监听
        jbArray[2].setBounds(300, 195, 60, 30);
        jp_1.add(jbArray[2]);
        jbArray[2].addActionListener(this);
        //返回登录界面
        return jp_1;
    }

    public JPanel register(JPanel jp_2){
        //注册界面为默认布局
        jp_2.setLayout(null);
        //初始化用户名等标签
        for(int i=0;i<3;i++){
            jlArray_2[i].setBounds(55, 20+i*40, 90, 26);
            jp_2.add(jlArray_2[i]);
        }
        //初始化按钮
        for(int i=0;i<2;i++){
            jbArray_2[i].setBounds(85+i*130, 150, 90, 26);
            jp_2.add(jbArray_2[i]);
            jbArray_2[i].addActionListener(this);
        }
        //初始化姓名框并监听
        jtxtName_2.setBounds(135, 20, 180, 30);
        jp_2.add(jtxtName_2);
        jtxtName_2.addActionListener(this);

        //初始化密码框并监听
        jtxtPassword_2_1.setBounds(135, 60, 180, 30);
        jp_2.add(jtxtPassword_2_1);
        jtxtPassword_2_1.addActionListener(this);

        //初始化确认密码框并监听
        jtxtPassword_2_2.setBounds(135, 100, 180, 30);
        jp_2.add(jtxtPassword_2_2);
        jtxtPassword_2_2.addActionListener(this);

        //初始化注册提示
        jlArray_2[3].setBounds(40, 195, 300, 30);
        jp_2.add(jlArray_2[3]);

        //初始化返回键并监听
        jbArray_2[2].setBounds(300, 195, 60, 30);
        jp_2.add(jbArray_2[2]);
        jbArray_2[2].addActionListener(this);

        return jp_2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取卡片布局管理器引用
        CardLayout cl=(CardLayout)jp.getLayout();
        //跳到下一个文字框
        if(e.getSource()==jtxtName){
            jtxtPassword.requestFocus();
        //清空
        }else if(e.getSource()==jbArray[1]){
            jlArray[2].setText("");
            jtxtPassword.setText("");
            jtxtName.setText("");
            jtxtName.requestFocus();
        //登录判定
        }else if(e.getSource()==jbArray[0]){
                //如果用户名框不为空
                if(!jtxtName.getText().equals("")){
                    System.out.println(""); 
                    System.out.println("正在验证密码..."); 
                //接受判定程序的返回值
                String str=Verification(jtxtName.getText(),String.valueOf(jtxtPassword.getPassword()));
                //登陆成功
                if(str.equals("PasswordTrue")){
                    //String.valueOf 的作用是将输进来的密码强制转换为String类型
                    jlArray[2].setText("恭喜你登录成功！！！");
                //密码错误
                }else if(str.equals("PasswordFalse")){
                    jlArray[2].setText("对不起，密码错误！！！");
                    jtxtPassword.setText("");
                    jtxtPassword.requestFocus();
                //用户名不存在
                }else if(str.equals("NameNull")){
                    jlArray[2].setText("对不起，用户名不存在！！！");
                    jtxtName.requestFocus();
                //未知错误
                }else{
                    jlArray[2].setText("未知错误！！！");
                }
            }
            /*                            */        
            /*          注册页面                                  */    
            /*                            */    
        //转到注册界面
        }else if(e.getSource()==jbArray[2]){
            System.out.println(""); 
            System.out.println("/************进入注册界面************/"); 
            //卡片式布局转为注册界面
            cl.show(jp,"register");
            //清空选框，并专注于用户名框
            jlArray_2[3].setText("");
            jtxtPassword_2_1.setText("");
            jtxtPassword_2_2.setText("");
            jtxtName_2.setText("");
            jtxtName_2.requestFocus();
        //清空
        }else if(e.getSource()==jbArray_2[1]){
            jlArray_2[3].setText("");
            jtxtPassword_2_1.setText("");
            jtxtPassword_2_2.setText("");
            jtxtName_2.setText("");
            jtxtName_2.requestFocus();
        //返回到登录界面
        }else if(e.getSource()==jbArray_2[2]){
            System.out.println(""); 
            System.out.println("/************进入登录界面************/"); 
            //卡片式布局转为登录界面
            cl.show(jp, "sign");
            //清空选框，并专注于用户名
            jlArray[2].setText("");
            jtxtPassword.setText("");
            jtxtName.setText("");
            jtxtName.requestFocus();
        //跳到下一个选框
        }else if(e.getSource()==jtxtName_2){
            jtxtPassword_2_1.requestFocus();
        }else if(e.getSource()==jtxtPassword_2_1){
            jtxtPassword_2_2.requestFocus();
        //点击注册界面
        }else if(e.getSource()==jbArray_2[0]){
                //用户名框不为空
                if(!jtxtName_2.getText().equals("")){
                    //两次输入的密码不一样
                    if(!String.valueOf(jtxtPassword_2_1.getPassword()).equals
                        (String.valueOf(jtxtPassword_2_2.getPassword()))){
                    jlArray_2[3].setText("两次密码不一样！");
                    jtxtPassword_2_1.setText("");
                    jtxtPassword_2_2.setText("");
                    jtxtPassword_2_1.requestFocus();
                    System.out.println(""); 
                    System.out.println("密码不相同禁止存入..."); 
                }else{
                    System.out.println(""); 
                    System.out.println("正在验证用户名是否合法..."); 
                    //获得注册函数的返回值
                    String str=signUser(jtxtName_2.getText(),String.valueOf(jtxtPassword_2_1.getPassword()));
                    //用户名已存在
                    if(str.equals("exist")){
                        jlArray_2[3].setText("用户名已存在！");
                        jtxtName_2.requestFocus();
                    //注册成功
                    }else if(str.equals("signed")){
                        jlArray_2[3].setText("注册成功！！！");
                    //发生未知错误，未成功
                    }else if(str.equals("false")){
                        jlArray_2[3].setText("未注册，发生未知错误！");
                    //发生未知错误
                    }else{
                        jlArray_2[3].setText("发生未知错误！");
                    }
                }       
            }
        }
    }

    public void conDatabase(){                                              //连接数据库
        try {
            //加载驱动，此时你要保证你有mysql-connector-java这个包
            Class.forName("com.mysql.jdbc.Driver");
            //初始化数据库的地址，此时最后面的是你自己的数据库名字
            String url = "jdbc:mysql://localhost:3306/test" ;
            //连接数据库的用户名和密码,一般默认用户名就是root
            String username = "root" ;   
            String password = "123456" ;  
            //连接数据库
            con=DriverManager.getConnection(url , username , password ) ; 
            System.out.println("数据库连接主类成功！"); 
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动程序类 ，加载驱动失败！"); 
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("数据库连接主类失败！"); 
            e.printStackTrace();
            System.exit(1);
        }
    }


    /*这里用的是，将需要验证或者存入的用户名和密码传递给函数，
     * 然后函数经过处理验证后，返回不同的字符串来判定用户是否合法*/

    public String Verification(String name,String password){
        //字符串返回值
        String reStr="";
        //初始化标记，若标记为0则表示用户名不村子，不为0则为用户存在
        int mark=0;
        try {
            //连接SignIn表单
            String sql = "select * from user ";
            PreparedStatement preStmt =con.prepareStatement(sql); 
            //执行查询操作
            ResultSet rs = preStmt.executeQuery(); 
            //依次遍历表单
            while(rs.next()){
                //将获取到的表单每个元素的名字与传递的名字对比，查找是否存在用户
                if(rs.getString(1).equals(name)){
                    mark=1;
                    //密码正确
                    if(rs.getString(2).equals(password)){
                        reStr="PasswordTrue";
                        System.out.println("密码正确");
                    //密码不正确
                    }else{
                        reStr="PasswordFalse";
                        System.out.println("密码错误");
                    }
                }
            }
            //用户名不存在
            if(mark==0){
                reStr="NameNull";
                System.out.println("用户名不存在");
            }
            //关闭数据流
            rs.close();
            preStmt.close();
        } catch (SQLException e) {
            System.out.println("数据库查询连接失败！"); 
            e.printStackTrace();
        }

        return reStr;
    }

    public String signUser(String name,String password){
        //创建获取用户名名字的表单
        ArrayList<String> namelist=new ArrayList<>();
        //字符返回值
        String str="";
        //用户是否存在的标记值和判定是否存入的标记
        int i=0,mark=0;
        try {
            //连接SignIn表单
            String sql = "select * from user ";
            PreparedStatement preStmt;
            preStmt = con.prepareStatement(sql);
            ResultSet rs = preStmt.executeQuery(); 
            //获取所有的用户名
            while(rs.next()){
                namelist.add(rs.getString(1));
            }
            rs.close();
            //将数据库里的用户名与需要注册的用户名进行对比
            for(String s:namelist){
                if(s.equals(name)){
                    str="exist";
                    System.out.println("用户已存在");
                    mark=1;
                }
            }
            //如果标记为0，则表示用户名不存在
            if(mark==0){
                //需要插入SignIn表单中的Name和Password元素
                String sql2="insert into user(name,pwd) values(?,?)";
                PreparedStatement preStmt2 =con.prepareStatement(sql2); 
                //插入Name元素
                preStmt2.setString(1,name);
                //插入Password元素
                preStmt2.setString(2, password);
                //获取返回值,i为1,则表示成功，为0则表示失败
                i=preStmt2.executeUpdate(); 
                //关闭连接
                preStmt2.close();
                if(i==1){
                    str="signed";
                    System.out.println("成功存入！");
                }else{
                    str="false";
                    System.out.println("未存入，发生未知错误！");
                }
            }
            rs.close();
            preStmt.close();
        } catch (SQLException e) {
            System.out.println("数据库存入连接失败！"); 
            e.printStackTrace();
        } 
        //返回字符串
        return str;
    }

    public static void main(String[] args) {
        new PassWord();
    }

}

