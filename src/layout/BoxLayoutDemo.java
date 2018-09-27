package layout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
 
public class BoxLayoutDemo extends JFrame{
    JButton btn1=new JButton("one");
    JButton btn2=new JButton("two");
    JButton btn3=new JButton("three");
    JButton btn4=new JButton("four");
    JButton btn5=new JButton("five");
    BoxLayoutDemo(){
        init();
        this.setTitle("��񲼾�");
        this.setResizable(true);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void init(){
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
        //����ʹ��Box��������
        //Box box = new Box(BoxLayout.Y_AXIS);box.add(btn...);box.add(creat..);
        this.add(btn1);
        this.add(btn2);
        this.getContentPane().add(Box.createHorizontalStrut(10)); //����x����ʱ����ӹ̶�����������
        //this.getContentPane().add(Box.createVerticalStrut(5)); //����y����ʱ����ӹ̶��߶��������
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
    }
    public static void main(String args[]){
        new BoxLayoutDemo();
    }
}
